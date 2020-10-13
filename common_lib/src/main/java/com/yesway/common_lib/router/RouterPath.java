package com.yesway.common_lib.router;

/**
 * @Description 所有路由路径
 * @Author guojingbu
 * @Date 2019/9/20
 */
public interface RouterPath {
    /**
     * 主页模块服务提供者路径
     * {@link IHomeProvider}
     */
    String HOME_PROVIDER_PATH = "/home/provider";
    /**
     * 主模块服务提供者路径
     * {@link IMainProvider}
     */
    String MAIN_PROVIDER_PATH = "/main/provider";
    /**
     * 我的模块服务提供者路径
     * {@link IMeProvider}
     */
    String ME_PROVIDER_PATH = "/me/provider";
    /**
     * 消息模块服务提供者路径
     * {@link IMessageProvider}
     */
    String MESSAGE_PROVIDER_PATH = "/message/provider";
    /**
     * 车辆模块服务提供者路径
     * {@link IVehicleProvider}
     */
    String VEHICLE_PROVIDER_PATH = "/vehicle/provider";
}
