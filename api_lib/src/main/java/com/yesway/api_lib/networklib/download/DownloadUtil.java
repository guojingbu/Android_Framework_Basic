package com.yesway.api_lib.networklib.download;

public class DownloadUtil {
    /**
     * 是否正在下载
     */
    public static boolean isDownloading = false;
    private static DownloadUtil mInstance = new DownloadUtil();
    INetManager mNetManager = new OkHttpNetManager();

    public void setNetManager(INetManager manager) {
        mNetManager = manager;
    }

    private DownloadUtil() {

    }

    public INetManager getNetManager() {
        return mNetManager;
    }

    public static DownloadUtil getInstance() {
        return mInstance;
    }
}
