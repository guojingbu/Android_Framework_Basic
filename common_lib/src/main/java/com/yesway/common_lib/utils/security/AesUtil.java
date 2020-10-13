package com.yesway.common_lib.utils.security;

import com.yesway.common_lib.utils.StringUtils;
import com.yesway.common_lib.utils.security.base64.Base64Factory;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * @Description:AES对称加密工具类 256位加密
 * @Date: 2018/03/07
 * @Version: V1.0.0
 * @Update: 更新说明
 */
public final class AesUtil {

    /**
     * 加密算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 默认模式/填充
     */
    private static final String DEFAULT_MODEL_FILL = "AES/CBC/PKCS7Padding";

    /**
     * ECB模式不需要偏移量
     */
    private static final String MODEL_ECB = "ECB";

    /**
     * NoPadding无填充时要代码处理
     */
    private static final String NO_PADDING = "NoPadding";

    /**
     * 默认key 16/24/32byte 128/192/256 bits
     * 不建议使用
     */
    @Deprecated
    private static final String DEFAULT_KEY = "c00a1b1d7e33036e2b5249507148asdf";

    /**
     * 16长度密码 16byte 128bit
     */
    private static final int KEY_SIZE_16 = 16;

    /**
     * 24长度密码 24byte 192bit
     */
    private static final int KEY_SIZE_24 = 24;

    /**
     * 32长度密码 32byte 256bit
     */
    private static final int KEY_SIZE_32 = 32;

    /**
     * 初始化向量16长度
     */
    private static final String DEFAULT_IV = "17b56901d7e60575";

    /**
     * 16 偏移量 32byte 128位
     */
    private static final int IV_SIZE_16 = 16;

    /**
     * 是否初始化标志
     */
    private static boolean initialized = false;


    private AesUtil() {

    }


    /**
     * 加密
     *
     * @param str       加密字符串
     * @param key       密码
     * @param modelFill 模式及填充
     * @param iv        偏移量
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(String str, String key, String modelFill, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (StringUtils.isEmpty(key)) {
            key = DEFAULT_KEY;
        }
        if (key.length() != KEY_SIZE_16 && key.length() != KEY_SIZE_24 && key.length() != KEY_SIZE_32) {
            return null;
        }
        if (StringUtils.isEmpty(modelFill)) {
            modelFill = DEFAULT_MODEL_FILL;
        }
        if (StringUtils.isEmpty(iv)) {
            iv = DEFAULT_IV;
        }
        if (iv.length() != IV_SIZE_16) {
            return null;
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(modelFill);
        String splitStr = "/";
        String[] modelFills = modelFill.split(splitStr);
        //非ECB模式需要偏移量
        if (modelFills[1].equals(MODEL_ECB)) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, getIvParameterSpec(iv));
        }
        byte[] bytes = str.getBytes();
        //如果是NoPadding需要补位
        if (modelFills[2].equals(NO_PADDING)) {
            int blockSize = cipher.getBlockSize();
            int plaintextLength = str.getBytes().length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
                byte[] plaintext = new byte[plaintextLength];
                System.arraycopy(bytes, 0, plaintext, 0, bytes.length);
                return Base64Factory.getInstance().getBase64().encodeString(cipher.doFinal(plaintext));
            }
        }
        return Base64Factory.getInstance().getBase64().encodeString(cipher.doFinal(bytes));
    }

    /**
     * 加密
     *
     * @param str       加密字符串
     * @param key       密码
     * @param modelFill 模式及填充
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(String str, String key, String modelFill) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encrypt(str, key, modelFill, "");
    }

    /**
     * 加密
     *
     * @param str 加密字符串
     * @param key 密码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(String str, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encrypt(str, key, null);
    }

    /**
     * 加密
     *
     * @param str 加密字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encrypt(str, null);
    }

    /**
     * 解密
     *
     * @param str       解密字符串
     * @param key       密码
     * @param modelFill 模式及填充
     * @param iv        偏移量
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String str, String key, String modelFill, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (StringUtils.isEmpty(key)) {
            key = DEFAULT_KEY;
        }
        if (key.length() != KEY_SIZE_16 && key.length() != KEY_SIZE_24 && key.length() != KEY_SIZE_32) {
            return null;
        }
        if (StringUtils.isEmpty(modelFill)) {
            modelFill = DEFAULT_MODEL_FILL;
        }
        if (StringUtils.isEmpty(iv)) {
            iv = DEFAULT_IV;
        }
        if (iv.length() != IV_SIZE_16) {
            return null;
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(modelFill);
        String splitStr = "/";
        if (modelFill.split(splitStr)[1].equals(MODEL_ECB)) {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, getIvParameterSpec(iv));
        }
        byte[] bytes = cipher.doFinal(Base64Factory.getInstance().getBase64().decodeData(str));
        return new String(bytes);
    }

    /**
     * 解密
     *
     * @param str       解密字符串
     * @param key       密码
     * @param modelFill 模式及填充
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String str, String key, String modelFill) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return decrypt(str, key, modelFill, "");
    }

    /**
     * 解密
     *
     * @param str 解密字符串
     * @param key 密码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchProviderException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String str, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return decrypt(str, key, null);
    }

    /**
     * 解密
     *
     * @param str 解密字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchProviderException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return decrypt(str, null);
    }

    /**
     * 获取初始化向量
     *
     * @return
     */
    public static IvParameterSpec getIvParameterSpec(String iv) {
        if (StringUtils.isEmpty(iv)) {
            iv = DEFAULT_IV;
        }
        return new IvParameterSpec(iv.getBytes());
    }


}