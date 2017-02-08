package com.qiantang.neighbourmother.business.data;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.qlhttp.bean.QLHttpHeader;
import com.qiantang.neighbourmother.business.request.LoginReq;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.ArrayList;

/**
 * ClassName:登录接口
 * author: Cocoa
 * date: 2016/8/4.
 */
public class LoginHttp extends BaseHttp {
    private Handler handler;
    private LoginReq loginReq;
    private int what;
    private boolean showProgress;

    public LoginHttp(Context context, Handler handler, LoginReq loginReq, boolean showProgress, int what) {
        this.context = context;
        this.handler = handler;
        this.loginReq = loginReq;
        this.what = what;
        this.showProgress = showProgress;
        start();
    }

    private void start() {

        String param = new Gson().toJson(loginReq);
        AppLog.D("param:" + param);
        AppLog.D("url:" + getUri());
        getHttp().post(getUri(), getLPHttpHeader(context, getUri()), param, new QLResponseCall() {
            @Override
            public void onStart() {
                if (showProgress)
                    onStartHandler(handler);
            }

            @Override
            public void onFailure(String arg0, int error) {
                AppLog.D("onFailure:" + error);
                AppLog.D("onFailure:" + arg0);
                onFailureHandler(context, handler, arg0, error);
            }

            @Override
            public void onSuccess(String result) {
                AppLog.D("result:" + result);
                onSuccessHandler(context, handler, result);
            }
        });
    }

    @Override
    void getData(Handler handler, BaseDataResp responseObj) {
        sendHandler(handler, new Gson().fromJson(responseObj.getSuccess(), UserInfoResp.class), what);
    }

    @Override
    String getPrimaryUrl() {
        return API.LOGIN;
    }


    protected ArrayList<QLHttpHeader> getLPHttpHeader(Context context) {
        ArrayList<QLHttpHeader> lpHttpHeaders = new ArrayList<QLHttpHeader>();

        lpHttpHeaders.add(new QLHttpHeader("Content-Type", "application/json"));
        lpHttpHeaders.add(new QLHttpHeader("Encoding", "utf-8"));
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        String useragent = "android," + Build.MODEL + "," + Build.VERSION.RELEASE + "," + Build.BRAND + "," + dm.widthPixels + "*" + dm.heightPixels;
        AppLog.D("useragent:" + useragent);
        lpHttpHeaders.add(new QLHttpHeader("User-Agent", useragent));

        return lpHttpHeaders;
    }

}
