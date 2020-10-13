package com.yesway.map_lib.location;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;
import com.amap.api.location.AMapLocationListener;

/**
 * created by @author guojingbu on ${DATA}
 * 高德定位封装工具类
 */

public class LocationUtils {

    /**
     * 定位utils实体引用
     */
    private static LocationUtils mInstance;
    /**
     * 高德定位客户端实体引用
     */
    private AMapLocationClient locationClient = null;
    /**
     * 高德定位配置类
     */
    private AMapLocationClientOption locationOption = null;
    /**
     * 定位工具类构造
     */
    private LocationUtils() {

    }

    /**
     * 单例模式获取定位工具类实体
     *
     * @return
     */
    public static LocationUtils getInstance() {
        if (mInstance == null) {
            synchronized (LocationUtils.class) {
                if (mInstance == null) {
                    mInstance = new LocationUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化定位
     */
    public void init(Context context) {
        //初始化client
        locationClient = new AMapLocationClient(context.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
    }

    /**
     * 设置定位配置参数
     *
     * @param option
     */
    public void setLcationOption(AMapLocationClientOption option) {
        this.locationOption = option;
    }

    /**
     * 获取最后一次定位的位置信息（历史）
     *
     * @return
     */
    public AMapLocation getLastKnownLocation() {
        AMapLocation lastKnownLocation = null;
        if (locationClient != null) {
            lastKnownLocation = locationClient.getLastKnownLocation();
        }
        return lastKnownLocation;
    }

    OnLocationListener locationListener;

    public void setLocationListener(OnLocationListener locationListener) {
        this.locationListener = locationListener;
    }

    /**
     * 开始定位
     *
     * @param isLooper 是否循环定位
     */
    public void startLocation(final Boolean isLooper) {
        if (!isLooper) {
            locationOption.setOnceLocation(true);
        } else {
            locationOption.setOnceLocation(false);
        }
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (null != location) {
                    if (location.getErrorCode() == 0) {
                        //定位成功
                        locationListener.locationSuccess(location);
                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + location.getErrorCode() + ", errInfo:"
                                + location.getErrorInfo());
                        //定位失败
                        locationListener.locationError();
                    }
                } else {
                    //定位失败
                    locationListener.locationError();
                }
                if (!isLooper) {
                    // 停止定位
                    locationClient.stopLocation();
                    locationClient.unRegisterLocationListener(this);
                }
            }
        });

        // 启动定位
        locationClient.startLocation();
    }



    /**
     * 开始定位
     *
     * @param isLooper 是否循环定位
     */
    public void startLocation(final Boolean isLooper, final OnLocationListener onLocationListener) {
        if (!isLooper) {
            locationOption.setOnceLocation(true);
        } else {
            locationOption.setOnceLocation(false);
        }
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (null != location) {
                    if (location.getErrorCode() == 0) {
                        //定位成功
                        onLocationListener.locationSuccess(location);
                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + location.getErrorCode() + ", errInfo:"
                                + location.getErrorInfo());
                        //定位失败
                        onLocationListener.locationError();
                    }
                } else {
                    //定位失败
                    onLocationListener.locationError();
                }
                if (!isLooper) {
                    // 停止定位
                    locationClient.stopLocation();
                    locationClient.unRegisterLocationListener(this);
                }
            }
        });

        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        // 停止定位
        if (locationClient != null) {
            locationClient.stopLocation();
        }
    }

    /**
     * 销毁定位
     */
    public void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

}
