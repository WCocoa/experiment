package com.cocoa.cocoautils.api.http;

import com.cocoa.cocoautils.api.respone.BaseResp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ClassName:回调封装
 * author: Cocoa
 * date: 2016/10/18.
 */

public abstract class HttpCallBack<T extends BaseResp> implements Callback<T> {

    public static final int START = 0;
    public static final int TOKEN_INVALID = -2;
    public static final int FAILURE = -1;
    public static final int ERROR = -4;
    public static final int END = -3;
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.raw().code() == 200) {
            if (response.body().getCode() == 0) {
                onFail(response.message(),ERROR);
            } else if (response.body().getCode() == 1) {

                onSucess(response);
            }

        }else {
            onFailure(call,new RuntimeException("response error,detail = " + response.raw().toString()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(t.getMessage(),FAILURE);
    }

    public abstract void onSucess(Response<T> response);

    public abstract void onFail(String message, int what);

}
