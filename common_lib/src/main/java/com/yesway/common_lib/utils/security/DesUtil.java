package com.yesway.common_lib.utils.security;

import com.yesway.common_lib.utils.StringUtils;
import com.yesway.common_lib.utils.security.base64.Base64Factory;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * @Description: DES加密工具类
 * @Date: 2018/3/9
 * @Version: V1.0.0
 * @Update: 更新说明
 */
public final class DesUtil {

    /**
     * 加密算法
     */
    private static final String ALGORITHM = "DES";

    /**
     * 填充模式
     */
    private static final String DEFAULT_MODEL_FILL = "DES/CBC/PKCS7Padding";

    /**
     * ECB模式不需要偏移量
     */
    private static final String MODEL_ECB = "ECB";

    /**
     * NoPadding无填充时要代码处理
     */
    private static final String NO_PADDING = "NoPadding";

    /**
     * 默认key8 byte 64 bit
     * 不建议使用
     */
    @Deprecated
    private static final String DEFAULT_KEY = "60d168a7";

    /**
     * 8长度密码 8byte 64bit
     */
    private static final int KEY_SIZE_8 = 8;

    /**
     * 初始化向量8byte 64 bit
     */
    private static final String DEFAULT_IV = "d0e6bc3d";

    /**
     * 8长度偏移量 8byte 64bit
     */
    private static final int IV_SIZE_8 = 8;

    /**
     * 是否初始化标志
     */
    private static boolean initialized = false;


    private DesUtil() {

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
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(String str, String key, String modelFill, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (StringUtils.isEmpty(key)) {
            key = DEFAULT_KEY;
        }
        if (key.length() != KEY_SIZE_8) {
            return null;
        }
        if (StringUtils.isEmpty(modelFill)) {
            modelFill = DEFAULT_MODEL_FILL;
        }
        if (StringUtils.isEmpty(iv)) {
            iv = DEFAULT_IV;
        }
        if (iv.length() != IV_SIZE_8) {
            return null;
        }

        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secureKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance(modelFill);
        String splitStr = "/";
        String[] modelFills = modelFill.split(splitStr);
        //非ECB模式需要偏移量
        if (modelFills[1].equals(MODEL_ECB)) {
            cipher.init(Cipher.ENCRYPT_MODE, secureKey);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, getIvParameterSpec(iv));
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
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(String str, String key, String modelFill) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
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
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(String str, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
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
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encrypt(str, null);
    }

    /**
     * 解密
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
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String str, String key, String modelFill, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (StringUtils.isEmpty(key)) {
            key = DEFAULT_KEY;
        }
        if (key.length() != KEY_SIZE_8) {
            return null;
        }
        if (StringUtils.isEmpty(modelFill)) {
            modelFill = DEFAULT_MODEL_FILL;
        }
        if (StringUtils.isEmpty(iv)) {
            iv = DEFAULT_IV;
        }
        if (iv.length() != IV_SIZE_8) {
            return null;
        }

        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secureKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance(modelFill);
        String splitStr = "/";
        //非ECB模式需要偏移量
        if (modelFill.split(splitStr)[1].equals(MODEL_ECB)) {
            cipher.init(Cipher.DECRYPT_MODE, secureKey);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, secureKey, getIvParameterSpec(iv));
        }
        return new String(cipher.doFinal(Base64Factory.getInstance().getBase64().decodeData(str)));
    }

    /**
     * 解密
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
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String str, String key, String modelFill) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        return decrypt(str, key, modelFill, "");
    }

    /**
     * 解密
     *
     * @param str 加密字符串
     * @param key 密码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String str, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        return decrypt(str, key, null);
    }

    /**
     * 解密
     *
     * @param str 加密字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        return decrypt(str, null);
    }

    /**
     * 获取初始化向量
     *
     * @return
     */
    public static IvParameterSpec getIvParameterSpec(String iv) {
        return new IvParameterSpec(iv.getBytes());
    }


}
