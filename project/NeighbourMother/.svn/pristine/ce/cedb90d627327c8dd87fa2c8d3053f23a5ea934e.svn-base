package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.OrderPayReq;
import com.qiantang.neighbourmother.business.response.AliPayResp;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * 专员订单详情查看孩子信息
 *
 * @author zb
 * @date 创建时间：2015年9月11日 下午7:16:30
 */
public class AliPayHttp extends BaseHttp {
    private Handler handler;
    private int what;
    private OrderPayReq orderPayReq;

    public AliPayHttp(Context context, Handler handler, OrderPayReq orderPayReq, int what) {
        this.context = context;
        this.handler = handler;
        this.orderPayReq = orderPayReq;
        this.what = what;
        start();
    }

    private void start() {
        orderPayReq.setPay_platform(2);
        String param = new Gson().toJson(orderPayReq);
        AppLog.D("param:" + param);
        AppLog.D("url:" + getUri());
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
        sendHandler(handler, new Gson().fromJson(responseObj.getSuccess(), AliPayResp.class), what);
    }

    @Override
    String getPrimaryUrl() {
        return API.ORDER_PAY;
    }


}
