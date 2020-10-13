package com.yesway.module_main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.yesway.api_lib.config.CommonConfig;
import com.yesway.api_lib.utils.SPUtils;
import com.yesway.common_lib.base.BaseActivity;
import com.yesway.common_lib.utils.EnvironmentUtil;

public class SplashActivity extends BaseActivity {
    // 延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 3000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final int GO_LOGIN = 1002;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int code = msg.what;
            switch (code) {
                case GO_HOME:
                    //跳转到主页
                    MainActivity.startMainActivity(SplashActivity.this);
                    finish();
                    break;
                case GO_GUIDE:
                    //TODO:首次使用该版本跳到导航页
                    GuideActivity.startGuideActivity(SplashActivity.this);
                    finish();
                    break;
                case GO_LOGIN:
                    LoginActivity.startLoginActivity(SplashActivity.this);
                    finish();
                    break;

            }

        }
    };

    @Override
    public void initData() {
        // 当前APP包内的版本号
        int lastVersion = (int) SPUtils.get(SplashActivity.this, CommonConfig.COMMON.KEY.APP_VERSION, 0);
        int versionCode = EnvironmentUtil.getAppVersionCode(SplashActivity.this);
        if (versionCode > lastVersion) {
            //缓存最新版本
            SPUtils.put(SplashActivity.this, CommonConfig.COMMON.KEY.APP_VERSION, versionCode);
            //跳入引导页
            goGuide();
        } else
            //跳入主页
//            goHome();
            goLogin();
    }

    @Override
    public void initView() {
        //解决应用首次安装home键后会重启应用问题
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
    }

    @Override
    public int bindView() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean enableToolbar() {
        return false;
    }

    @Override
    protected boolean fitsSystemWindows() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    private void goLogin() {
        mHandler.sendEmptyMessageDelayed(GO_LOGIN, SPLASH_DELAY_MILLIS);
    }

    private void goGuide() {
        mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
    }

    private void goHome() {
        mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
    }
}
