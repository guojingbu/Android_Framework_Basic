package com.yesway.api_lib.networklib.download;

import java.io.File;

public interface DownloadCallBack {
    void onProgress(int currentLength);

    void onSuccess(File file);

    void onFailure(Throwable throwable);
}
