package com.qiantang.neighbourmother.ui.center.user;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.PayRecordAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.AccountBalanceHttp;
import com.qiantang.neighbourmother.business.response.AccountBalanceResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

/**
 * ClassName:用户账户
 * author: Cocoa
 * date: 2016/9/23.
 */
public class UserAccountActivity extends BaseActivity implements View.OnClickListener {

    private ImageView        back;
    private TextView         blance_money;//余额
    private RelativeLayout   rl_pay;//充值
    private XListView        listview;//充值记录
    private PayRecordAdapter payRecordAdapter;

    private RelativeLayout rl_top;
    private LinearLayout   ll_operation;

    @Override
    public int getContentView() {
        return R.layout.activity_user_account;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        blance_money = (TextView) findViewById(R.id.blance_money);
        rl_top = (RelativeLayout) findViewById(R.id.rl_top);
        rl_pay = (RelativeLayout) findViewById(R.id.rl_pay);
        ll_operation = (LinearLayout) findViewById(R.id.ll_operation);
        listview = (XListView) findViewById(R.id.listview);
    }

    @Override
    public void initData() {
        listview.setPullRefreshEnable(false);
        payRecordAdapter = new PayRecordAdapter(this);
        listview.setAdapter(payRecordAdapter);
        listview.setPullLoadEnable(false);
        getHttpData(true, true, 1);
    }

    private void getHttpData(boolean isFirst, boolean isShowProgress, int what) {
        int count = isFirst == true ? 0 : payRecordAdapter.getDataList().size();
        new AccountBalanceHttp(this, handler, count, what, isShowProgress);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        rl_pay.setOnClickListener(this);
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getHttpData(true, true, 1);
            }

            @Override
            public void onLoadMore() {
                getHttpData(false, false, 2);
            }
        });
    }

    private AccountBalanceResp accountBalanceResp;

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                accountBalanceResp = (AccountBalanceResp) msg.obj;
                setData(accountBalanceResp, true);
                break;
            case 2:
                accountBalanceResp = (AccountBalanceResp) msg.obj;
                setData(accountBalanceResp, false);
                break;
        }
        closeProgressDialog();
    }


    private void setData(AccountBalanceResp accountBalanceResp, boolean isFirst) {
        blance_money.setText((String.format("%1.2f", accountBalanceResp.getMoney() / 100)));
        rl_top.setVisibility(View.VISIBLE);
        ll_operation.setVisibility(View.VISIBLE);
        if (isFirst) {
            payRecordAdapter.getDataList().clear();
        }
        if (accountBalanceResp.getList() != null && accountBalanceResp.getList().size() > 0) {
            payRecordAdapter.getDataList().addAll(accountBalanceResp.getList());
            if (accountBalanceResp.getList().size() < 10) {
                listview.setPullLoadEnable(false);
            } else {
                listview.setPullLoadEnable(true);
            }
        } else {
//            ToastUtils.toastshort(this, isFirst == true ? getString(R.string.app_pull_not_data) : getString(R.string.app_pull_already_last_page));
        }
        payRecordAdapter.notifyDataSetChanged();
        listview.aotuRefreshComplete();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_pay:
                startActivityForResult(new Intent(this, RechargeActivity.class),1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if(resultCode==10){
        getHttpData(true, true, 1);
    }
    }
}
