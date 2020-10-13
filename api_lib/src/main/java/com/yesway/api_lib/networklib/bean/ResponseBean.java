package com.yesway.api_lib.networklib.bean;

/**
 * response响应公共类封装
 * Created by weiz on 2018/6/13.
 */

public class ResponseBean<T> extends BaseResponseBean{
    private T data;

    public T getResult() {
        return data;
    }

    public void setResult(T data) {
        this.data = data;
    }

}
