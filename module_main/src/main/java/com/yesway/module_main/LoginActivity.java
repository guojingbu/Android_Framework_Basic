package com.yesway.module_main;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yesway.common_lib.base.BaseMvpActivity;
import com.yesway.common_lib.manager.JpushManager;
import com.yesway.module_main.contract.LoginContract;
import com.yesway.module_main.presenter.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginPresenter> {

    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startMainActivity(LoginActivity.this);
                finish();
            }
        });
    }

    @Override
    public int bindView() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public boolean enableToolbar() {
        return false;
    }
}
