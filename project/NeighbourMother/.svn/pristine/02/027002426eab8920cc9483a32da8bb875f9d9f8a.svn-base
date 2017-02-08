package com.qiantang.neighbourmother.logic;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.response.ChildInfoResp;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.PropertyConfig;

/**
 * Created by Administrator on 2015/12/7.
 */
public class ChildContacts {


    public static final String CHILD_INFO = "CHILD_INFO";

    private static ChildInfoResp childInfoResp;

    /**
     * 保存用户信息
     *
     * @param context
     */
    public static void saveChildInfo(Context context, ChildInfoResp childInfoResp1) {
        PropertyConfig mPropertyConfig = PropertyConfig.getInstance(context);
        childInfoResp = childInfoResp1;

       String child_info= new Gson().toJson(childInfoResp, ChildInfoResp.class);
        //保存用户信息
        mPropertyConfig.save(ChildContacts.CHILD_INFO, child_info);
    }

    public static ChildInfoResp getChildInfo(Context context) {
        if (childInfoResp != null)
            return (ChildInfoResp)childInfoResp.clone();
        PropertyConfig mPropertyConfig = PropertyConfig.getInstance(context);
        String strOrgInfo = mPropertyConfig.getString(CHILD_INFO);

        if (!TextUtils.isEmpty(strOrgInfo))
            childInfoResp = new Gson().fromJson(strOrgInfo, ChildInfoResp.class);

        return childInfoResp==null?null:(ChildInfoResp) childInfoResp.clone();
    }

    public static void clearChildInfo(Context context) {
        PropertyConfig mPropertyConfig = PropertyConfig.getInstance(context);
        mPropertyConfig.save(ChildContacts.CHILD_INFO, null);
        childInfoResp = null;
    }

}
