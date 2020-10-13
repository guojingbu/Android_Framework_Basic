package com.yesway.module_main.presenter;

import android.content.Context;

import com.yesway.common_lib.base.BasePresenter;
import com.yesway.module_main.contract.LoginContract;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    public LoginPresenter(Context context) {
        super(context);
    }
}
