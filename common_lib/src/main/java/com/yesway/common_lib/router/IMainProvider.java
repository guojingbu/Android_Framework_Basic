package com.yesway.common_lib.router;

import android.app.Activity;

import com.alibaba.android.arouter.facade.template.IProvider;
/**
 * @Description 主模块服务提供者
 * @Author guojingbu
 * @Date 2019/9/20
 */
public interface IMainProvider extends IProvider {
    /**
     * 跳转到登录页面
     */
    void startLoginActivity(Activity context);

}
