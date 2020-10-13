package com.yesway.module_me.view;

import android.view.View;

import com.yesway.common_lib.base.BaseMvpFragment;
import com.yesway.module_me.R;
import com.yesway.module_me.contract.MeMainContract;
import com.yesway.module_me.presenter.MeMainPresenter;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
public class MeMainFragment extends BaseMvpFragment<MeMainContract.View, MeMainPresenter> implements MeMainContract.View {

    public final static MeMainFragment newInstance() {
        return new MeMainFragment();
    }

    @Override
    public MeMainPresenter initPresenter() {
        return new MeMainPresenter(mActivity);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public int bindView() {
        return R.layout.fragment_me_main;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.title_me;
    }

    @Override
    public void initData() {

    }
    @Override
    public boolean enableToolBar() {
        return true;
    }

    @Override
    public void initListener() {

    }
}
