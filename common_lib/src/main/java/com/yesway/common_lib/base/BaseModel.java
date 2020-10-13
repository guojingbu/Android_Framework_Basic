package com.yesway.common_lib.base;

import android.content.Context;

import com.trello.rxlifecycle3.components.support.RxFragment;


public class BaseModel {
    protected Context mContext;
    protected RxFragment mFragment;
    public BaseModel(Context context) {
        mContext = context;
    }

    public BaseModel(RxFragment fragment) {
        mContext = fragment.getContext();
        this.mFragment = fragment;
    }


    public Context getContext() {
        return mContext;
    }
}
