package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.response.LookOverServiceResp;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.List;

/**
 * ClassName:专员列表请求接口
 * author: Cocoa
 * date: 2016/9/28.
 */

public class LookOverServiceHttp extends BaseHttp {
    private Handler handler;
    private String order_id;
    private int count;
    private int what;
    private boolean isShowProgress;

    public LookOverServiceHttp(Context context, Handler handler,String order_id, int count,int what, boolean isShowProgress) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.order_id = order_id;
        this.count = count;
        this.isShowProgress = isShowProgress;
        start();
    }

    private void start() {
        AppLog.D("url:" + getUri());
        getHttp().get(getUri(), getLPHttpHeader(context,getUri()), new QLResponseCall() {
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
        List<LookOverServiceResp> lookOverServiceResps = new Gson().fromJson(responseObj.getSuccess(), new TypeToken<List<LookOverServiceResp>>() {
        }.getType());
        sendHandler(handler, lookOverServiceResps, what);
    }

    @Override
    String getPrimaryUrl() {
        return  API.LOOK_OVER_SERVICE + "?order_id=" + order_id + "&count=" + API.COUNT + "&offset=" + count;
    }
}
