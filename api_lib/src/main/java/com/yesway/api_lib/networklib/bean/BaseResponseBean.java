package com.yesway.api_lib.networklib.bean;

import com.yesway.api_lib.base.ResponseHeader;

/**
 * 基础response返回头
 * Created by weiz on 2018/6/13.
 */

public class BaseResponseBean {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int code;
    private String des;
    private String msg;

}
