package com.cocoa.cocoautils.api.http;

import com.cocoa.cocoautils.api.respone.BaseResp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * ClassName:请求接口
 * author: Cocoa
 * date: 2016/10/18.
 */

public interface ApiServices<T> {

    @FormUrlEncoded
    @POST("servantApply")
    Call<BaseResp<T>> getinfo(@Field("type") String type);
}
