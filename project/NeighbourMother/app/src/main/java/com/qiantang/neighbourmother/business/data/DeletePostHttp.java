package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.DeletePostReq;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:删除帖子
 * author: Cocoa
 * date: 2016/9/28.
 */

public class DeletePostHttp extends BaseHttp {
    private Handler handler;
    private int what;
    private boolean isShowProgress;
    private String post_id;

    public DeletePostHttp(Context context, Handler handler, String post_id, int what, boolean isShowProgress) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.isShowProgress = isShowProgress;
        this.post_id = post_id;
        start();
    }

    private void start() {

        String params = new Gson().toJson(new DeletePostReq(post_id));
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
        return API.DELETE_POST;
    }

}
