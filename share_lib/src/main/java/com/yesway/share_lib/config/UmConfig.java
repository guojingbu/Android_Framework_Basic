package com.yesway.share_lib.config;

/**
 * Created by 95190 on 2018/4/26.
 */

public class UmConfig {
    /**
     * U-meng平台的appkey
     */
    public String appKey;
    /**
     * 渠道 如果在manifest中配置了就传空字符串或null
     */
    public String channel;
    /**
     * 推送秘钥不用友盟推送可以传空字符串或null
     */
    public String pushSecret;
    /**
     * she被类型
     */
    public int devicetype;

    public UmConfig(String appKey, String channel,String pushSecret) {
        this.appKey = appKey;
        this.channel = channel;
        this.pushSecret = pushSecret;
    }

    public String getAppkey() {
        return appKey;
    }

    public void setAppkey(String appKey) {
        this.appKey = appKey;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPushSecret() {
        return pushSecret;
    }

    public void setPushSecret(String pushSecret) {
        this.pushSecret = pushSecret;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }
}
