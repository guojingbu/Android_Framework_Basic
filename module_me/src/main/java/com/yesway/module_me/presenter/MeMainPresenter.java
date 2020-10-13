package com.yesway.module_me.presenter;

import android.content.Context;

import com.yesway.common_lib.base.BasePresenter;
import com.yesway.module_me.contract.MeMainContract;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
public class MeMainPresenter extends BasePresenter<MeMainContract.View> implements MeMainContract.Presenter {
    public MeMainPresenter(Context context) {
        super(context);
    }
}
