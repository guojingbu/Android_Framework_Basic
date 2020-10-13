package com.yesway.api_lib.base;

public class CommonResponse extends ResponseHeader {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
