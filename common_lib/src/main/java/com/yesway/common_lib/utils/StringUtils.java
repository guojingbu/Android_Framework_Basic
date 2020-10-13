package com.yesway.common_lib.utils;

import android.text.TextUtils;
import android.util.Base64;

import com.yesway.api_lib.constant.Constant;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String类相关操作
 *
 * @author guojingbu
 */
public class StringUtils {

    /**
     * 将字符串用分隔符分割为字符串列表
     *
     * @param string    字符串
     * @param delimiter 分隔符
     * @return 分割后的字符串数组.
     */
    public static List<String> splitToStringList(String string, String delimiter) {
        List<String> stringList = new ArrayList<String>();
        if (!TextUtils.isEmpty(string)) {
            int length = string.length();
            int pos = 0;

            do {
                int end = string.indexOf(delimiter, pos);
                if (end == -1) {
                    end = length;
                }
                stringList.add(string.substring(pos, end));
                pos = end + 1;
            } while (pos < length);
        }
        return stringList;
    }

    /**
     * InputSteam 转换到 String，会把输入流关闭
     *
     * @param inputStream 输入流
     * @return String 如果有异常则返回null
     */
    public static String stringFromInputStream(InputStream inputStream) {
        try {
            byte[] readBuffer = new byte[Constant.CACHE_SIZE];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int readLen = inputStream.read(readBuffer, 0, Constant.CACHE_SIZE);
                if (readLen <= 0) {
                    break;
                }

                byteArrayOutputStream.write(readBuffer, 0, readLen);
            }
            return byteArrayOutputStream.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断是否为空串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 电话号码中间四位*号处理
     *
     * @param phone
     */
    public static String formatStarPhone(String phone) {
        String result = "";
        if (isEmpty(phone)) {
            return result;
        }
        result = phone.substring(0, 3) + "****" + phone.substring(7);
        return result;
    }

    /**
     * 格式化车牌号中间以***代替
     *
     * @param plateNum
     * @return
     */
    public static String formatPlateNum(String plateNum) {
        if (!StringUtils.isEmpty(plateNum) && plateNum.length() > 4) {
            plateNum = plateNum.substring(0, 2) + "***" + plateNum.substring(plateNum.length() - 2);
        }
        return plateNum;
    }

    /**
     * 格式化带点的车牌号
     *
     * @param plateNum
     * @param isDot
     * @return
     */
    public static String formatDotPlateNum(String plateNum, boolean isDot) {
        if (isDot) {
            if (!StringUtils.isEmpty(plateNum) && plateNum.length() > 2) {
                plateNum = plateNum.substring(0, 2) + "·" + plateNum.substring(2);
            }
        } else {
            if (!StringUtils.isEmpty(plateNum)) {
                plateNum = plateNum.replace("·", "");
            }

        }

        return plateNum;
    }

    /**
     * 去掉手机号内除数字外的所有字符
     *
     * @param phoneNum 手机号
     * @return
     */
    public static String formatPhoneNum(String phoneNum) {
        String regex = "(\\+66)|[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.replaceAll("");
    }

    /**
     * 手机号*号处理
     *
     * @param phonenum
     * @return
     */
    public static String formatStarPhoneNum(String phonenum) {
        if (!StringUtils.isEmpty(phonenum) && phonenum.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phonenum.length(); i++) {
                char c = phonenum.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return phonenum;
    }

    /**
     * base64解码
     * @param base64Str
     * @return
     */
    public static byte[] decordeBase64(String base64Str) {
        byte[] bytes = new byte[0];
        if (!StringUtils.isEmpty(base64Str)) {
            bytes = Base64.decode(base64Str, Base64.DEFAULT);
        }
        return bytes;
    }

}
