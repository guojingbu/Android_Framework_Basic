package com.yesway.module_vehicle.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yesway.common_lib.router.IVehicleProvider;
import com.yesway.common_lib.router.RouterPath;
import com.yesway.module_vehicle.view.VehicleMainFragment;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
@Route(path = RouterPath.VEHICLE_PROVIDER_PATH)
public class VehicleProvider implements IVehicleProvider {
    @Override
    public Fragment getMainVehicleFragment() {
        return VehicleMainFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
