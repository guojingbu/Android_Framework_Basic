package com.yesway.api_lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;

import com.yesway.api_lib.R;
import com.yesway.api_lib.networklib.RetrofitManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Toast提示工具
 */
public class ToastUtils {
    /**
     * 吐司对象
     */
    private static Toast mToast;

    public static void showToast(String text) {
        mToast = Toast.makeText(RetrofitManager.mContext, text, Toast.LENGTH_SHORT);
        LayoutInflater inflater = LayoutInflater.from(RetrofitManager.mContext);
        View toastView = inflater.inflate(R.layout.toast_layout, null);
        mToast.setView(toastView);
        mToast.setText(text);
        if (isNotificationEnabled(RetrofitManager.mContext)) {
            mToast.show();
        } else {
            showSystemToast(mToast);
        }
    }


    /**
     * 关闭正在显示的Toast
     */
    public static void hideToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }


    private static Object iNotificationManagerObj;

    /**
     * 显示系统Toast
     */
    private static void showSystemToast(Toast toast) {
        try {
            @SuppressLint("SoonBlockedPrivateApi") Method getServiceMethod = Toast.class.getDeclaredMethod("getService");
            getServiceMethod.setAccessible(true);
            //hook INotificationManager
            if (iNotificationManagerObj == null) {
                iNotificationManagerObj = getServiceMethod.invoke(null);

                Class iNotificationManagerCls = Class.forName("android.app.INotificationManager");
                Object iNotificationManagerProxy = Proxy.newProxyInstance(toast.getClass().getClassLoader(), new Class[]{iNotificationManagerCls}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //强制使用系统Toast
                        if ("enqueueToast".equals(method.getName())
                                || "enqueueToastEx".equals(method.getName())) {  //华为p20 pro上为enqueueToastEx
                            args[0] = "android";
                        }
                        return method.invoke(iNotificationManagerObj, args);
                    }
                });
                Field sServiceFiled = Toast.class.getDeclaredField("sService");
                sServiceFiled.setAccessible(true);
                sServiceFiled.set(null, iNotificationManagerProxy);
            }
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息通知是否开启
     *
     * @return
     */
    private static boolean isNotificationEnabled(Context context) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        boolean areNotificationsEnabled = notificationManagerCompat.areNotificationsEnabled();
        return areNotificationsEnabled;
    }

}

 
