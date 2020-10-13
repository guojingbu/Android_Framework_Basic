package com.yesway.common_lib.eventbus;

/**
 * Event事件类
 */
public class Event<T> {
    private int code;
    private T data;

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }
    public Event(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

