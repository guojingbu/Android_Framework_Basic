package com.yesway.webview_lib.jsbridage;

public interface WebViewJavascriptBridge {
    public void send(String data);

    public void send(String data, CallBackFunction responseCallback);

}

