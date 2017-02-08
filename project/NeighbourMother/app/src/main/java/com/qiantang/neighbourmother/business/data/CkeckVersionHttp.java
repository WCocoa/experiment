package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.response.VersionResp;
import com.qiantang.neighbourmother.util.AppLog;


public class CkeckVersionHttp extends BaseHttp {
	private Handler handler;
	private int what;
	private boolean isShowProgress;
	public CkeckVersionHttp(Context context, Handler handler, boolean isShowProgress, int what) {
		this.context = context;
		this.handler = handler;
		this.isShowProgress = isShowProgress;
		this.what = what;
		start();
	}

	private void start() {
		AppLog.D("http:" + getUri());
		getHttp().get(getUri(),getLPHttpHeader(context,getUri()),new QLResponseCall() {
			@Override
			public void onStart() {
				if(isShowProgress)
					onStartHandler(handler);
			}

			@Override
			public void onFailure(String arg0, int error) {

				AppLog.D("onFailure:" + arg0);
				AppLog.D("onFailure:"+error);

				onFailureHandler(context, handler, arg0, error);

			}

			@Override
			public void onSuccess(String result) {
				AppLog.D("success:"+result);
				onSuccessHandler(context, handler, result);
			}
		});

	}

	@Override
	void getData(Handler handler, BaseDataResp responseObj) {
		sendHandler(handler,new Gson().fromJson(responseObj.getSuccess(),VersionResp.class),what);
	}

	@Override
	String getPrimaryUrl() {
		return API.CHECK_NEW_VERSION_URL;
	}

}
