package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.response.ChildInfoResp;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * 注册网络请求
 * @author zb
 * @date 创建时间：2015年9月11日 下午7:16:30
 */
public class ChildInfoHttp extends BaseHttp {
	private Handler handler;
	private int what;
	public ChildInfoHttp(Context context, Handler handler, int what) {
		this.context = context;
		this.handler = handler;
		this.what = what;
		start();
	}

	private void start() {
		AppLog.D("url:"+getUri());
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
		sendHandler(handler,new Gson().fromJson(responseObj.getSuccess(), ChildInfoResp.class),what);
	}

	@Override
	String getPrimaryUrl() {
		return API.CHILD_INFO;
	}


}
