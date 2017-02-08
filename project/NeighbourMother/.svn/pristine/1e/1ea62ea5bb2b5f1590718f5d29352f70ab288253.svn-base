package com.qiantang.neighbourmother.ui.center.attache;

import android.content.DialogInterface;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.BindBankCardHttp;
import com.qiantang.neighbourmother.business.request.BindBankCardReq;
import com.qiantang.neighbourmother.business.response.CenterMoneyResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.BindBankDialog;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * ClassName:绑定银行卡
 * author: Cocoa
 * date: 2016/9/23.
 */
public class AttacheBindBankCardActivity extends BaseActivity implements View.OnClickListener {

    private ImageView       back;//返回
    private EditText        open_bank;//开户行
    private EditText        bank_card_no;//银行卡号
    private EditText        open_bank_name;//户名
    private TextView        open_bank_bind_status;//绑定状态
    private TextView        btn_binding;//绑定提交信息
    private ImageView       iv_status;//状态图标
    private LinearLayout    ll_bind_back;//根布局
    private MyTextWatcher   myTextWatcher;
    private CenterMoneyResp centerMoneyResp;
    private BindBankCardReq bindBankCardReq;

    @Override
    public int getContentView() {
        return R.layout.activity_attache_bind_bank_card;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        open_bank = (EditText) findViewById(R.id.open_bank);
        bank_card_no = (EditText) findViewById(R.id.bank_card_no);
        open_bank_name = (EditText) findViewById(R.id.open_bank_name);
        open_bank_bind_status = (TextView) findViewById(R.id.open_bank_bind_status);
        btn_binding = (TextView) findViewById(R.id.btn_binding);
        iv_status = (ImageView) findViewById(R.id.iv_status);
        ll_bind_back = (LinearLayout) findViewById(R.id.ll_bind_back);
    }

    @Override
    public void initData() {
        myTextWatcher = new MyTextWatcher();
        bank_card_no.addTextChangedListener(myTextWatcher);
        centerMoneyResp = (CenterMoneyResp) getIntent().getSerializableExtra(IntentFinal.INTENT_ATTACHE_ACCOUNT_MONEY_BIND_BANK);
        setData(centerMoneyResp);

    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        btn_binding.setOnClickListener(this);
        bank_card_no.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    bank_card_no.setText("");
                    count = 0;
                }
                return false;
            }
        });


    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
//            case 1:
//                centerMoneyResp = (CenterMoneyResp) msg.obj;
//
//                break;
            case 2:
                showDialog(bindBankCardReq);
                setData(centerMoneyResp);
                break;
        }
        closeProgressDialog();
    }

    BindBankDialog dialog;

    private void showDialog(BindBankCardReq bindBankCardReq) {
        if (dialog == null) {
            dialog = new BindBankDialog(this, bindBankCardReq);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        dialog.setOnBindBankListener(new BindBankDialog.BindBankListener() {
            @Override
            public void onBindBankComplete() {
                dialog.dismiss();
                setResult(5);
                finish();
            }
        });
    }

    private void setData(CenterMoneyResp centerMoneyResp) {
        if (centerMoneyResp != null) {
            open_bank.setText(centerMoneyResp.getOpen_bank());
            bank_card_no.setText(centerMoneyResp.getCard_no());
            open_bank_name.setText(centerMoneyResp.getAccount_name());
            if (!TextUtils.isEmpty(centerMoneyResp.getCard_no())) {
                open_bank_bind_status.setVisibility(View.VISIBLE);
                iv_status.setVisibility(View.VISIBLE);
            }
        } else {
            open_bank_bind_status.setVisibility(View.INVISIBLE);
            iv_status.setVisibility(View.INVISIBLE);
        }
        ll_bind_back.setVisibility(View.VISIBLE);
    }

    private boolean checkInput(String openBank, String bankCard, String account) {
        if (TextUtils.isEmpty(openBank)) {
            ToastUtils.toastshort(this, R.string.open_bank_isempty);
            return false;
        }
        if (TextUtils.isEmpty(bankCard)) {
            ToastUtils.toastshort(this, R.string.open_bank_card_isempty);
            return false;
        }
        if (TextUtils.isEmpty(account)) {
            ToastUtils.toastshort(this, R.string.bank_account);
            return false;
        }

        if (centerMoneyResp != null) {
            if (centerMoneyResp.getAccount_name().equals(account) && centerMoneyResp.getCard_no().equals(bankCard) && centerMoneyResp.getOpen_bank().equals(openBank)) {
                ToastUtils.toastshort(this, R.string.bank_info_update);
                return false;
            } else {
                return true;
            }
        }

        bankCard = bankCard.replace(" ", "");
        AppLog.D("bankCard：" + bankCard);
        if (bankCard.length() > 20 || bankCard.length() < 13) {
            ToastUtils.toastshort(this, R.string.bank_card_length);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_binding:
                String openBank = open_bank.getText().toString().trim();
                String bankCard = bank_card_no.getText().toString().trim();
                String account = open_bank_name.getText().toString().trim();
                if (checkInput(openBank, bankCard, account)) {
                    bindBankCardReq = new BindBankCardReq(bankCard, openBank, account);
                    new BindBankCardHttp(this, handler, bindBankCardReq, 2);
                }
                break;
            case R.id.back:
                setResult(5);
                finish();
                break;
        }
    }

    private int count = 0;

    private class MyTextWatcher implements TextWatcher {
        CharSequence sequ = null;


        @Override
        public void afterTextChanged(Editable s) {
            int textSum = s.length();
            AppLog.D("textSum:" + textSum + "s:" + s.toString());
            if (count < 3) {
                switch (textSum) {
                    case 4:
                    case 9:
                    case 14:
                        bank_card_no.setText(s.toString() + " ");
                        bank_card_no.setSelection(bank_card_no.getText().toString().length());
                        count++;
                        break;
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            sequ = s;
        }

    }
}
