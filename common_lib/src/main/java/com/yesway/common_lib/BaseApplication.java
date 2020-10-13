package com.yesway.common_lib;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.yesway.api_lib.db.DaoManager;
import com.yesway.api_lib.networklib.RetrofitManager;
import com.yesway.common_lib.base.ActivityManager;
import com.yesway.common_lib.utils.V2xCrashUtils;
import com.yesway.common_lib.utils.language.MultiLanguageUtil;
import com.yesway.common_lib.utils.log.KLog;
import com.yesway.webview_lib.service.X5NetService;

/**
 * Application基础类
 * 1.上下文
 * 2.调试模式开关
 * 3.X5内核预加载
 * 4.arouter初始化
 * 5.glide图片加载优化
 */
public class BaseApplication extends Application {

    public static Context context;
    //是否开启调试
    private static boolean isDebug = true;
    private final static String APP_LOG_TAG = "Android_Framework_Basic";
    /**
     * 是否显示日志
     */
    private final static boolean IS_SHOWLOG = true;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        KLog.init(IS_SHOWLOG, APP_LOG_TAG);
        MultiDex.install(this);
//        V2xCrashUtils.init(new V2xCrashUtils.CrashAppListener() {
//            @Override
//            public void onFinishApp(Thread t, Throwable e) {
//            }
//        });
        RetrofitManager.init(this);
        //初始化greendao
        DaoManager.getInstance().init(this);
        initArouter();
        MultiLanguageUtil.getInstance().setApplicationLanguage(this);
    }

    /**
     * ARouter 初始化
     * com.onesignal:OneSignal:3.12.2
     */
    private void initArouter() {
        if (isDebug) {
            //打印日志
            ARouter.openLog();
            ARouter.openDebug();
        }
//        ARouter的实例化
        ARouter.init(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // Glide内存优化
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // Glide内存优化
        Glide.get(this).clearMemory();
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiLanguageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(base));
        Log.i("guojingbu", "BaseApplication attachBaseContext = " + getResources().getConfiguration().locale.getLanguage());
    }
}
