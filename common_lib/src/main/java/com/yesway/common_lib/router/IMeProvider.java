package com.yesway.common_lib.router;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @Description 我的模块服务提供者
 * @Author guojingbu
 * @Date 2019/9/20
 */
public interface IMeProvider extends IProvider {
    Fragment getMainMeFragment();
}
