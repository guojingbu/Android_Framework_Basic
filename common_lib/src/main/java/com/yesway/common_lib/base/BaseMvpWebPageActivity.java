package com.yesway.common_lib.base;

import android.os.Bundle;

/**
 * author : guojingbu
 * date   : 2020/9/25
 * desc   :
 */
public abstract class BaseMvpWebPageActivity<V, T extends BasePresenter<V>> extends BaseWebPageActivity {
    private T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
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
     * 初始化 presenter
     *
     * @return presenter对象
     */
    public abstract T initPresenter();
}
