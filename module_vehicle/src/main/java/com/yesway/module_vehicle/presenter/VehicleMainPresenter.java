package com.yesway.module_vehicle.presenter;

import android.content.Context;

import com.yesway.common_lib.base.BasePresenter;
import com.yesway.module_vehicle.contract.VehicleMainContract;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
public class VehicleMainPresenter extends BasePresenter<VehicleMainContract.View> implements VehicleMainContract.Presenter {
    public VehicleMainPresenter(Context context) {
        super(context);
    }
}
