package com.yesway.common_lib.router;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @Description 车辆模块服务提供者
 * @Author guojingbu
 * @Date 2019/9/20
 */
public interface IVehicleProvider extends IProvider {
    Fragment getMainVehicleFragment();
}
