package com.yesway.common_lib.utils.security.base64;

/**
 * @Description:将String进行base64加密解密
 * @Date: 2018/03/07
 * @Version: V1.0.0
 * @Update: 更新说明
 */
public interface IBase64Util {

    /**
     * 对输入的字符串进行Base64加密。
     *
     * @param code
     * @return 加密后的byte。
     */
    byte[] encodeData(String code);

    /**
     * 对输入的字符串进行Base64加密。
     *
     * @param code
     * @return 加密后的byte。
     */
    byte[] encodeData(byte[] code);

    /**
     * 对输入的byte进行Base64加密。
     *
     * @param code
     * @return 加密后的字符串。
     */
    String encodeString(String code);

    /**
     * 对输入的byte进行Base64加密。
     *
     * @param code
     * @return 加密后的字符串。
     */
    String encodeString(byte[] code);

    /**
     * 对输入的字符串进行Base64解密。
     *
     * @param code
     * @return 解密后的byte。
     */
    byte[] decodeData(String code);

    /**
     * 对输入的字符串进行Base64解密。
     *
     * @param code
     * @return 解密后的字符串。
     */
    byte[] decodeData(byte[] code);

    /**
     * 对输入的字符串进行Base64解密。
     *
     * @param code
     * @return 解密后的字符串。
     */
    String decodeString(String code);

    /**
     * 对输入的字符串进行Base64解密。
     *
     * @param code
     * @return 解密后的字符串。
     */
    String decodeString(byte[] code);

}
