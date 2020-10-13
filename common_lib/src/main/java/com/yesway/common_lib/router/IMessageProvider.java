package com.yesway.common_lib.router;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @Description 消息模块服务提供者
 * @Author guojingbu
 * @Date 2019/9/20
 */
public interface IMessageProvider extends IProvider {
    Fragment getMainMessageFragment();
}
