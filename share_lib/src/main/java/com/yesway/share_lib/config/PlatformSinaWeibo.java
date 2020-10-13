package com.yesway.share_lib.config;

/**
 * Created by 95190 on 2018/4/26.
 */

public class PlatformSinaWeibo {
    public String appKey;
    public String appSecret;
    public String callbackAddress;

    public PlatformSinaWeibo(String appKey, String appSecret, String callbackAddress) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.callbackAddress = callbackAddress;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCallbackAddress() {
        return callbackAddress;
    }

    public void setCallbackAddress(String callbackAddress) {
        this.callbackAddress = callbackAddress;
    }
}
