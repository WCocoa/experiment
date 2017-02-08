package com.qiantang.neighbourmother.business.pay;

import android.app.Activity;
import android.content.Context;

import com.qiantang.neighbourmother.business.qlhttp.bean.HeadParamObj;
import com.qiantang.neighbourmother.business.response.OrderPayResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/** 
 * @author quliang 
 * @version 2015-9-2 上午9:41:39 
 * desc 创建标签文本
 */
public class WeixinPay {
	private Context context;
	private Activity activity;
	private IWXAPI api;
	public WeixinPay(Context context, Activity activity ) {
		this.context=context;
		this.activity=activity;
		init();	
	}
	
	private void init(){	
		api = WXAPIFactory.createWXAPI(context, OtherContacts.WEIXIN_APP_ID);
	}


	private PayReq CreatePayReq(OrderPayResp orderPayResp){
		PayReq req = new PayReq();

		req.appId = orderPayResp.getAppid();
		req.partnerId = orderPayResp.getPartnerid();
		req.prepayId = orderPayResp.getPrepayid();
		req.packageValue = orderPayResp.getPpackage();
		req.nonceStr = orderPayResp.getNoncestr();
		req.timeStamp = orderPayResp.getTimestamp();
		req.sign = orderPayResp.getSign();

		AppLog.D("req.appId:"+orderPayResp.getAppid());
		AppLog.D("req.partnerId:"+orderPayResp.getPartnerid());
		AppLog.D("req.prepayId:"+orderPayResp.getPrepayid());
		AppLog.D("req.packageValue:"+orderPayResp.getPpackage());
		AppLog.D("req.nonceStr:"+orderPayResp.getNoncestr());
		AppLog.D("req.timeStamp:"+orderPayResp.getTimestamp());
		AppLog.D("req.sign:"+orderPayResp.getSign());
		return req;
	}

	public void sendPayReq(OrderPayResp orderPayResp) {
		
		PayReq payReq=CreatePayReq(orderPayResp);
		
		((BaseActivity)activity).closeProgressDialog();

		Boolean b=api.registerApp(OtherContacts.WEIXIN_APP_ID);

		AppLog.D("b:"+b);

		api.sendReq(payReq);
		AppLog.D("sendPayReqsendPayReq");
	}

	public boolean wxhasUser() {
		boolean hasUser = false;
		if (api == null)
			api = WXAPIFactory.createWXAPI(context, OtherContacts.WEIXIN_APP_ID);
		if (!api.isWXAppInstalled()) {
			ToastUtils.toastshort(context, "请先安装微信客户端!");
			return hasUser;
		}
		return true;
	}

//	private String genNonceStr() {
//		Random random = new Random();
//		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))
//				.getBytes());
//	}
	
//	private String genAppSign(List<HeadParamObj> params, String wxapi_key) {
//		StringBuilder sb = new StringBuilder();
//
//		for (int i = 0; i < params.size(); i++) {
//			sb.append(params.get(i).getName());
//			sb.append('=');
//			sb.append(params.get(i).getValue());
//			sb.append('&');
//		}
//		sb.append("key=");
//		sb.append(wxapi_key);
//		// this.sb.append("sign str\n"+sb.toString()+"\n\n");
//
//		AppLog.D("sb.toString():"+sb.toString());
//		String appSign = MD5.getMessageDigest(sb.toString().getBytes())
//				.toUpperCase();
//		AppLog.D("appSign:"+appSign);
//		return appSign;
//	}
	
//	private long genTimeStamp() {
//		return System.currentTimeMillis() / 1000;
////	}


//	private PayReq CreatePayReq(OrderPayResp orderPayResp){
//		PayReq req = new PayReq();
//
//
////		req.appId = OtherContacts.WEIXIN_APP_ID;
////		req.partnerId = weixinPayResultResp.getPartnerid();
////		req.prepayId = weixinPayResultResp.getPrepayid();
////		req.packageValue = weixinPayResultResp.getPpackage();
////		req.nonceStr = weixinPayResultResp.getNoncestr();
////		req.timeStamp = weixinPayResultResp.getTimestamp();
//
//
//		req.appId = orderPayResp.getAppid();
//		req.partnerId = orderPayResp.getPartnerid();
//		req.prepayId = orderPayResp.getPrepayid();
//		req.packageValue = orderPayResp.getPpackage();
//		req.nonceStr = orderPayResp.getNoncestr();
//		req.timeStamp = orderPayResp.getTimestamp();
////		req.sign = orderPayResp.getSign();
//
//		AppLog.D("req.appId:"+orderPayResp.getAppid());
//		AppLog.D("req.partnerId:"+orderPayResp.getPartnerid());
//		AppLog.D("req.prepayId:"+orderPayResp.getPrepayid());
//		AppLog.D("req.packageValue:"+orderPayResp.getPpackage());
//		AppLog.D("req.nonceStr:"+orderPayResp.getNoncestr());
//		AppLog.D("req.timeStamp:"+orderPayResp.getTimestamp());
//		AppLog.D("req.sign:"+orderPayResp.getSign());
//
//
//
//		List<HeadParamObj> signParams = new LinkedList<HeadParamObj>();
//		signParams.add(new HeadParamObj("appid", req.appId));
//		signParams.add(new HeadParamObj("noncestr", req.nonceStr));
//		signParams.add(new HeadParamObj("package", req.packageValue));
//		signParams.add(new HeadParamObj("partnerid", req.partnerId));
//		signParams.add(new HeadParamObj("prepayid", req.prepayId));
//		signParams.add(new HeadParamObj("timestamp", req.timeStamp));
//
////		req.sign = weixinPayResultResp.getSign();
//		req.sign = genAppSign(signParams, OtherContacts.WX_API_KEY);
//
//		return req;
//	}
	
}