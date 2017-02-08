package com.qiantang.neighbourmother.business.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.alipay.sdk.app.PayTask;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.AliPayResp;
import com.qiantang.neighbourmother.service.PayResultService;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.order.user.OrderPayActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.ToastUtils;


public class ZfbPay {
	private Activity activity;
	private Handler mHandler;
	public ZfbPay(Activity activity, Handler mHandler){
		this.activity=activity;
		this.mHandler=mHandler;
	}

	private PayResult payResult;
public void request(final AliPayResp aliPayResp){
	new Thread() {
		@Override
		public void run() {
			// 构造PayTask 对象
			PayTask alipay = new PayTask(activity);
			// 调用支付接口，获取支付结果
			String result = alipay.pay(aliPayResp.getData(), true);
			AppLog.D("result:" + result);
			 payResult=new PayResult(result);

			mHandler.post(new Runnable() {
				@Override
				public void run() {
					String resultInfo = payResult.getResult();// 同步返回需要验证的信息
					AppLog.D("resultInfo:" + resultInfo);
					aliPayResult(payResult);
					((BaseActivity)activity).closeProgressDialog();
				}
			});
		}
	}.start();
}


	private void aliPayResult(PayResult payResult){
		switch (payResult.getResultStatus()) {
			case "9000":
				ToastUtils.toastLong(activity, R.string.order_pay_success);
				resultPeration(1);
				break;
			case "8000":
				ToastUtils.toastLong(activity,R.string.order_pay_comm);
				resultPeration(1);
				break;
			case "6004":
				ToastUtils.toastLong(activity,R.string.order_pay_comm);
				resultPeration(1);
				break;
			case "4000":
				ToastUtils.toastLong(activity,R.string.order_pay_failure);
				resultPeration(2);
				break;
			case "5000":
				ToastUtils.toastLong(activity,R.string.order_pay_replace);
				resultPeration(2);
				break;
			case "6001":
				ToastUtils.toastLong(activity,R.string.order_pay_cancer);
				resultPeration(2);
				break;
			case "6002":
				ToastUtils.toastLong(activity,R.string.order_pay_data_error);
				resultPeration(2);
				break;
			default:
				ToastUtils.toastLong(activity,R.string.order_pay_failure);
				resultPeration(2);
				break;
		}
	}

	//	1成功2失败
	private void resultPeration(int type){
		Intent intent=null;
		switch (type) {
			case 1:
				intent=new Intent(activity, PayResultService.class);
				intent.putExtra(PayResultService.INTENT_PAY_RESULT_TYPE,1);
				activity.startService(intent);
				if(aliPayResListener!=null)
					aliPayResListener.onAliPayRes(1);
//				activity.paySuccessFinish();
				break;
			case 2:
			default:
				intent=new Intent(activity, PayResultService.class);
				intent.putExtra(PayResultService.INTENT_PAY_RESULT_TYPE,2);
				activity.startService(intent);
				break;
		}
	}


	private AliPayResListener aliPayResListener;

	public void setOnAliPayResListener(AliPayResListener aliPayResListener){
		this.aliPayResListener=aliPayResListener;
	}

	public interface AliPayResListener{
		/**0失败1成功*/
		void onAliPayRes(int type);
	}
}
