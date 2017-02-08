package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.response.ConmmunityUserDetailsResp;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:社群用户详情
 * author: Cocoa
 * date: 2016/9/28.
 */

public class ConUserDetailsHttp extends BaseHttp {
    private Handler handler;
    private String     user_id;
    private int     offset;
    private int     what;
    private boolean isShowProgress;

    public ConUserDetailsHttp(Context context, Handler handler,String  user_id,int offset, int what, boolean isShowProgress) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.user_id = user_id;
        this.offset = offset;
        this.isShowProgress = isShowProgress;
        start();
    }

    private void start() {
        AppLog.D("url:" + getUri());
        getHttp().get(getUri(), getLPHttpHeader(context, getUri()), new QLResponseCall() {
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
//        List<AttacheListResp> attacheListResps = new Gson().fromJson(responseObj.getSuccess(), new TypeToken<List<AttacheListResp>>() {
//        }.getType());
        sendHandler(handler, new Gson().fromJson(responseObj.getSuccess(), ConmmunityUserDetailsResp.class), what);
    }

    @Override
    String getPrimaryUrl() {
        return API.USER_DETAILS + "?user_id=" + user_id + "&count=" + API.COUNT + "&offset="+offset;
    }
}
