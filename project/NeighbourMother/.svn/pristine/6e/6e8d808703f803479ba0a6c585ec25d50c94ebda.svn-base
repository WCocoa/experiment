package com.qiantang.neighbourmother.logic;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.PropertyConfig;

/**
 * Created by Administrator on 2015/12/7.
 */
public class UserContacts {

    //token

    //用户token
    public static final String USER_TOKEN = "USER_TOKEN";
    //用户id
    public static final String USER_ID = "USER_ID";
    public static final String USER_INFO = "USER_INFO";
    //登录帐号
    public static final String USER_ACOUNT = "USER_ACOUNT";
    //1用户2专员
    public static final String USER_ROLE = "USER_ROLE";
    //登录密码
//    public static final String USER_PASSWORD = "USER_PASSWORD";

    private static UserInfoResp userInfoResp;

    /**
     * 保存用户信息
     *
     * @param context
     * @param userInfo
     */
    public static void saveUserInfo(Context context, UserInfoResp userInfo, boolean isFirstLogin) {
        PropertyConfig mPropertyConfig = PropertyConfig.getInstance(context);
        if (isFirstLogin) {
            mPropertyConfig.save(UserContacts.USER_TOKEN, userInfo.getToken());
            mPropertyConfig.save(UserContacts.USER_ID, userInfo.getUser_id());
        }
        userInfoResp = userInfo;
        //保存用户信息
        mPropertyConfig.save(UserContacts.USER_INFO, new Gson().toJson(userInfo, UserInfoResp.class));
    }

    public static UserInfoResp getUserInfo(Context context) {
        AppLog.D("getUserInfo:"+PropertyConfig.getInstance(context).getString(USER_INFO));

        if (userInfoResp != null)
            return (UserInfoResp)userInfoResp.clone();
        PropertyConfig mPropertyConfig = PropertyConfig.getInstance(context);
        String strOrgInfo = mPropertyConfig.getString(USER_INFO);
        if (!TextUtils.isEmpty(strOrgInfo))
            userInfoResp = new Gson().fromJson(strOrgInfo, UserInfoResp.class);
        return (UserInfoResp) userInfoResp.clone();
    }

    public static void clearUserInfo(Context context) {
        PropertyConfig mPropertyConfig = PropertyConfig.getInstance(context);
        mPropertyConfig.save(UserContacts.USER_INFO, null);
        mPropertyConfig.save(UserContacts.USER_ACOUNT, null);
        mPropertyConfig.save(UserContacts.USER_TOKEN, "");
        mPropertyConfig.save(UserContacts.USER_ID, "");
        mPropertyConfig.save(UserContacts.USER_ROLE, 0);
        userInfoResp = null;
    }

}
