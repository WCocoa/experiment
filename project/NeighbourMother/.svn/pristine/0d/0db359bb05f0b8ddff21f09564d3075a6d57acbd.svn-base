package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.CreateGroupReq;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:发起讨论组
 * author: Cocoa
 * date: 2016/8/4.
 */
public class SubmitGroupHttp extends BaseHttp {
    private Handler handler;
    private int     what;
    private boolean showProgress;
    private CreateGroupReq createGroupReq;

    public SubmitGroupHttp(Context context, Handler handler, boolean showProgress, CreateGroupReq createGroupReq,int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.showProgress = showProgress;
        this.createGroupReq = createGroupReq;
        start();

    }

    private void start() {
        AppLog.D("url:" + getUri());
        String params = new Gson().toJson(createGroupReq);
        AppLog.D("params:" + params);
        getHttp().post(getUri(), getLPHttpHeader(context, getUri()), params,new QLResponseCall() {
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
        sendHandler(handler, null, what);
    }

    @Override
    String getPrimaryUrl() {
        return API.CREATE_GROUP;
    }

}
