/*
 * 官网地站:http://www.ShareSDK.cn
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 ShareSDK.cn. All rights reserved.
 */

package com.qiantang.neighbourmother.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.pay.OtherContacts;
import com.qiantang.neighbourmother.util.AppLog;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/**
 * 微信客户端回调activity示例
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    public static String TRANSACTION_SHARED="webpage";
//    public static String TRANSACTION_login="webpage";

    private IWXAPI api;
    private String code;

    private Handler mHandler;

//    private WXTokenObj wxTokenObj;
    // private UserObj userObj;
//    private WeixinLoginResultResp weixinResultResp;

    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpayentry);

        api = WXAPIFactory.createWXAPI(this, OtherContacts.WEIXIN_APP_ID,
                false);
        api.registerApp(OtherContacts.WEIXIN_APP_ID);
        api.handleIntent(getIntent(), this);
//        initHandler();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq arg0) {
        // TODO Auto-generated method stub
        AppLog.D("onReq");
    }

    @Override
    public void onResp(BaseResp resp) {
        // resp.transaction
        // TODO Auto-generated method stub
        AppLog.D("resp.errCode"+resp.errCode);
        finish();
    }





}
