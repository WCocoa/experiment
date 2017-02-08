package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.response.OrderDetailResp;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:专员删除订单请求接口
 * author: Cocoa
 * date: 2016/9/28.
 */

public class OrderDetailHttp extends BaseHttp {
    private Handler handler;
    private int what;
    private String orderId;

    public OrderDetailHttp(Context context, Handler handler, String orderId, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.orderId = orderId;
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
        sendHandler(handler, new Gson().fromJson(responseObj.getSuccess(), OrderDetailResp.class), what);
    }

    @Override
    String getPrimaryUrl() {
        return API.ORDER_DETAIL + "?order_id=" + orderId;
    }

}