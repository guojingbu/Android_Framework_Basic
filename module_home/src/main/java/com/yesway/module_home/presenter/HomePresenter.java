package com.yesway.module_home.presenter;

import android.content.Context;

import com.yesway.common_lib.base.BasePresenter;
import com.yesway.module_home.contract.HomeContract;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    public HomePresenter(Context context) {
        super(context);
    }
}
