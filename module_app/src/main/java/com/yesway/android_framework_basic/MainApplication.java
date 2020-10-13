package com.yesway.android_framework_basic;

import android.content.Intent;
import android.os.Build;

import com.yesway.common_lib.BaseApplication;
import com.yesway.map_lib.location.LocationUtils;
import com.yesway.share_lib.ShareUtils;
import com.yesway.share_lib.config.PlatformSinaWeibo;
import com.yesway.share_lib.config.PlatformWeiXin;
import com.yesway.share_lib.config.ShareConfig;
import com.yesway.share_lib.config.UmConfig;
import com.yesway.statistics_lib.StatisticsManager;
import com.yesway.webview_lib.service.X5NetService;

import cn.jpush.android.api.JPushInterface;


public class MainApplication extends BaseApplication {
    public static MainApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppManager.getAppManager();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        LocationUtils.getInstance().init(this);
        //分享
        ShareConfig shareConfig = new ShareConfig.Builder()
                .setConfig(new UmConfig("", "", ""))
                .setContext(this)
                .setPlatformWeiXin(new PlatformWeiXin("wxee18f7bd5adf8716", "42126e7ad23bc7aa8be4212461ba21ae"))
                .setPlatformSinaWeibo(new PlatformSinaWeibo("2763837637", "a1b6ec554ee3654b58daa1f28adb32f4", ""))
                .setDebug(true)
                .creatConfig();

        ShareUtils.init(shareConfig);
        StatisticsManager.setPageCollectionMode(StatisticsManager.PageMode.AUTO);
        preInitX5Core();

    }

    public static MainApplication getInstance() {
        return mInstance;
    }


    private void preInitX5Core() {
        // 启动服务的地方
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, X5NetService.class));
        } else {
            context.startService(new Intent(context, X5NetService.class));
        }
    }
}
