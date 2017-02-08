package com.qiantang.neighbourmother.ui.center.attache;

import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.WithdrawsDepositHttp;
import com.qiantang.neighbourmother.business.request.WithdrawsDepositReq;
import com.qiantang.neighbourmother.business.response.CenterMoneyResp;
import com.qiantang.neighbourmother.logic.MoneyInputFlter;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.WithdrawDepositDialog;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * ClassName:提现
 * author: Cocoa
 * date: 2016/9/23.
 */
public class AttacheWithdrawDepositActivity extends BaseActivity implements View.OnClickListener {

    private ImageView       back;//返回
    private TextView        bank_card_no;//绑定的银行卡号
    private EditText        et_wd_money;//提现金额输入框
    private TextView        blanace_text;//余额
    private TextView        wd_all_money;//全部提现
    private TextView        btn_sure;//完成
    private CenterMoneyResp centerMoneyResp;
    private MyTextWatcher   myTextWatcher;

    @Override
    public int getContentView() {
        return R.layout.activity_attache_withdraw_deposit;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        bank_card_no = (TextView) findViewById(R.id.bank_card_no);
        et_wd_money = (EditText) findViewById(R.id.et_wd_money);
        blanace_text = (TextView) findViewById(R.id.blanace_text);
        wd_all_money = (TextView) findViewById(R.id.wd_all_money);
        btn_sure = (TextView) findViewById(R.id.btn_sure);

    }

    @Override
    public void initData() {
        myTextWatcher = new MyTextWatcher();
        InputFilter[] inputFilters = new InputFilter[]{new MoneyInputFlter()};
        et_wd_money.setFilters(inputFilters);
        et_wd_money.addTextChangedListener(myTextWatcher);
        centerMoneyResp = (CenterMoneyResp) getIntent().getSerializableExtra(IntentFinal.INTENT_ATTACHE_ACCOUNT_MONEY_WITHDRAW_DEPOSIT);
//        new GetCenterMoneyHttp(this, handler, CENTER_MONEY + "?type=" + 2, 2, true);
        setData(centerMoneyResp);
    }


    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        wd_all_money.setOnClickListener(this);


    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
//                ToastUtils.toastshort(this, "审核中");
                showDialog();
                type = 1;
                break;
//            case 2:
//                centerMoneyResp = (CenterMoneyResp) msg.obj;
//
//                break;
        }
        closeProgressDialog();

    }

    private void setData(CenterMoneyResp centerMoneyResp) {
        if (centerMoneyResp != null) {
            bank_card_no.setText("尾号为" + centerMoneyResp.getCard_no().substring(centerMoneyResp.getCard_no().length() - 4, centerMoneyResp.getCard_no().length()) + "银行卡");
            blanace_text.setText(getResources().getString(R.string.use_blance) + String.format("%1.2f", centerMoneyResp.getMoney() / 100));
        }
    }

    WithdrawDepositDialog dialog;

    private void showDialog() {
        if (dialog == null) {
            dialog = new WithdrawDepositDialog();
        }
        dialog.show(getSupportFragmentManager(), "AttacheWithdrawDepositActivity");
        dialog.setOnWithdrawDepositListener(new WithdrawDepositDialog.OnWithdrawDepositListener() {
            @Override
            public void onClickConfrim() {
                et_wd_money.setText("");
                finish();
            }
        });
    }

    private int type = 1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_sure:

                String money = et_wd_money.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {
                    ToastUtils.toastshort(this, R.string.wd_isempty);
                } else {
                    double dmoney = Double.valueOf(money);
                    if (dmoney > centerMoneyResp.getMoney()) {
                        ToastUtils.toastshort(this, R.string.wd_blance);
                    } else {
                        WithdrawsDepositReq withdrawsDepositReq = new WithdrawsDepositReq(type, dmoney * 100);
                        new WithdrawsDepositHttp(this, handler, withdrawsDepositReq, 1);
                    }
                }
                break;
            case R.id.wd_all_money:
                type = 2;
                et_wd_money.setText(String.format("%1.2f", centerMoneyResp.getMoney() / 100));
                et_wd_money.setSelection(et_wd_money.getText().toString().length());
                break;
        }
    }

    class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().startsWith(".")) {
                s.clear();
                return;
            }
            if (s.length() > 0) {
                double money = Double.parseDouble(s.toString());
                AppLog.D("money:" + money + "centerMoneyResp.getMoney():" + centerMoneyResp.getMoney());
                if (centerMoneyResp.getMoney() / 100 == money) {
                    type = 2;
                } else {
                    type = 1;
                }
            }
        }
    }
}