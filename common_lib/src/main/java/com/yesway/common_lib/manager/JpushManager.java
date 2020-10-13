package com.yesway.common_lib.manager;

import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * author : guojingbu
 * date   : 2020/9/23
 * desc   :
 */
public class JpushManager {
    /**
     * 设置别名操作序列号
     */
    public final static int SEQUENCE_CODE = 1000;

    /**
     * 设置别名
     *
     * @param context
     * @param alias
     */
    public static void setAlias(Context context, String alias) {
        JPushInterface.setAlias(context, SEQUENCE_CODE, alias);
    }

    /**
     * 删除别名
     *
     * @param context
     */
    public static void delAlias(Context context) {
        JPushInterface.deleteAlias(context, SEQUENCE_CODE);
    }
}
