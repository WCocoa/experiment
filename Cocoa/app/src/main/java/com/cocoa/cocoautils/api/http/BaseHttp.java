package com.cocoa.cocoautils.api.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.cocoa.cocoautils.api.respone.BaseResp;

import retrofit2.Response;

/**
 * ClassNmae:
 * Author Cocoa
 * Date 2017/7/24 0024.
 */

public class BaseHttp<T extends BaseResp> extends HttpCallBack<T> {

    private Context context;
    private Handler handler;
    private int     what;

    public BaseHttp(Context context, Handler handler, int what) {
        this.context = context;
        this.handler = handler;
        this.what = what;
    }

    @Override
    public void onStart() {
        handler.sendEmptyMessage(HttpCallBack.START);
    }

    @Override
    public void onSucess(Response response) {
        Message message1 = handler.obtainMessage();
        message1.what = what;
        message1.obj = response;
        handler.sendMessage(message1);
    }

    @Override
    public void onFail(String message, int what) {
        Message message1 = handler.obtainMessage();
        message1.what = what;
        message1.obj = message;
        handler.sendMessage(message1);
    }

    @Override
    public void onError() {
        handler.sendEmptyMessage(HttpCallBack.ERROR);
    }

    @Override
    public void onEnd() {
        handler.sendEmptyMessage(HttpCallBack.END);
    }
}
