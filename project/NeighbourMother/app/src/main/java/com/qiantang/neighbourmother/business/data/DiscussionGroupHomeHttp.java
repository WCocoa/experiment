package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.DiscussionGroupHomeReq;
import com.qiantang.neighbourmother.business.request.PublicPosterReq;
import com.qiantang.neighbourmother.business.response.DiscussionGroupHomeResp;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:专员列表请求接口
 * author: Cocoa
 * date: 2016/9/28.
 */

public class DiscussionGroupHomeHttp extends BaseHttp {
    private Handler handler;
    private int what;
    private DiscussionGroupHomeReq discussionGroupHomeReq;

    public DiscussionGroupHomeHttp(Context context, Handler handler, DiscussionGroupHomeReq discussionGroupHomeReq, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
        this.discussionGroupHomeReq = discussionGroupHomeReq;
        start();
    }

    private void start() {
        AppLog.D("url:" + getUri());
        getHttp().get(getUri(), getLPHttpHeader(context,getUri()),new QLResponseCall() {
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
        sendHandler(handler, new Gson().fromJson(responseObj.getSuccess(), DiscussionGroupHomeResp.class), what);
    }

    @Override
    String getPrimaryUrl() {
        return API.DISCUSSION_GROUP_HOME+"?count="+discussionGroupHomeReq.getCount()+"&offset="+discussionGroupHomeReq.getOffset()+"&group_id="+discussionGroupHomeReq.getGroup_id();
    }
}
