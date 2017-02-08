package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.response.CenterMoneyResp;
import com.qiantang.neighbourmother.util.AppLog;

import static com.qiantang.neighbourmother.business.API.CENTER_MONEY;

/**
 * ClassName:中心获取金额接口
 * author: Cocoa
 * date: 2016/9/28.
 */
public class GetCenterMoneyHttp extends BaseHttp {
    private Handler handler;
    private int     what;
    private boolean isShow;
    private int type;

    public GetCenterMoneyHttp(Context context, Handler handler, int type,int what, boolean isShow) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.type = type;
        this.isShow = isShow;
        start();
    }

    private void start() {
        AppLog.D("url:" + getUri());
        getHttp().get(getUri(), getLPHttpHeader(context, getUri()), new QLResponseCall() {
            @Override
            public void onStart() {
                if (isShow)
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
        sendHandler(handler, new Gson().fromJson(responseObj.getSuccess(), CenterMoneyResp.class), what);
    }

    @Override
    String getPrimaryUrl() {
        return CENTER_MONEY + "?type=" + type;
    }


}
