package com.qiantang.neighbourmother.ui.order.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.data.AccountPayHttp;
import com.qiantang.neighbourmother.business.data.AliPayHttp;
import com.qiantang.neighbourmother.business.data.GetAccountSurplusHttp;
import com.qiantang.neighbourmother.business.data.WxPayHttp;
import com.qiantang.neighbourmother.business.pay.OtherContacts;
import com.qiantang.neighbourmother.business.pay.WeixinPay;
import com.qiantang.neighbourmother.business.pay.ZfbPay;
import com.qiantang.neighbourmother.business.request.OrderPayReq;
import com.qiantang.neighbourmother.business.response.AliPayResp;
import com.qiantang.neighbourmother.business.response.OrderDetailResp;
import com.qiantang.neighbourmother.business.response.OrderPayResp;
import com.qiantang.neighbourmother.business.response.PayAccountSurplusResp;
import com.qiantang.neighbourmother.service.PayResultService;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.AccountPayDialog;
import com.qiantang.neighbourmother.ui.home.DownOrderActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;
import com.qiantang.neighbourmother.util.QLTimeUtil;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.CircleImageView;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/**
 * ClassName:订单支付
 * author: Cocoa
 * date: 2016/10/12.
 */

public class OrderPayActivity extends BaseActivity implements View.OnClickListener,
        AccountPayDialog.AccountPayListener,ZfbPay.AliPayResListener{

    private TextView title;
    private ImageView back;//返回
    private CircleImageView person_headimg;//专员头像
    private ImageView attache_sex;//性别
    private TextView person_name;//专员姓名
    private TextView attache_age;//专员年龄
    private TextView attache_post;//专员行业
    private TextView person_service_type;//专员服务类型
    private TextView person_address;//专员地址

    private TextView service_content;//服务内容
    private TextView down_order_date;//下单时间
    private TextView order_no;//订单编号
    private TextView pay_money;//待付款
    private RelativeLayout rl_wechat_pay;//微信支付
    private RelativeLayout rl_ali_pay;//支付宝支付
    private RelativeLayout rl_account_pay;//账户余额支付
    private ScrollView scrollView;
    private OrderDetailResp orderDetailResp;

    private WeixinPay weixinPay;
    private int payType;

    @Override
    public int getContentView() {
        return R.layout.activity_order_pay;
    }

    @Override
    public void initView() {
        title = (TextView) findViewById(R.id.title);
        back = (ImageView) findViewById(R.id.back);
        rl_ali_pay = (RelativeLayout) findViewById(R.id.rl_ali_pay);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        attache_sex = (ImageView) findViewById(R.id.attache_sex);
        person_headimg = (CircleImageView) findViewById(R.id.person_headimg);
        person_name = (TextView) findViewById(R.id.person_name);
        attache_age = (TextView) findViewById(R.id.attache_age);
        attache_post = (TextView) findViewById(R.id.attache_post);
        person_service_type = (TextView) findViewById(R.id.person_service_type);
        person_address = (TextView) findViewById(R.id.person_address);

        service_content = (TextView) findViewById(R.id.service_content);
        down_order_date = (TextView) findViewById(R.id.down_order_date);
        order_no = (TextView) findViewById(R.id.order_no);
        pay_money = (TextView) findViewById(R.id.pay_money);
        rl_wechat_pay = (RelativeLayout) findViewById(R.id.rl_wechat_pay);
        rl_account_pay = (RelativeLayout) findViewById(R.id.rl_account_pay);
    }

    @Override
    public void initData() {
        weixinPay = new WeixinPay(this, this);
        orderDetailResp = (OrderDetailResp) getIntent().getSerializableExtra(IntentFinal.INTENT_ORDER_DETAIL_OBJ);
        payType = getIntent().getIntExtra(IntentFinal.INTENT_PAY_TYPE, 0);
        setData(orderDetailResp);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        rl_wechat_pay.setOnClickListener(this);
        rl_ali_pay.setOnClickListener(this);
        rl_account_pay.setOnClickListener(this);
    }

    private ZfbPay zfbPay;
    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                OrderPayResp orderPayResp = (OrderPayResp) msg.obj;
                PropertyConfig.getInstance(this).save(PayResultService.PREF_PAY_RESULT_OBJ, orderPayResp.getPay_id());
                if (weixinPay.wxhasUser()) {
                    weixinPay.sendPayReq(orderPayResp);
                }
                break;

            case 2:
                AliPayResp aliPayResp = (AliPayResp) msg.obj;
                PropertyConfig.getInstance(this).save(PayResultService.PREF_PAY_RESULT_OBJ, aliPayResp.getPay_id());
                if (zfbPay == null) {
                    zfbPay = new ZfbPay(OrderPayActivity.this, handler);
                    zfbPay.setOnAliPayResListener(this);
                }
                zfbPay.request(aliPayResp);
                break;
            case 3:
                PayAccountSurplusResp payAccountSurplusResp=(PayAccountSurplusResp)msg.obj;
                if(payAccountSurplusResp.getMoney()-orderDetailResp.getOrder().getMoney()<=0){
                    ToastUtils.toastLong(this,R.string.order_pay_surplus_money_not_enough);
                }else{

                    switch (payType){
                        case 1:
                            payAccountSurplusResp.setSpendMoney(orderDetailResp.getOrder().getMoney());
                            initDialog(payAccountSurplusResp);
                            break;
                        case 2:
                            payAccountSurplusResp.setSpendMoney(orderDetailResp.getOrder().getAdditional_money());
                            initDialog(payAccountSurplusResp);
                            break;
                    }


                }
                closeProgressDialog();
                break;
            case 4:
                ToastUtils.toastLong(this,R.string.orderpay_pay_success);
                closeProgressDialog();
                paySuccessFinish();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_wechat_pay:
                IWXAPI api = WXAPIFactory.createWXAPI(this, OtherContacts.WEIXIN_APP_ID);
                boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                AppLog.D("isPaySupported:" + isPaySupported);
                if (isPaySupported) {
                    new WxPayHttp(this, handler, new OrderPayReq(orderDetailResp.getOrder().getOrder_id(), payType), 1);
                } else {
                    ToastUtils.toastLong(this, R.string.orderpay_not_support_wxpay);
                }
                break;
            case R.id.rl_ali_pay:
                new AliPayHttp(this, handler, new OrderPayReq(orderDetailResp.getOrder().getOrder_id(), payType), 2);
                break;
            case R.id.rl_account_pay:
                new GetAccountSurplusHttp(this,handler,3);
                break;
        }
    }

    private void setData(OrderDetailResp orderDetailResp) {

        display(person_headimg, APIConfig.BASE_IMG_URL + orderDetailResp.getServant().getUser_avatar(), R.mipmap.default_img);

        attache_sex.setSelected(orderDetailResp.getServant().getUser_gender() == 2);
        person_name.setText(orderDetailResp.getServant().getUser_name());

        attache_age.setText(orderDetailResp.getServant().getUser_age() + "岁");
        attache_post.setText(orderDetailResp.getServant().getIndustry());

        StringBuilder serType = new StringBuilder();
        for (String str : orderDetailResp.getServant().getServant_type_array_string()) {
            serType.append(str + " ");
        }
        person_service_type.setText(serType);
        person_address.setText(orderDetailResp.getServant().getProvince_name() + orderDetailResp.getServant().getCity_name() + orderDetailResp.getServant().getDistrict_name() + orderDetailResp.getServant().getUser_community() + orderDetailResp.getServant().getUser_address());

        service_content.setText(orderDetailResp.getOrder().getOrder_title());
        down_order_date.setText(QLTimeUtil.getStringTime(orderDetailResp.getOrder().getCtime() * 1000, QLTimeUtil.TIME_MODEL_1));
        order_no.setText(orderDetailResp.getOrder().getOrder_no());

        switch (payType) {
            case 1:
                title.setText(R.string.order_pay_title);
                pay_money.setText(new java.text.DecimalFormat("0.00").format((float) orderDetailResp.getOrder().getMoney() / 100));
                break;
            case 2:
                title.setText(R.string.order_pay_title_addMoney);
                pay_money.setText(new java.text.DecimalFormat("0.00").format((float) orderDetailResp.getOrder().getAdditional_money() / 100));
                break;
        }

        scrollView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private AccountPayDialog accountPayDialog;
    private void initDialog(PayAccountSurplusResp payAccountSurplusResp){
        if(accountPayDialog==null){
            accountPayDialog=new AccountPayDialog();
            accountPayDialog.setOnAccountPayListener(this);
        }

        Bundle bundle=new Bundle();
        bundle.putSerializable(IntentFinal.INTENT_PAY_DIALOG_OBJ,payAccountSurplusResp);
        accountPayDialog.setArguments(bundle);
        accountPayDialog.show(getSupportFragmentManager(),"accountPayDialog");
    }

    @Override
    public void onAccountPay() {
        new AccountPayHttp(this,handler,new OrderPayReq(orderDetailResp.getOrder().getOrder_id(),payType),4);
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
        setResult(DownOrderActivity.RESULT_PAY_RESULT_SUCCESS);
        finish();
    }

    @Override
    public void onAliPayRes(int type) {
        if(type==1)
            paySuccessFinish();
    }
}
