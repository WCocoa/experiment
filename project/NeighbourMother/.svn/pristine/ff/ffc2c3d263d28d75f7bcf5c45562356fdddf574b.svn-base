package com.qiantang.neighbourmother.ui.center.user;

import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.RechargeAdapter;
import com.qiantang.neighbourmother.business.data.GetRechargeMoneyHttp;
import com.qiantang.neighbourmother.business.data.RechargeAliHttp;
import com.qiantang.neighbourmother.business.data.RechargeWxHttp;
import com.qiantang.neighbourmother.business.pay.OtherContacts;
import com.qiantang.neighbourmother.business.pay.WeixinPay;
import com.qiantang.neighbourmother.business.pay.ZfbPay;
import com.qiantang.neighbourmother.business.request.RechargeReq;
import com.qiantang.neighbourmother.business.response.AliPayResp;
import com.qiantang.neighbourmother.business.response.OrderPayResp;
import com.qiantang.neighbourmother.model.RechargeObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.FixationHeightGridView;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.ArrayList;

/**
 * ClassName:用户充值
 * author: Cocoa
 * date: 2016/9/21.
 */
public class RechargeActivity extends BaseActivity implements View.OnClickListener,ZfbPay.AliPayResListener {
    private ImageView back;//返回
    private FixationHeightGridView dgridview;
    private EditText ev_recharge;
    private RelativeLayout rl_wx_pay;
    private RelativeLayout rl_ali_pay;

    private RechargeAdapter rechargeAdapter;

    private int moneyType;

    @Override
    public int getContentView() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        dgridview = (FixationHeightGridView) findViewById(R.id.dgridview);
        ev_recharge = (EditText) findViewById(R.id.ev_recharge);
        rl_wx_pay = (RelativeLayout) findViewById(R.id.rl_wx_pay);
        rl_ali_pay = (RelativeLayout) findViewById(R.id.rl_ali_pay);
    }

    @Override
    public void initData() {
        weixinPay = new WeixinPay(this, this);
        rechargeAdapter=new RechargeAdapter(this);
        dgridview.setAdapter(rechargeAdapter);

        new GetRechargeMoneyHttp(this,handler,1);
    }

    private boolean clearIndex=true;
    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        rl_wx_pay.setOnClickListener(this);
        rl_ali_pay.setOnClickListener(this);
        dgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rechargeAdapter.selected(position);
                clearIndex=false;
                ev_recharge.setText("");
            }
        });

        ev_recharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(clearIndex)
                rechargeAdapter.selected(-1);

                if("".equals(s.toString())){
                    clearIndex=true;
                }
            }
        });
    }


    private WeixinPay weixinPay;
    private ZfbPay zfbPay;
    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                ArrayList<RechargeObj> rechargeObjs=(ArrayList<RechargeObj>)msg.obj;
                rechargeAdapter.getDataList().addAll(rechargeObjs);
                rechargeAdapter.notifyDataSetChanged();
                closeProgressDialog();
                break;
            case 2:
                OrderPayResp orderPayResp = (OrderPayResp) msg.obj;
                PropertyConfig.getInstance(this).save(IntentFinal.INTENT_WX_PAY_TYPE, 1);
                if (weixinPay.wxhasUser()) {
                    weixinPay.sendPayReq(orderPayResp);
                }
                closeProgressDialog();
                break;
            case 3:
                AliPayResp aliPayResp = (AliPayResp) msg.obj;
//                PropertyConfig.getInstance(this).save(PayResultService.PREF_PAY_RESULT_OBJ, aliPayResp.getPay_id());
                if (zfbPay == null) {
                    zfbPay = new ZfbPay(RechargeActivity.this, handler);
                    zfbPay.setOnAliPayResListener(this);
                }
                zfbPay.request(aliPayResp);
                closeProgressDialog();
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_wx_pay:
                pay(1);
                break;
            case R.id.rl_ali_pay:
                pay(2);
                break;
    }
    }

    private int getMoney(){
        int money=0;
        String sev=ev_recharge.getText().toString().trim();
        if(!TextUtils.isEmpty(sev)){
            money=Integer.valueOf(sev)*100;
            moneyType=2;
        }

       if(money<=0){
            RechargeObj rechargeObj=rechargeAdapter.getSelected();
            if(rechargeObj!=null){
                money=rechargeObj.getMoney();
                moneyType=1;
            }
        }

        if(money<=0)
            ToastUtils.toastLong(this,R.string.recharge_money_be_more_zero);
        return money;
    }

//    1微信2阿里
    private void pay(int type){
        int money=getMoney();
        if(money<=0)
            return;

        switch (type){
            case 1:

                IWXAPI api = WXAPIFactory.createWXAPI(this, OtherContacts.WEIXIN_APP_ID);
                boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                if (isPaySupported) {
                    new RechargeWxHttp(this,handler,new RechargeReq(money,moneyType,type),2);
                } else {
                    ToastUtils.toastLong(this, R.string.orderpay_not_support_wxpay);
                }

                break;
            case 2:
                new RechargeAliHttp(this,handler,new RechargeReq(money,moneyType,type),3);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppLog.D("OrderPayActivity.onResume");
        if (PropertyConfig.getInstance(this).getInt(IntentFinal.PREF_PAY_SUCCESS_FAG, 0) == 1) {
            PropertyConfig.getInstance(this).save(IntentFinal.PREF_PAY_SUCCESS_FAG, 0);
            paySuccessFinish();
        }
    }

    public void paySuccessFinish() {
        setResult(10);
        finish();
    }

    @Override
    public void onAliPayRes(int type) {
        if(type==1)
            paySuccessFinish();
    }
}
