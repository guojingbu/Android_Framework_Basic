package com.yesway.common_lib.widget;

/**
 * 对某种情况处理的策略接口
 */

public interface IStrategy {
    //显示断网
    int VIEW_WIFIFAILUER = 2;
    //显示数据加载失败
    int VIEW_LOADFAILURE = 3;
    //无数据
    int NODATA_LOADFAILURE = 4;

    void operate();
}
