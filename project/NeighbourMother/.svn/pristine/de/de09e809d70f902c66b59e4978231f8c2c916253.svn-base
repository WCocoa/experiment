package com.qiantang.neighbourmother.service;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.qiantang.neighbourmother.business.data.PayCancerHttp;
import com.qiantang.neighbourmother.business.data.PaySuccessHttp;
import com.qiantang.neighbourmother.business.request.PayResultReq;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.PropertyConfig;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class PayResultService extends IntentService {
    /**1支付成功2支付取消*/
    public final static String INTENT_PAY_RESULT_TYPE="INTENT_PAY_RESULT_TYPE";
    public final static String PREF_PAY_RESULT_OBJ="PREF_PAY_RESULT_OBJ";

    private String  payId;
    public PayResultService() {
        super("PayResultService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AppLog.D("onHandleIntent");
        if(intent!=null){
            payId=PropertyConfig.getInstance(this).getString(PREF_PAY_RESULT_OBJ);
            AppLog.D("onHandleIntent_payId:"+payId);
            if(!TextUtils.isEmpty(payId)){
                resultOper(intent.getIntExtra(INTENT_PAY_RESULT_TYPE,0));

            }
        }
    }

    private void resultOper(int type){
        switch (type){
            case 1:
                new PaySuccessHttp(this,new PayResultReq(payId,String.valueOf(type)));
                break;
            case 2:
                new PayCancerHttp(this,new PayResultReq(payId,String.valueOf(type)));
                break;
        }
    }

}
