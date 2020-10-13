package com.yesway.api_lib.moudel.main.entity;

/**
 * author : guojingbu
 * date   : 2020/5/25
 * desc   :
 */
public class LoginRequest {
    private String account;
    private String password;
    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
