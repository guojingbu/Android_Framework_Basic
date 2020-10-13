package com.yesway.api_lib.networklib.rdownload;

public interface DownFileCallback {

    void onSuccess(String path);

    void onFail(String msg);

    void onProgress(long totalSize, long downSize);
}
