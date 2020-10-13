package com.yesway.api_lib.config;

/**
 * author : guojingbu
 * date   : 2020/5/26
 * desc   :
 */
public interface OrderStatus {
    /**
     * 待确认
     */
    int ORDER_UNCONFIRM_STATUS = 0;
    /**
     * 待完成
     */
    int ORDER_NOTCOMPLETE_STATUS = 1;
    /**
     * 已取消
     */
    int ORDER_CANCLED_STATUS = 2;
    /**
     * 已完成
     */
    int ORDER_COMPLETED_STATUS = 3;
}
