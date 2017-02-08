package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.SuperadditionCostReq;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:追加费用请求接口
 * author: Cocoa
 * date: 2016/9/28.
 */

public class SuperadditionCostHttp extends BaseHttp {
    private Handler handler;
    private int what;
    private boolean isShowProgress;
    private SuperadditionCostReq superadditionCostReq;

    public SuperadditionCostHttp(Context context, Handler handler, SuperadditionCostReq superadditionCostReq, int what, boolean isShowProgress) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.isShowProgress = isShowProgress;
        this.superadditionCostReq = superadditionCostReq;
        start();
    }

    private void start() {

        String params = new Gson().toJson(superadditionCostReq);
        AppLog.D("url:" + getUri());
        AppLog.D("params:" + params);
        getHttp().post(getUri(), getLPHttpHeader(context,getUri()), params, new QLResponseCall() {
            @Override
            public void onStart() {
                if (isShowProgress)
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
        return API.SUPERADDITION_COST;
    }


}
