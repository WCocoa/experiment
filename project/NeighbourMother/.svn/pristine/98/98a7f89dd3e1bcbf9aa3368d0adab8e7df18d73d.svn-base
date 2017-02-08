package com.qiantang.neighbourmother.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.data.PaySuccessHttp;
import com.qiantang.neighbourmother.business.pay.OtherContacts;
import com.qiantang.neighbourmother.service.PayResultService;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpayentry);

    	api = WXAPIFactory.createWXAPI(this, OtherContacts.WEIXIN_APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp baseResp) {

				if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
					switch (baseResp.errCode) {
						case BaseResp.ErrCode.ERR_COMM:
							ToastUtils.toastLong(this,R.string.order_pay_failure);
							resultPeration(2);
							break;
						case BaseResp.ErrCode.ERR_OK:
							ToastUtils.toastLong(this,R.string.order_pay_success);
							resultPeration(1);
							break;
						case BaseResp.ErrCode.ERR_USER_CANCEL:
							ToastUtils.toastLong(this,R.string.order_pay_cancer);
							resultPeration(2);
							break;

						case BaseResp.ErrCode.ERR_SENT_FAILED:
							ToastUtils.toastLong(this,R.string.order_pay_failure);
							resultPeration(2);
							break;
						case BaseResp.ErrCode.ERR_AUTH_DENIED:
							ToastUtils.toastLong(this,R.string.order_pay_denied);
							resultPeration(2);
							break;
						case BaseResp.ErrCode.ERR_UNSUPPORT:
							ToastUtils.toastLong(this,R.string.order_pay_not_support);
							resultPeration(2);
							break;
						default:
							ToastUtils.toastLong(this,R.string.order_pay_failure);
							resultPeration(2);
							break;
					}
				}
	}

//	1成功2失败
	private void resultPeration(int type){
		int wtype=PropertyConfig.getInstance(this).getInt(IntentFinal.INTENT_WX_PAY_TYPE,0);
		switch (type) {
			case 1:
				paySuccess(wtype);
				break;
			case 2:
				payFailure(wtype);
				break;
		}
		finish();
	}


	private void paySuccess(int type){
		Intent intent=null;
switch (type){
	case 0:
		intent=new Intent(this, PayResultService.class);
		intent.putExtra(PayResultService.INTENT_PAY_RESULT_TYPE,1);
		startService(intent);
		PropertyConfig.getInstance(this).save(IntentFinal.PREF_PAY_SUCCESS_FAG,1);
		break;
	case 1:
		PropertyConfig.getInstance(this).save(IntentFinal.PREF_PAY_SUCCESS_FAG,1);
		break;
}
	}
	private void payFailure(int type){
		Intent intent=null;
		switch (type){
			case 0:
				intent=new Intent(this, PayResultService.class);
				intent.putExtra(PayResultService.INTENT_PAY_RESULT_TYPE,2);
				startService(intent);
				break;
			case 1:
				break;
		}
	}

}