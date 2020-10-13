package com.yesway.common_lib.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @author guojingbu
 * @date 2019-09-19
 */
public abstract class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private final Activity mTargetActivity;

    public ActivityLifecycleCallbacks(Activity targetActivity) {
        mTargetActivity = targetActivity;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity == mTargetActivity) {
            mTargetActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            onTargetActivityDestroyed();
        }
    }

    protected abstract void onTargetActivityDestroyed();
}
