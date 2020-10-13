package com.yesway.module_vehicle.view;

import android.view.View;

import com.yesway.common_lib.base.BaseMvpFragment;
import com.yesway.module_vehicle.R;
import com.yesway.module_vehicle.contract.VehicleMainContract;
import com.yesway.module_vehicle.presenter.VehicleMainPresenter;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
public class VehicleMainFragment extends BaseMvpFragment<VehicleMainContract.View, VehicleMainPresenter> implements VehicleMainContract.View {

    public final static VehicleMainFragment newInstance() {
        return new VehicleMainFragment();
    }

    @Override
    public VehicleMainPresenter initPresenter() {
        return new VehicleMainPresenter(mActivity);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public int bindView() {
        return R.layout.fragment_vehicle_main;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.title_vehicle;
    }
    @Override
    public boolean enableToolBar() {
        return true;
    }
    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
