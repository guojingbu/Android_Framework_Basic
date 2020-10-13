package com.yesway.share_lib.config;

import android.content.Context;


/**
 * Created by 95190 on 2018/4/26.
 */

public class ShareConfig {

    public Context context;
    public PlatformQQZone mQQZone ;
    public PlatformSinaWeibo mSinaWeibo ;
    public PlatformWeiXin mWeiXin ;
    public UmConfig mConfig ;
    public Boolean isdebug = false;

    public ShareConfig() {

    }

    //配置类的builder
    public static class Builder {
        private Context mContext;
        private PlatformQQZone qqZone= null;
        private PlatformSinaWeibo sinaWeibo= null;
        private PlatformWeiXin weiXin= null;
        private UmConfig config= null;
        private Boolean isDebug = false;


        public Builder setDebug(Boolean debug) {
            this.isDebug = debug;
            return this ;
        }

        public Builder setConfig(UmConfig umConfig) {
            this.config = umConfig;
            return this;
        }

        public Builder setPlatformWeiXin(PlatformWeiXin platformweixin) {
            this.weiXin = platformweixin;
            return this;
        }


        public Builder setPlatformSinaWeibo(PlatformSinaWeibo platformsinaweibo) {
            this.sinaWeibo = platformsinaweibo;
            return this;
        }

        public Builder setPlatformQQZone(PlatformQQZone platformqqzone) {
            this.qqZone = platformqqzone;
            return this;
        }

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }


        void applyConfig(ShareConfig shareConfig) {
            shareConfig.context = this.mContext;
            shareConfig.mQQZone = this.qqZone;
            shareConfig.mSinaWeibo = this.sinaWeibo;
            shareConfig.mWeiXin = this.weiXin;
            shareConfig.mConfig = this.config;
            shareConfig.isdebug = this.isDebug;
        }

        public ShareConfig creatConfig() {
            ShareConfig shareConfig = new ShareConfig();
            applyConfig(shareConfig);
            return shareConfig;
        }
    }

}
