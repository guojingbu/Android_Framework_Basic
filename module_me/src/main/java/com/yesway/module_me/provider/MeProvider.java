package com.yesway.module_me.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yesway.common_lib.router.IMeProvider;
import com.yesway.common_lib.router.RouterPath;
import com.yesway.module_me.view.MeMainFragment;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
@Route(path = RouterPath.ME_PROVIDER_PATH)
public class MeProvider implements IMeProvider {
    @Override
    public Fragment getMainMeFragment() {
        return MeMainFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
