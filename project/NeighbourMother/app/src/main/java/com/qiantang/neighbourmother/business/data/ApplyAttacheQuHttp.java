package com.qiantang.neighbourmother.business.data;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.qlhttp.QLResponseCall;
import com.qiantang.neighbourmother.business.request.ApplyAttacheQuReq;
import com.qiantang.neighbourmother.business.response.ApplyAttacheQuResp;
import com.qiantang.neighbourmother.util.AppLog;

/**
 * 注册网络请求
 * @author zb
 * @date 创建时间：2015年9月11日 下午7:16:30
 */
public class ApplyAttacheQuHttp extends BaseHttp {
	private Handler handler;
	private int what;
	private ApplyAttacheQuReq applyAttacheQuReq;
	public ApplyAttacheQuHttp(Context context, Handler handler, ApplyAttacheQuReq applyAttacheQuReq, int what) {
		this.context = context;
		this.handler = handler;
		this.what = what;
		this.applyAttacheQuReq = applyAttacheQuReq;
		start();
	}

	private void start() {
		String param=new Gson().toJson(applyAttacheQuReq);
		AppLog.D("param:"+param);
		AppLog.D("url:"+getUri());
		getHttp().post(getUri(), getLPHttpHeader(context,getUri()),param,new QLResponseCall() {
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
//		sendHandler(handler,null,what);
		sendHandler(handler,new Gson().fromJson(responseObj.getSuccess(), ApplyAttacheQuResp.class),what);
	}

	@Override
	String getPrimaryUrl() {
		return API.RECUIT_ATTACHE_QU;
	}


}
