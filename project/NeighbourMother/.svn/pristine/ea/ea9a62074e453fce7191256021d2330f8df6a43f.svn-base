package com.qiantang.neighbourmother.business.qlhttp;


import com.qiantang.neighbourmother.business.qlhttp.bean.HeadParamObj;
import com.qiantang.neighbourmother.business.qlhttp.bean.OKHttpBase;
import com.qiantang.neighbourmother.business.qlhttp.bean.QLHttpHeader;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.ArrayList;
import java.util.List;

public class QLHttp {


    /**
     * get1
     */
    public void get(String url,
                    final QLResponseCall mResponseCall) {
        new OKHttpBase(url, mResponseCall, 0).run();
    }

    /**
     * get2
     */
    public void get(String url, List<QLHttpHeader> mLPHttpHeaders,
                    final QLResponseCall mResponseCall) {

        new OKHttpBase(url, mLPHttpHeaders, mResponseCall, 0).run();
    }

    /**
     * post1
     */
    public void post(String url,
                     final QLResponseCall mResponseCall) {
        new OKHttpBase(url, mResponseCall, 1).run();
    }


    /**
     * post2
     */
    public void post(String url, ArrayList<HeadParamObj> mLPHttpParams,
                     final QLResponseCall mResponseCall) {

        new OKHttpBase(url, mLPHttpParams, mResponseCall).run();
    }

    /**
     * post3
     */
    public void post(String url, String params,
                     final QLResponseCall mResponseCall) {

        new OKHttpBase(url, params, mResponseCall).run();

    }

    /**
     * post4
     */
    public void post(String url, List<QLHttpHeader> mLPHttpHeaders,
                     final QLResponseCall mResponseCall) {
        new OKHttpBase(url, mLPHttpHeaders, mResponseCall, 1).run();
    }

    /**
     * post5
     */
    public void post(String url, List<QLHttpHeader> mLPHttpHeaders, ArrayList<HeadParamObj> mLPHttpParams,
                     final QLResponseCall mResponseCall) {

        new OKHttpBase(url, mLPHttpHeaders, mLPHttpParams, mResponseCall).run();
    }

    /**
     * post6
     */
    public void post(String url, List<QLHttpHeader> mLPHttpHeaders, String params,
                     final QLResponseCall mResponseCall) {

        new OKHttpBase(url, mLPHttpHeaders, params, mResponseCall).run();
    }

    /**
     * post7
     */
    public void fileUpload(String url, List<QLHttpHeader> headers, ArrayList<HeadParamObj> nvs,
                           ArrayList<UpFileObj> upFileObjs, QLResponseCall mResponseCall) {
        AppLog.D("fileUploadfileUpload:FORM");
        new OKHttpBase(url, headers, nvs, upFileObjs, mResponseCall).run();
    }

    /**
     * post8
     */
    public void fileUpload(String url, List<QLHttpHeader> headers, String jsons,
                           ArrayList<UpFileObj> upFileObjs, final QLResponseCall mResponseCall) {
        new OKHttpBase(url, headers, jsons, upFileObjs, mResponseCall).run();
    }

}
