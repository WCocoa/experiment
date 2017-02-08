package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.qlhttp.bean.HeadParamObj;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.ArrayList;

public class UploadImageHttp extends BaseHttp {
    public static final String TAG = UploadImageHttp.class.getSimpleName();
    private Handler handler;
    private ArrayList<UpFileObj> upFileObjs;
    private int what;
    private boolean isShowProgress;

    public UploadImageHttp(Context context, Handler handler, ArrayList<UpFileObj> upFileObjs, boolean isShowProgress, int what) {
        this.context = context;
        this.handler = handler;
        this.upFileObjs = upFileObjs;
        this.isShowProgress = isShowProgress;
        this.what = what;
        start();
    }

    private void start() {
        AppLog.D("url:" + API.IMG_UPLOADER + upFileObjs.toString());
        ArrayList<HeadParamObj> nvp = new ArrayList<HeadParamObj>();


        getHttp().fileUpload(getUri(), getLPHttpHeader(context, getUri()), nvp, upFileObjs, new QLResponseCall() {
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
                onSuccessHandler(context, handler, result);
            }
        });

    }

    @Override
    void getData(Handler handler, BaseDataResp responseObj) {
        //    	List<BbsObj> studentBbsObjs = new Gson().fromJson(responseObj.getSuccess(), new TypeToken<List<BbsObj>>() {}.getType());
        AppLog.D(responseObj.getSuccess().toString());
        String[] imgs = new Gson().fromJson(responseObj.getSuccess(), new TypeToken<String[]>() {
        }.getType());
        sendHandler(handler, imgs, what);
    }

    @Override
    String getPrimaryUrl() {
        return API.IMG_UPLOADER;
    }

}
