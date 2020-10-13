package com.yesway.common_lib.utils.security.base64;

/**
 * @Description:Base64加密类工厂
 * @Date: 2018/03/07
 * @Version: V1.0.0
 * @Update: 更新说明
 */
public class Base64Factory {

    /**
     * Base64工厂对象
     */
    private static volatile Base64Factory factory;

    /**
     * 将构造函数私有化
     */
    private Base64Factory() {
    }

    /**
     * 获得一个Base64Factory的单例对象。
     *
     * @return Base64Apache对象。
     */
    public static Base64Factory getInstance() {
        // 采用锁进行双重判断，确保对象是单例的
        if (factory == null) {
            synchronized (Base64Factory.class) {
                if (factory == null) {
                    factory = new Base64Factory();
                }
            }
        }
        return factory;
    }

    /**
     * 创建Apache提供的的Base64加密对象。
     *
     * @return Base64加密对象。
     */
    public IBase64Util getBase64() {
        return getBase64(Base64Type.APACHE);
    }

    /**
     * 创建指定类型的Base64加密对象。
     *
     * @param type
     * @return Base64加密对象。
     */
    public IBase64Util getBase64(Base64Type type) {
        IBase64Util base64 = null;
        switch (type) {
            case APACHE:
            default:
                base64 = Base64Apache.getInstance();
                break;
        }
        if (base64 == null) {
            throw new NullPointerException("没有与输入的类型相匹配的Base64加密类。");
        }
        return base64;
    }

}
