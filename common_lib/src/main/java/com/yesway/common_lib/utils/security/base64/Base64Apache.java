package com.yesway.common_lib.utils.security.base64;


import android.util.Base64;

import com.yesway.common_lib.utils.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Description:基于Apache的Base64加密
 * @Date: 2018/03/07
 * @Version: V1.0.0
 * @Update: 更新说明
 */
class Base64Apache implements IBase64Util {

    /**
     * 基于Apache的Base64加密对象
     */
    private static volatile Base64Apache apacheBase64;
    /**
     * 采用UTF-8编码
     */
    private static final String UTF_8 = "UTF-8";

    /**
     * 将构造函数私有化
     */
    private Base64Apache() {
    }

    /**
     * 获得一个Base64Apache的单例对象。
     *
     * @return Base64Apache对象。
     */
    public static Base64Apache getInstance() {
        // 采用锁进行双重判断，确保对象是单例的
        if (apacheBase64 == null) {
            synchronized (Base64Apache.class) {
                if (apacheBase64 == null) {
                    apacheBase64 = new Base64Apache();
                }
            }
        }
        return apacheBase64;
    }

    /**
     * 对输入的字符串进行Base64加密。
     *
     * @param code 要加密的字符串。
     * @return 加密后的byte。
     */
    @Override
    public byte[] encodeData(String code) {
        if (code == null) {
            throw new NullPointerException("要加密的字符串不能为null。");
        }
        try {

            return Base64.encode(code.getBytes(UTF_8), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("byte转码时发生了异常。");
        }
    }

    /**
     * 对输入的字符串进行Base64加密。
     *
     * @param code 要加密的byte。
     * @return 加密后的byte。
     */
    @Override
    public byte[] encodeData(byte[] code) {
        if (code == null) {
            throw new NullPointerException("要加密的byte不能为null。");
        }
        return Base64.encode(code, Base64.NO_WRAP);
    }

    /**
     * 对输入的字符串进行Base64加密。
     *
     * @param code 要加密的字符串。
     * @return 加密后的字符串。
     */
    @Override
    public String encodeString(String code) {
        if (code == null) {
            throw new NullPointerException("要加密的字符串不能为null。");
        }
        try {
            return Base64.encodeToString(code.getBytes(UTF_8), Base64.NO_WRAP);

        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("byte转码时发生了异常。");
        }
    }

    /**
     * 对输入的byte进行Base64加密。
     *
     * @param code 要加密的字符串。
     * @return 加密后的字符串。
     */
    @Override
    public String encodeString(byte[] code) {
        if (code == null) {
            throw new NullPointerException("要加密的byte不能为null。");
        }
        return Base64.encodeToString(code, Base64.NO_WRAP);
    }

    /**
     * 对输入的字符串进行Base64解密。
     *
     * @param code 要解密的字符串。
     * @return 解密后的byte。
     */
    @Override
    public byte[] decodeData(String code) {
        byte[] bytes = new byte[0];
        try {
            if (!StringUtils.isEmpty(code)) {
                bytes = Base64.decode(code.getBytes(UTF_8), Base64.NO_WRAP);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 对输入的byte进行Base64解密。
     *
     * @param code 要解密的byte。
     * @return 解密后的byte。
     */
    @Override
    public byte[] decodeData(byte[] code) {
        if (code == null) {
            throw new NullPointerException("要加密的byte不能为null。");
        }
        return Base64.encode(code, Base64.NO_WRAP);
    }

    /**
     * 对输入的byte进行Base64解密。
     *
     * @param code 要解密的字符串。
     * @return 解密后的byte。
     */
    @Override
    public String decodeString(String code) {
        if (code == null) {
            throw new NullPointerException("要解密的字符串不能为null。");
        }
        try {
            return Base64.encodeToString(code.getBytes(UTF_8), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("byte转码时发生了异常。");
        }
    }

    /**
     * 对输入的byte进行Base64解密。
     *
     * @param code 要解密的byte。
     * @return 解密后的byte。
     */
    @Override
    public String decodeString(byte[] code) {
        if (code == null) {
            throw new NullPointerException("要解密的byte不能为null。");
        }
        return Base64.encodeToString(code, Base64.NO_WRAP);
    }

}
