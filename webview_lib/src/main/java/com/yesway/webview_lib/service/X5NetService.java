package com.yesway.webview_lib.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tencent.smtt.sdk.QbSdk;
import com.yesway.webview_lib.R;

/**
 * Email: 2185134304@qq.com
 * Created by JackChen 2018/4/23 11:46
 * Version 1.0
 * Params:
 * Description:
 */
public class X5NetService extends IntentService {

    public static final String TAG = X5NetService.class.getSimpleName();
    private static final String CHANNEL_ID_STRING = "X5NetService";
    public static Handler mHandler = new Handler();

    public X5NetService() {
        super(TAG);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public X5NetService(String name) {
        super(TAG);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = null;
            channel = new NotificationChannel(CHANNEL_ID_STRING, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID_STRING).build();
            startForeground(1, notification);
        }

    }

    /**
     * 初始化X5WebView
     */
    @Override
    public void onHandleIntent(@Nullable Intent intent) {
        initX5Web();
    }

    public void initX5Web() {
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
        @Override
        public void onViewInitFinished(boolean arg0) {
            // TODO Auto-generated method stub
            Log.i(TAG, "x5webView  onViewInitFinished初始化成功");
            stopForegroundService();
        }

        @Override
        public void onCoreInitFinished() {
            // TODO Auto-generated method stub
            Log.i(TAG, "x5webView onCoreInitFinished 初始化成功");
            stopForegroundService();
        }
    };

    private void stopForegroundService() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopForeground(true);
            }
        }, 1000);
    }


}



