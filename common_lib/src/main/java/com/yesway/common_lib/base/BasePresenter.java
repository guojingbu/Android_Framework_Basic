package com.yesway.common_lib.base;

import android.content.Context;

import com.trello.rxlifecycle3.components.support.RxFragment;


public abstract class BasePresenter<V> {
    protected Context mContext;
    protected RxFragment mFragment;
    protected V mView;

    public BasePresenter(Context context) {
        mContext = context;
    }

    public BasePresenter(RxFragment fragment) {
        mFragment = fragment;
    }

    public void attach(V view) {
        attachView(view);
    }

    public void detach() {
        detachView();
    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }
}


