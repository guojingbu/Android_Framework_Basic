package com.yesway.common_lib.base;

import android.os.Bundle;

/**
 * 公共的activity基类
 */
public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends BaseActivity{
    public static final String TAG = "YESWAY";

    protected BaseMvpActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
        mActivity = this;
        super.onCreate(savedInstanceState);
    }

    /**
     * onDestroy presenter释放view引用, Activity的管理者移除此页面
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }

    }

    /**
     * presenter泛型
     */
    public T mPresenter;

    /**
     * 初始化 presenter
     *
     * @return presenter对象
     */
    public abstract T initPresenter();


}
