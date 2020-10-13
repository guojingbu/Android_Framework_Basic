/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarPlus Project
 *                http://www.yesway.cn/
 * 创建时间：2017年3月29日
 * 内容说明：
 * 
 * 编号                日期                     担当者             内容                  
 * -------------------------------------------------
 *
 * -------------------------------------------------- */
package com.yesway.paysdk.pay;

import android.app.Activity;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yesway.paysdk.PayConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 微信支付
 *
 * @author zhangke
 */
class WxPay extends AbstractPay<BaseResp> {

    /**
     * 启动微信支付
     *
     * @param context
     * @param orderinfo
     */
    @Override
    public void onPay(Activity context, String orderinfo, OnPayResultListener listener) {
        this.mOnPayResultListener = listener;

        try {
            /*
             * 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
			 */
            IWXAPI api = WXAPIFactory.createWXAPI(context, PayConfig.WX_APPID);
            api.registerApp(PayConfig.WX_APPID);

            if (!api.isWXAppInstalled()) {
                Toast.makeText(context, "没有安装微信客户端", Toast.LENGTH_SHORT).show();
                return;
            } else if (api.getWXAppSupportAPI()< Build.PAY_SUPPORTED_SDK_INT) {
                Toast.makeText(context, "该版本微信客户端不支持微信支付", Toast.LENGTH_SHORT).show();
                return;
            }

            JSONObject json = new JSONObject(orderinfo);
            PayReq req = new PayReq();
            req.appId = json.getString("appid");
            req.partnerId = json.getString("partnerid");
            req.prepayId = json.getString("prepayid");
            req.nonceStr = json.getString("noncestr");
            req.timeStamp = json.getString("timestamp");
            req.packageValue = json.getString("package");
            req.sign = json.getString("sign");
            // req.extData = "";

            api.sendReq(req);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理支付结果
     *
     * @param result
     */
    @Override
    public void onResult(BaseResp result) {
        String response = JSON.toJSONString(result);
        int errCode = result.errCode;

        int code;
        if (errCode == 0) {
            // 成功
            code = PAY_RESULT_SUCCESS;
        } else if (errCode == -2) {
            // 取消
            code = PAY_RESULT_CANCEL;
        } else {
            // 异常
            code = PAY_RESULT_ERROR;
        }

        onResult(code, response);
        instance = null;
    }

    private static WxPay instance;

    private WxPay() {

    }

    public static WxPay getInstace() {
        if (instance == null) {
            instance = new WxPay();
        }
        return instance;
    }
}
