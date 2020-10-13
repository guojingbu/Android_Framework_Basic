package com.yesway.common_lib.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.ProgressBar;

import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.umeng.socialize.UMShareAPI;
import com.yesway.api_lib.utils.ToastUtils;
import com.yesway.common_lib.R;
import com.yesway.common_lib.utils.NetUtil;
import com.yesway.webview_lib.X5WebView;
import com.yesway.webview_lib.jsbridage.BridgeWebViewClient;

import org.greenrobot.eventbus.EventBus;

/**
 * @Description
 * @Author guojingbu
 * @Date 2019/10/8
 */
public abstract class BaseWebPageActivity extends BaseActivity {

    private static final String TAG = "BaseWebPageActivity";
    protected X5WebView mWebView;
    private Button mToolbarBtnLeft;
    private Button mToolbarBtnRight;
    private ProgressBar progressBar;
    private boolean isLoadingSuccessful = true;
    private boolean isVisibleProgressBar = true;

    @Override
    public void initView() {
        mWebView = findViewById(R.id.wev_abwp_page);
        mToolbarBtnLeft = findViewById(R.id.toolbar_btn_left);
        mToolbarBtnRight = findViewById(R.id.toolbar_btn_right);
        progressBar = findViewById(R.id.progress_bar);
        intSettingWebView();
        this.mToolbarBtnLeft.setCompoundDrawablesWithIntrinsicBounds(R.drawable.button_menu_close, 0,
                0, 0);
        this.mToolbarBtnLeft.setPadding(0, 0, 0, 0);
        this.mToolbarBtnLeft.setText("");
        this.mToolbarBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClose();
            }
        });
    }

    private void intSettingWebView() {
        // 允许5.0+支持 https + http 混合模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // MIXED_CONTENT_COMPATIBILITY_MODE
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        // 设置定位的数据库路径，若不设置定位数据库路径则无法使用定位功能
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        mWebView.getSettings().setGeolocationDatabasePath(dir);
        // 启用地理定位
        mWebView.getSettings().setGeolocationEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
                //不显示进度条
                if (!isVisibleProgressBar) {
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (progressBar != null && newProgress != 100) {
                    progressBar.setProgress(newProgress);
                    //Webview加载没有完成 就显示我们自定义的加载图
                    progressBar.setVisibility(View.VISIBLE);

                } else if (progressBar != null) {
                    //Webview加载完成 就隐藏进度条,显示Webview
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String s, GeolocationPermissionsCallback geolocationPermissionsCallback) {
                super.onGeolocationPermissionsShowPrompt(s, geolocationPermissionsCallback);
                geolocationPermissionsCallback.invoke(s, true, false);
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
            }
        });

        mWebView.setWebViewClient(new BridgeWebViewClient(mWebView) {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view,url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                supportInvalidateOptionsMenu();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webClientOnPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                webClientOnReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                sslErrorHandler.proceed(); // 接受网站证书
            }
        });
    }

    /**
     * 设置加载进度条是否显示
     *
     * @param isVisibleProgressBar
     */
    public void setProgressBarVisible(boolean isVisibleProgressBar) {
        this.isVisibleProgressBar = isVisibleProgressBar;
    }

    @Override
    public int bindView() {
        return R.layout.activity_base_web_page;
    }

    @Override
    protected void re_Request() {
        if (!NetUtil.checkNet()) {
            ToastUtils.showToast(getString(R.string.net_work_error));
            return;
        }
        mWebView.reload();
    }


    @Override
    public int onBindToolbarLayout() {
        return R.layout.common_web_toolbar;
    }

    @Override
    public void onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    /**
     * 重写 webClient 的 onReceivedError 方法,子类直接覆盖重写即可
     *
     * @param view
     * @param errorCode
     * @param description
     * @param failingUrl
     */
    protected void webClientOnReceivedError(WebView view, int errorCode, String description,
                                            String failingUrl) {
        showNetWorkErrorPage();
        isLoadingSuccessful = false;
    }

    /**
     * 重写 webClient 的 onPageFinished 方法,子类直接覆盖重写即可
     *
     * @param view
     * @param url
     */
    protected void webClientOnPageFinished(WebView view, String url) {
        if (isLoadingSuccessful) {
            mWebView.setVisibility(View.VISIBLE);
            hideErrorPage();
        } else {
            mWebView.setVisibility(View.GONE);
        }
        isLoadingSuccessful = true;
        isShowCloseButton(mWebView);
    }
    @Override
    protected void onDestroy() {
        if(mWebView!=null){
            mWebView.stopLoading();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    /**
     * 是否显示 "关闭(叉子)" 按钮及 "关闭(叉子)" 执行的操作,子类直接重写即可
     */
    protected void isShowCloseButton(final WebView webView) {
        if (webView == null || this.mToolbarBtnLeft == null) {
            return;
        }
        if (webView.canGoBack()) {
            this.mToolbarBtnLeft.setVisibility(View.VISIBLE);
        } else {
            this.mToolbarBtnLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 页面关闭按钮执行的操作
     */
    protected void onClose() {
        finish();
    }

}
