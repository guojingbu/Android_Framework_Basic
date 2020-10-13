package com.yesway.api_lib.moudel.common.response;

import com.yesway.api_lib.moudel.common.entity.ClientVersion;

/**
 * author : guojingbu
 * date   : 2020/5/29
 * desc   :
 */
public class CheckVersionResponse {
   private ClientVersion dealerClient;

    public ClientVersion getDealerClient() {
        return dealerClient;
    }

    public void setDealerClient(ClientVersion dealerClient) {
        this.dealerClient = dealerClient;
    }
}
