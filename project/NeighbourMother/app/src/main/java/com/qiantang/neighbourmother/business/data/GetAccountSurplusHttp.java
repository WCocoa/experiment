package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.response.AttacheDetailResp;
import com.qiantang.neighbourmother.business.response.PayAccountSurplusResp;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:专员列表请求接口
 * author: Cocoa
 * date: 2016/9/28.
 */

public class GetAccountSurplusHttp extends BaseHttp {
    private Handler handler;
    private int what;

    public GetAccountSurplusHttp(Context context, Handler handler, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        start();
    }

    private void start() {
        AppLog.D("url:" + getUri());
        getHttp().get(getUri(), getLPHttpHeader(context,getUri()), new QLResponseCall() {
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
//        List<AttacheListResp> attacheListResps = new Gson().fromJson(responseObj.getSuccess(), new TypeToken<List<AttacheListResp>>() {
//        }.getType());
        sendHandler(handler,new Gson().fromJson(responseObj.getSuccess(), PayAccountSurplusResp.class), what);
    }

    @Override
    String getPrimaryUrl() {
        return API.PAY_GET_SURPLUS_MONEY;
    }
}
