package com.yesway.api_lib.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * author : guojingbu
 * date   : 2020/5/26
 * desc   :登录服务提供
 */
public interface ILoginProvider extends IProvider {
    /**
     * 退出登录
     */
    void logout(Context context);
}
