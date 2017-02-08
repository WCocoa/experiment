package com.qiantang.neighbourmother.business.qlhttp.bean;

import com.qiantang.neighbourmother.business.qlhttp.OKHttp;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.util.AppLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OKHttpBase {
    private String url;
    private QLResponseCall mResponseCall;
    private List<QLHttpHeader> mLPHttpHeaders;
    private ArrayList<HeadParamObj> mLPHttpParams;
    private String params;
    private ArrayList<UpFileObj> upFileObjs;
    //    private Map<String, File> mapFiles;
    //	0get 1post
    private int httpRequestType;
    private int httpRequestParamType;


    /*httpRequestParamType 1*/
    public OKHttpBase(String url,
                      final QLResponseCall mResponseCall, int httpRequestType) {
        this.httpRequestType = httpRequestType;
        this.url = url;
        this.mResponseCall = mResponseCall;
        httpRequestParamType = 1;
    }

    /*httpRequestParamType 2*/
    public OKHttpBase(String url, List<QLHttpHeader> mLPHttpHeaders,
                      final QLResponseCall mResponseCall, int httpRequestType) {
        this.url = url;
        this.mResponseCall = mResponseCall;
        this.mLPHttpHeaders = mLPHttpHeaders;
        this.httpRequestType = httpRequestType;
        httpRequestParamType = 2;
    }

    /*httpRequestParamType 3*/
    public OKHttpBase(String url, ArrayList<HeadParamObj> mLPHttpParams,
                      final QLResponseCall mResponseCall) {
        this.url = url;
        this.mResponseCall = mResponseCall;
        this.mLPHttpParams = mLPHttpParams;
        httpRequestParamType = 3;
        this.httpRequestType = 1;
    }

    /*httpRequestParamType 4*/
    public OKHttpBase(String url, String params,
                      final QLResponseCall mResponseCall) {
        this.url = url;
        this.mResponseCall = mResponseCall;
        this.params = params;
        httpRequestParamType = 4;
        this.httpRequestType = 1;
    }

    /*httpRequestParamType 5*/
    public OKHttpBase(String url, List<QLHttpHeader> mLPHttpHeaders, ArrayList<HeadParamObj> mLPHttpParams,
                      final QLResponseCall mResponseCall) {
        this.url = url;
        this.mResponseCall = mResponseCall;
        this.mLPHttpParams = mLPHttpParams;
        this.mLPHttpHeaders = mLPHttpHeaders;
        httpRequestParamType = 5;
        this.httpRequestType = 1;
    }

    /*httpRequestParamType 6*/
    public OKHttpBase(String url, List<QLHttpHeader> mLPHttpHeaders, String params,
                      final QLResponseCall mResponseCall) {
        this.url = url;
        this.mResponseCall = mResponseCall;
        this.params = params;
        this.mLPHttpHeaders = mLPHttpHeaders;
        httpRequestParamType = 6;
        this.httpRequestType = 1;
    }

    /*httpRequestParamType 7*/
    public OKHttpBase(String url, List<QLHttpHeader> headers, ArrayList<HeadParamObj> nvs,
                      ArrayList<UpFileObj> upFileObjs, QLResponseCall mResponseCall) {
        this.url = url;
        this.upFileObjs = upFileObjs;
        this.mResponseCall = mResponseCall;
        this.mLPHttpParams = nvs;
        this.mLPHttpHeaders = headers;
        httpRequestParamType = 7;
        this.httpRequestType = 1;
    }

    /**
     * post8
     */
    public OKHttpBase(String url, List<QLHttpHeader> headers, String params,
                      ArrayList<UpFileObj> upFileObjs, final QLResponseCall mResponseCall) {
        this.url = url;
        this.upFileObjs = upFileObjs;
        this.mResponseCall = mResponseCall;
        this.params = params;
        this.mLPHttpHeaders = headers;
        httpRequestParamType = 8;
        this.httpRequestType = 1;
    }


    public void run() {
        Request request = getRequest();
        if (request == null)
            return;
        mResponseCall.onStart();
        OKHttp.getInstace().newBuilder().build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mResponseCall.onFailure(null, 0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                AppLog.D("OKHttp.onResponse");
                String bodyString = response.body().string();
                if (response.isSuccessful()) {
                    mResponseCall.onSuccess(bodyString);
                } else {
                    mResponseCall.onFailure(null, response.code());
                }
                response.body().close();
            }

        });
    }

    private Request getRequest() {
        MultipartBody.Builder mutipartbodyBuilder;
        FormBody.Builder formbody;
        Headers.Builder headersBuilder;
        Request request = null;
        switch (httpRequestParamType) {
            case 1:

                switch (httpRequestType) {
                    case 0:
                        request = new Request.Builder().url(url).get().build();

                        break;
                    case 1:
                        request = new Request.Builder().url(url).post(null).build();

                        break;
                }

                break;

            case 2:
                headersBuilder = new Headers.Builder();
                for (QLHttpHeader qlHttpHeader : mLPHttpHeaders) {
                    headersBuilder.add(qlHttpHeader.getKey(), qlHttpHeader.getValue());
                }
                switch (httpRequestType) {
                    case 0:
                        request = new Request.Builder().url(url).get().headers(headersBuilder.build()).build();
                        break;
                    case 1:
                        request = new Request.Builder().url(url).post(null).headers(headersBuilder.build()).build();
                        break;
                }

                break;
            case 3:
                formbody = new FormBody.Builder();
                for (HeadParamObj nameValuePair : mLPHttpParams) {
                    formbody.add(nameValuePair.getName(), nameValuePair.getValue());
                }
                request = new Request.Builder().url(url).post(formbody.build()).build();
                break;
            case 4:
                request = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params)).build();
                break;
            case 5:
                headersBuilder = new Headers.Builder();
                for (QLHttpHeader qlHttpHeader : mLPHttpHeaders) {
                    headersBuilder.add(qlHttpHeader.getKey(), qlHttpHeader.getValue());
                }
                formbody = new FormBody.Builder();
                for (HeadParamObj nameValuePair : mLPHttpParams) {
                    formbody.add(nameValuePair.getName(), nameValuePair.getValue());
                }
                request = new Request.Builder().url(url).post(formbody.build()).headers(headersBuilder.build()).build();
                break;
            case 6:
                headersBuilder = new Headers.Builder();
                for (QLHttpHeader qlHttpHeader : mLPHttpHeaders) {
                    headersBuilder.add(qlHttpHeader.getKey(), qlHttpHeader.getValue());
                }
                request = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params)).headers(headersBuilder.build()).build();
                break;
            case 7:
                mutipartbodyBuilder = new MultipartBody.Builder();
                mutipartbodyBuilder.setType(MultipartBody.FORM);

                for (UpFileObj upFileObj : upFileObjs) {
                    mutipartbodyBuilder.addFormDataPart(upFileObj.getKey(), upFileObj.getKey(), RequestBody.create(null, upFileObj.getFile()));
                }

                headersBuilder = new Headers.Builder();
                for (QLHttpHeader qlHttpHeader : mLPHttpHeaders) {
                    headersBuilder.add(qlHttpHeader.getKey(), qlHttpHeader.getValue());
                }
                request = new Request.Builder().url(url).post(mutipartbodyBuilder.build()).headers(headersBuilder.build()).build();
                break;

            case 8:
                mutipartbodyBuilder = new MultipartBody.Builder();
                mutipartbodyBuilder.setType(MultipartBody.FORM);
                for (UpFileObj upFileObj : upFileObjs) {
                    mutipartbodyBuilder.addFormDataPart(upFileObj.getKey(), upFileObj.getKey(), RequestBody.create(null, upFileObj.getFile()));
                }

                mutipartbodyBuilder.addPart(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params));

                headersBuilder = new Headers.Builder();
                for (QLHttpHeader qlHttpHeader : mLPHttpHeaders) {
                    headersBuilder.add(qlHttpHeader.getKey(), qlHttpHeader.getValue());
                }

                request = new Request.Builder().url(url).post(mutipartbodyBuilder.build()).headers(headersBuilder.build()).build();
                break;
        }

//        mResponseCall.onStart();
//        call=builder.build().newCall(request);

        return request;
    }


}
