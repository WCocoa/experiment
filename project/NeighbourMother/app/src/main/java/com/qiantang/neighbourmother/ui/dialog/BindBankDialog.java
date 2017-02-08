package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.request.BindBankCardReq;


/**
 * 绑定银行卡dialog
 */
public class BindBankDialog extends Dialog {
    private BindBankListener bindBankListener;

    private Context         context;
    private BindBankCardReq bindBankCardReq;

    public BindBankDialog(Context context, BindBankCardReq bindBankCardReq) {
        super(context);
        this.context = context;
        this.bindBankCardReq = bindBankCardReq;
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = LayoutInflater.from(context);
        View           view     = inflater.inflate(R.layout.dialog_bind_bank, null);
        setContentView(view);
        TextView open_bank      = (TextView) view.findViewById(R.id.open_bank);
        TextView open_bank_card = (TextView) view.findViewById(R.id.open_bank_card);
        TextView bank_account   = (TextView) view.findViewById(R.id.bank_account);
        TextView dialog_sure    = (TextView) view.findViewById(R.id.dialog_sure);
        setData(open_bank, open_bank_card, bank_account);


//        Window dialogWindow = getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
//        dialogWindow.setAttributes(lp);
        dialog_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bindBankListener != null) {
                    bindBankListener.onBindBankComplete();
                }
            }
        });

    }

    private void setData(TextView open_bank, TextView open_bank_card, TextView bank_account) {
        if (bindBankCardReq != null) {
            open_bank.setText(bindBankCardReq.getOpen_bank());
            open_bank_card.setText(bindBankCardReq.getCard_no());
            bank_account.setText(bindBankCardReq.getAccount_name());
        }
    }


    public void setOnBindBankListener(BindBankListener bindBankListener) {
        this.bindBankListener = bindBankListener;
    }

    public interface BindBankListener {
        void onBindBankComplete();
    }
}
