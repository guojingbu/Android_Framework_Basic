package com.yesway.api_lib.base;

/**
 * author : guojingbu
 * date   : 2020/5/25
 * desc   :请求协议头
 */
public class MessageHeader {
    /**
     * 用户id
     */
    private String userid;
    /**
     * 接口访问令牌
     */
    private String token;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * App标识
     */
    private String appkey;
    /**
     * 版本名称
     */
    private String version;
    /**
     * 版本号
     */
    private String buildnumber;
    /**
     * 设备类型
     */
    private String devicetype;
    private String environment;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuildnumber() {
        return buildnumber;
    }

    public void setBuildnumber(String buildnumber) {
        this.buildnumber = buildnumber;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }
}
