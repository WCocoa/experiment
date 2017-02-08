package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.RechargeReq;
import com.qiantang.neighbourmother.business.response.AliPayResp;
import com.qiantang.neighbourmother.model.RechargeObj;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.ArrayList;

/**
 * 专员订单详情查看孩子信息
 *
 * @author zb
 * @date 创建时间：2015年9月11日 下午7:16:30
 */
public class RechargeAliHttp extends BaseHttp {
    private Handler handler;
    private int what;
    private RechargeReq rechargeReq;

    public RechargeAliHttp(Context context, Handler handler, RechargeReq rechargeReq, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.rechargeReq = rechargeReq;
        start();
    }

    private void start() {
        String param=new Gson().toJson(rechargeReq);
        AppLog.D("param:" + param);
        AppLog.D("url:" + getUri());
        getHttp().post(getUri(), getLPHttpHeader(context, getUri()), param,new QLResponseCall() {
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
        return API.RECHARGE_MONEY;
    }


}