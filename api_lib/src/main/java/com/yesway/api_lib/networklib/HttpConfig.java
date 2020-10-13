package com.yesway.api_lib.networklib;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.yesway.api_lib.base.MessageHeader;
import com.yesway.api_lib.constant.Constant;
import com.yesway.api_lib.manager.UserManager;
import com.yesway.api_lib.utils.VersionUtils;

public class HttpConfig {

    /**
     * 字符集的请求
     */
    public static final String PROTOCOL_CHARSET = "utf-8";
    /**
     * HTTP响应的内容类型
     */
    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    /**
     * 应用版本号
     */
    public static final String HEADER_BUILDNUMBER = "X-Build-Number";

	/**
	 * 应用唯一标识
	 */
	public static final String  HEADER_APPKEY = "appKey";

    public static final String DEVICE_SYSTEM_INFO = "X-Device-System-Info";
    public static final String DEVICE_TYPE = "X-Device-Type";

    /**
     * 鉴权令牌
     */
    public static final String HEADER_ACCESSTOKEN = "X-Access-Token";

    /**
     * 时间戳
     */
    public static final String HEADER_TIMESTAMP = "X-Time-Stamp";

    /**
     * 用户id
     */
    public static final String HEADER_USERID = "X-Customer-Id";
    /**
     * 语言切换码
     */
    public static final String HEADER_LANGUAGE_CODE = "X-Language-Code";
    /**
     * android :android ios: ios
     */
    public static final String HEADER_X_OS_TYPE = "X-Customer-Id";
    /**
     *  协议版本[1-9][0-9]\.[0-9]
     */
    public static final String HEADER_VERSION_NAME = "X-App-Version";
    /**
     * 内容类型的请求
     */
    public static final String PROTOCOL_CONTENT_TYPE = String.format(
            "application/json; charset=%s", PROTOCOL_CHARSET);

    public static String getHeaderAppkey() {
        return Build.BRAND + " " + Build.MODEL + "|Android" + Build.VERSION.RELEASE;
    }


    /**
     * 设备型号
     *
     * @return
     */
    public static String getDeviceType() {
        return Build.MODEL;
    }

    /**
     * 用户Id
     */
    public static String getUserId() {
        return UserManager.getUserId();
    }

    /**
     * 设备类型标识
     */

    public static String getOSType() {
        return "Android";
    }

    /**
     * 获取token
     */
    public static String getToken() {
        return UserManager.getToken();
    }

    /**
     * 获取appkey
     * @return
     */
    public static String getAppKey(){
        return Constant.APP_KEY;
    }

    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static MessageHeader buildMessageHeader(){
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setAppkey(getAppKey());
        messageHeader.setBuildnumber(getVersionCode(RetrofitManager.mContext)+"");
        messageHeader.setDevicetype(getOSType());
        messageHeader.setTimestamp(System.currentTimeMillis()+"");
        messageHeader.setToken(getToken());
        messageHeader.setUserid(getUserId());
        messageHeader.setVersion( VersionUtils.getAppVersionName(RetrofitManager.mContext));
        return messageHeader;

    }
}

