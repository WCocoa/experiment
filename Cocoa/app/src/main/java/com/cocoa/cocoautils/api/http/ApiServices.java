package com.cocoa.cocoautils.api.http;

import com.cocoa.cocoautils.api.respone.BaseResp;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * ClassName:请求接口
 * author: Cocoa
 * date: 2016/10/18.
 */

public interface ApiServices<T extends BaseResp> {

    @FormUrlEncoded
    @GET("101110101")
    io.reactivex.Observable<T> getinfo();
}
