package com.yesway.api_lib.manager;

import com.google.gson.Gson;
import com.yesway.api_lib.config.CommonConfig;
import com.yesway.api_lib.moudel.common.entity.User;
import com.yesway.api_lib.networklib.RetrofitManager;
import com.yesway.api_lib.utils.SPUtils;

public class UserManager {
    public static String getToken() {

        return "";
    }

    public static String getUserId() {
        User user = getUser();
        String userId = "";
        if (user != null) {
            userId = user.getId();
        }
        return userId;
    }

    /**
     * 保存用户信息
     *
     */
    public static void saveUser(User user) {
        String userJson = "";
        if (user != null) {
            userJson = new Gson().toJson(user);
        }
        SPUtils.put(RetrofitManager.mContext, CommonConfig.MAIN.KEY.KEY_USER_INFO, userJson);
    }

    /**
     * 删除用户信息
     */
    public static void delUserInfo() {
        SPUtils.put(RetrofitManager.mContext, CommonConfig.MAIN.KEY.KEY_USER_INFO, "");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static User getUser() {
        User user = null;
        String userJson = (String) SPUtils.get(RetrofitManager.mContext, CommonConfig.MAIN.KEY.KEY_USER_INFO, "");
        if (!isBlank(userJson)) {
            user = new Gson().fromJson(userJson, User.class);
        }
        return user;
    }

    public static boolean isLogin() {
        boolean isLogin = false;
        String userId = getUserId();
        if (!isBlank(userId)) {
            isLogin = true;
        }
        return isLogin;
    }

    public static boolean isBlank(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 退出登录删除相关信息
     */
    public static void logout(){
        delUserInfo();
    }
}
