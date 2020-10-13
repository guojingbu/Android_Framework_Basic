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
package com.yesway.paysdk.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密
 *
 * @author zhangke
 */
public class AESUtils {
	
	public static void main(String[] args) {
		
		try {
			System.out.println(AESEncode("123", "1111"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加密
	 */
	public static String AESEncode(String sKey, String sSrc) throws Exception {
		byte[] raw = sKey.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

		// "算法/模式/补码方式"
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		byte[] encrypted = cipher.doFinal(sSrc.getBytes());

		// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		// return new String(Base64.encode(encrypted, Base64.NO_WRAP));
		return new String(Base64Utils.encode(encrypted));
	}

	/**
	 * 解密
	 */
	public static String AESDncode(String sKey, String sSrc) throws Exception {
		try {
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			// byte[] encrypted1 = Base64.decode(sSrc,Base64.NO_WRAP);
			byte[] encrypted1 = Base64Utils.decode(sSrc);

			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;

		} catch (Exception ex) {
			return null;
		}
	}
}
