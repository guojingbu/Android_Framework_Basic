package com.yesway.api_lib.base;

/**
 * author : guojingbu
 * date   : 2020/5/25
 * desc   : 请求通用类
 */
public class RequestBean<T> {
    private T data;
    private MessageHeader messageHeader;

    public RequestBean(MessageHeader messageHeader,T data) {
        this.data = data;
        this.messageHeader=messageHeader;
    }

}
