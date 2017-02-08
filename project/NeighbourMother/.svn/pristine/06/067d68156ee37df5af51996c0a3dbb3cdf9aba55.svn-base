package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:申请明星达人
 * author: Cocoa
 * date: 2016/8/4.
 */
public class ApplyForStarHttp extends BaseHttp {
    private Handler handler;
    private int     what;
    private boolean showProgress;

    public ApplyForStarHttp(Context context, Handler handler, boolean showProgress, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.showProgress = showProgress;
        start();
    }

    private void start() {
        AppLog.D("url:" + getUri());
        getHttp().post(getUri(), getLPHttpHeader(context, getUri()), "{}",new QLResponseCall() {
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
        return API.APPLY_FOR_STAR;
    }

}
