package com.yesway.common_lib.base;

import android.os.Bundle;

/**
 * mvp公共的Fragment基类
 */
public abstract class BaseMvpFragment<V, T extends BasePresenter<V>> extends BaseFragment {
    public static final String TAG = "YESWAY";
    /**
     * presenter泛型
     */
    public T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
    }

    /**
     * presenter释放view引用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 初始化 presenter
     *
     * @return presenter对象
     */
    public abstract T initPresenter();
}
