package com.yesway.api_lib.networklib.download;

import java.io.File;

public interface INetManager {

    void downLoad(String url, File targetFile, DownloadCallBack downloadListener, Object tag);

    void cancel(Object tag);
}
