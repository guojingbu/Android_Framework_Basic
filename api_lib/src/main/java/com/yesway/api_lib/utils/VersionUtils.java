package com.yesway.api_lib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @Description
 * @Author guojingbu
 * @Date 2019/9/21
 */
public class VersionUtils {
    /**
     * 获取包名
     *
     * @return 包名
     */
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }
    /**
     * 获取当前应用的版本号
     */
    public static int getAppVersionCode(Context context) {
        // 获取手机的包管理者
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(getPackageName(context), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取当前应用的版本名称
     */
    public static String getAppVersionName(Context context) {
        // 获取手机的包管理者
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(getPackageName(context), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // 不可能发生.
            return "";
        }
    }
}
