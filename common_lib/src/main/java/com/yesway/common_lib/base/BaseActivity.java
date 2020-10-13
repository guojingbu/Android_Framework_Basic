package com.yesway.common_lib.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.yesway.common_lib.R;
import com.yesway.common_lib.eventbus.BindEventBus;
import com.yesway.common_lib.eventbus.EventBusUtils;
import com.yesway.common_lib.utils.StatusBarUtil;
import com.yesway.common_lib.utils.language.MultiLanguageUtil;
import com.yesway.common_lib.widget.ErrorHintView;

import java.util.Stack;

public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
    protected static final String TAG = BaseActivity.class.getSimpleName();
    protected ErrorHintView mErrorHintView;
    public Context mContext;
    private ViewStub mViewSubContent;
    private ViewStub mViewSubToolBar;
    private Toolbar mToolbar;
    protected TextView mTxtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决Android P 以上版本设置全屏主题不管用问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }
        setContentView(R.layout.activity_base_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setStatusBar();
        ARouter.getInstance().inject(this);
        initCommonView();
        mContext = this;
        initView();
        initData();
        ActivityManager.getInstance().addActivity(this);
        // 若使用BindEventBus注解，则绑定EventBus
        if (getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.register(this);
        }
        MultiLanguageUtil.getInstance().setConfiguration(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }

    private void initCommonView() {
        mErrorHintView = (ErrorHintView) findViewById(R.id.hintView);
        mViewSubContent = (ViewStub) findViewById(R.id.view_contnet);
        mViewSubToolBar = (ViewStub) findViewById(R.id.view_stub_toolbar);
        mViewSubContent.setLayoutResource(bindView());
        mViewSubContent.inflate();
        if (enableToolbar()) {
            mViewSubToolBar.setLayoutResource(onBindToolbarLayout());
            View view = mViewSubToolBar.inflate();
            initToolbar(view);
        }
    }

    public void onRecreat(Activity activity) {
        //recreate();
        Stack<Activity> activityStack = ActivityManager.getInstance().getActivityStack();
        if (null == activityStack) {
            return;
        }
        for (int i = 0; i < activityStack.size(); i++) {
            if (activity != activityStack.get(i)) {
                activityStack.get(i).recreate();
            }
        }
    }

    public int onBindToolbarLayout() {
        return R.layout.common_toolbar;
    }

    public boolean enableToolbar() {
        return true;
    }

    protected void setStatusBar() {
        if (fitsSystemWindows()) {
            StatusBarUtil.setColor(this, getResources().getColor(R.color.green_1), 0);
        } else {
            StatusBarUtil.setTransparent(this);
            StatusBarUtil.setFitsSystemWindows(this, false);
        }

    }

    protected boolean fitsSystemWindows() {
        return true;
    }

    protected void initToolbar(View view) {
        mToolbar = view.findViewById(R.id.toolbar_root);
        mTxtTitle = view.findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mTxtTitle != null) {
            mTxtTitle.setText(title);
        }
        //可以再次覆盖设置title
        String tootBarTitle = getTootBarTitle();
        if (mTxtTitle != null && !TextUtils.isEmpty(tootBarTitle)) {
            mTxtTitle.setText(tootBarTitle);
        }
    }

    /**
     * 子类设置title
     *
     * @return
     */
    public String getTootBarTitle() {
        return "";
    }

    /**
     * 设置toolbar背景颜色
     *
     * @param colorId
     */
    public void setToobarBgColor(int colorId) {
        if (mToolbar != null) {
            mToolbar.setBackground(new ColorDrawable(getResources().getColor(colorId)));
        }
    }

    /**
     * 设置返回按钮
     *
     * @param drawableId
     */
    public void setCallBackIcon(int drawableId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(drawableId);
        }
    }

    /**
     * 设置toolbar文字
     */
    public void setToobarText(String title) {

        if (mTxtTitle != null && !TextUtils.isEmpty(title)) {
            mTxtTitle.setText(title);
        }
    }

    /**
     * onDestroy presenter释放view引用, Activity的管理者移除此页面
     */
    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
        if (getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.unregister(this);
        }
    }


    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化view，需要覆写setConventView方法
     */
    public abstract void initView();

    /**
     * 绑定view
     */
    public abstract int bindView();

    /**
     * 显示loading
     */
    @Override
    public void showInitLoading() {
        mErrorHintView.loadingData();
    }

    /**
     * 隐藏loading
     */
    @Override
    public void hideInitLoading() {
        mErrorHintView.hideLoading();
    }

    @Override
    public void showTransLoading() {
        mErrorHintView.loadingTransData();
    }

    @Override
    public void hideTransLoading() {
        mErrorHintView.hideTransLoading();
    }

    @Override
    public void hideNoData() {
        mErrorHintView.close();
    }

    @Override
    public void showNoDataPage(String content, int drawableId) {
        mErrorHintView.noData(content, drawableId);
    }

    @Override
    public void showNetWorkErrorPage() {
        if (mErrorHintView == null) {
            return;
        }
        mErrorHintView.netError(0, new ErrorHintView.OperateListener() {

            @Override
            public void operate() {
                re_Request();
            }
        });
    }

    @Override
    public void showTransNetWorkErrorPage() {
        if (mErrorHintView == null) {
            return;
        }
        mErrorHintView.netError(R.color.transparent, new ErrorHintView.OperateListener() {

            @Override
            public void operate() {
                re_Request();
            }
        });
    }

    @Override
    public void hideErrorPage() {
        if (mErrorHintView != null) {
            mErrorHintView.close();
        }
    }

    protected void re_Request() {

    }


    /**
     * 点击空白位置 隐藏软键盘
     */
    @SuppressLint("NewApi")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != getCurrentFocus()) {

            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
