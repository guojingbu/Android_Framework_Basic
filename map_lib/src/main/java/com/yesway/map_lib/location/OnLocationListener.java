package com.yesway.map_lib.location;

import com.amap.api.location.AMapLocation;

/**
 * created by @author guojingbu on ${DATA}
 * 定位回调接口类
 */

public interface OnLocationListener {
    /**
     * 定位成功回调接口
     *
     * @param location 定位成功后返回的位置信息
     */
    void locationSuccess(AMapLocation location);

    /**
     * 定位失败回调接口
     */
    void locationError();

}
