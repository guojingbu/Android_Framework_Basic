/*-------------------------------------------------- * Copyright (C) 2015 The Android Y-CarPlus Project *                http://www.yesway.cn/ * 创建时间：2017年3月29日 * 内容说明： *  * 编号                日期                     担当者             内容                   * ------------------------------------------------- * * -------------------------------------------------- */package com.yesway.paysdk.pay;import android.text.TextUtils;import java.util.Map;/** * alipay支付结果说明 * * @author zhangke */class AliPayResult {	/**	 * 支付结果码</br>	 * </br>	 * 	 * 9000 :订单支付成功 </br>	 * 8000 :正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态 </br>	 * 4000 :订单支付失败</br>	 * 5000 :重复请求 </br>	 * 6001 :用户中途取消 </br>	 * 6002 :网络连接出错 </br>	 * 6004 :支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态 </br>	 * 其它 :其它支付错误</br>	 * 	 */	private String resultStatus;	/**	 * 返回数据resultStatus为9000的情况下，解析result结果，提取验证签名的相关核心数据	 */	private String result;	/**	 * 描述信息	 */	private String memo;	public AliPayResult(Map<String, String> rawResult) {		if (rawResult == null) {			return;		}		for (String key : rawResult.keySet()) {			if (TextUtils.equals(key, "resultStatus")) {				resultStatus = rawResult.get(key);			} else if (TextUtils.equals(key, "result")) {				result = rawResult.get(key);			} else if (TextUtils.equals(key, "memo")) {				memo = rawResult.get(key);			}		}	}	public String getResultStatus() {		return resultStatus;	}	public String getMemo() {		return memo;	}	public String getResult() {		return result;	}}