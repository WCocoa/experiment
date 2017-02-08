package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.qlhttp.bean.QLHttpHeader;
import com.qiantang.neighbourmother.business.request.PhoneCodeReq;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.ArrayList;

/**
 * 注册网络请求
 *
 * @author zb
 * @date 创建时间：2015年9月11日 下午7:16:30
 */
public class PhoneCodeHttp extends BaseHttp {
    private Handler handler;
    private int what;
    private PhoneCodeReq phoneCodeReq;

    public PhoneCodeHttp(Context context, Handler handler, PhoneCodeReq phoneCodeReq, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.phoneCodeReq = phoneCodeReq;
        start();
    }

    private void start() {
        String param = new Gson().toJson(phoneCodeReq);
        AppLog.D("url:" + getUri());
        AppLog.D("url_param:" + param);
        getHttp().post(getUri(), getLPHttpHeader(context,getUri()), param, new QLResponseCall() {
            @Override
            public void onStart() {
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
        sendHandler(handler, null, what);
    }

    @Override
    String getPrimaryUrl() {
        return API.SMS;
    }

    @Override
    protected ArrayList<QLHttpHeader> getLPHttpHeader(Context context) {
        ArrayList<QLHttpHeader> lpHttpHeaders = new ArrayList<QLHttpHeader>();
        lpHttpHeaders.add(new QLHttpHeader("Content-Type", "application/json"));
        lpHttpHeaders.add(new QLHttpHeader("Encoding", "utf-8"));
        return lpHttpHeaders;
    }

}
