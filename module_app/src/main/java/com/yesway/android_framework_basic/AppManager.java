package com.yesway.android_framework_basic;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
/**
 * App 的管理,activity前后台状态等等
 * Created by Riverlet PC on 2019/4/12.
 */
public class AppManager implements Application.ActivityLifecycleCallbacks {
    private static AppManager instance;
    private Context context;
    /**
     * 当前启动的activity数量
     */
    private int appCount = 0;
    /**
     * 判断当前程序前后台
     */
    private boolean isForground = false;

    private AppManager() {
        context = MainApplication.getInstance();
        MainApplication.getInstance().registerActivityLifecycleCallbacks(this);

    }

    /**
     * 单实例 , UI无需考虑多线程同步问题
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        appCount++;
        if (isForgroundAppValue()) {
            isForground = true;
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        appCount--;
        isForground = false;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    private boolean isForgroundAppValue() {
        return appCount == 1;
    }
}
