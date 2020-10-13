package com.yesway.module_home.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yesway.common_lib.router.IHomeProvider;
import com.yesway.common_lib.router.RouterPath;
import com.yesway.module_home.view.HomeFragment;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
@Route(path = RouterPath.HOME_PROVIDER_PATH)
public class HomeProvider implements IHomeProvider {
    @Override
    public Fragment getMainHomeFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
