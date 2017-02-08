package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.FocusReq;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:关注
 * author: Cocoa
 * date: 2016/8/4.
 */
public class ConFocusHttp extends BaseHttp {
    private Handler handler;
    private int     what;
    private boolean showProgress;
    private String  user_id;

    public ConFocusHttp(Context context, Handler handler, boolean showProgress, String user_id, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.showProgress = showProgress;
        this.user_id = user_id;
        start();
    }

    private void start() {
        AppLog.D("url:" + getUri());
        FocusReq focusReq = new FocusReq(user_id);
        String   param    = new Gson().toJson(focusReq);
        AppLog.D("param:" + param);
        getHttp().post(getUri(), getLPHttpHeader(context, getUri()), param, new QLResponseCall() {
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
        return API.USER_DETAILS_FOCUS;
    }

}
