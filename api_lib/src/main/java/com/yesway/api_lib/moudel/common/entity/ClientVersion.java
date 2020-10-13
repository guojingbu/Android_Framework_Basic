package com.yesway.api_lib.moudel.common.entity;

/**
 * author : guojingbu
 * date   : 2020/5/29
 * desc   :
 */
public class ClientVersion {

    /**
     * appId : 3
     * appKey : androida-3e1d-45c0-9353-46a12984f0d4
     * appName : android
     * version : 2.0.143
     * versionName : 2.0.143
     * introduction : 1、预约维保服务升级，新增专属SA，全面优化“专属”服务顾问体验，让您尊享无需等待的保养体验；
     * 2、新增一键在线救援，掌控实时救援状态，守护随时可见；
     * 3、更多优化，等您下载体验。
     * lowestVersion : 0
     * downloadUrl : http://acura-app-test.oss-cn-beijing.aliyuncs.com/app/ACURA e-BUTLER-2.0.150-29(3).apk
     * fileSize : 10
     * devicetype : android
     * environment : 3
     * buildnumber : 29
     * forcedUpdate : 0
     * updateTime : 20190805165458
     * addTime : 20170907155521
     */
    private String appId;
    /**
     * app的唯一标识
     */
    private String appKey;
    /**
     * app名称
     */
    private String appName;
    /**
     * 版本名称
     */
    private String version;
    /**
     * 版本名称
     */
    private String versionName;
    /**
     * 版本更新描述
     */
    private String introduction;
    /**
     * 最低版本
     */
    private int lowestVersion;
    /**
     * app下载链接
     */
    private String downloadUrl;
    /**
     * apk文件大小
     */
    private String fileSize;
    /**
     * 设备类型
     */
    private String devicetype;
    /**
     * 设备环境
     */
    private int environment;
    /**
     * 更新版本号
     */
    private int buildnumber;
    /**
     * 是否强制更新0：不强制 1：强制
     */
    private int forcedUpdate;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 添加时间
     */
    private String addTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getLowestVersion() {
        return lowestVersion;
    }

    public void setLowestVersion(int lowestVersion) {
        this.lowestVersion = lowestVersion;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public int getEnvironment() {
        return environment;
    }

    public void setEnvironment(int environment) {
        this.environment = environment;
    }

    public int getBuildnumber() {
        return buildnumber;
    }

    public void setBuildnumber(int buildnumber) {
        this.buildnumber = buildnumber;
    }

    public int getForcedUpdate() {
        return forcedUpdate;
    }

    public void setForcedUpdate(int forcedUpdate) {
        this.forcedUpdate = forcedUpdate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
