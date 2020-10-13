package com.yesway.share_lib.config;

/**
 * Created by 95190 on 2018/4/26.
 */

public class PlatformWeiXin {

    public String appId;
    public String signMd5;

    public PlatformWeiXin(String appId, String signMd5) {
        this.appId = appId;
        this.signMd5 = signMd5;
    }

    public String getS() {
        return appId;
    }

    public void setS(String appId) {
        this.appId = appId;
    }

    public String getS1() {
        return signMd5;
    }

    public void setS1(String signMd5) {
        this.signMd5 = signMd5;
    }
}
