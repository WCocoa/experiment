package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.BindBankCardReq;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:绑定银行卡
 * author: Cocoa
 * date: 2016/9/28.
 */
public class BindBankCardHttp extends BaseHttp {
    private Handler             handler;
    private int                 what;
    private BindBankCardReq bindBankCardReq;

    public BindBankCardHttp(Context context, Handler handler, BindBankCardReq bindBankCardReq, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.bindBankCardReq = bindBankCardReq;
        start();
    }

    private void start() {
        AppLog.D("url:" + getUri());
        String param = new Gson().toJson(bindBankCardReq);
        AppLog.D("param:" + param);
        getHttp().post(getUri(), getLPHttpHeader(context, getUri()), param, new QLResponseCall() {
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
        return API.BIND_BANK_CARD;
    }


}