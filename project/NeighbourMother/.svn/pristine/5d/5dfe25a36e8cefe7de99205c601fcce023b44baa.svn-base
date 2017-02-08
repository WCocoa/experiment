package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.PayAccountSurplusResp;
import com.qiantang.neighbourmother.util.IntentFinal;

import java.text.DecimalFormat;


/**
 * Created by Administrator on 2015/11/12.
 * 账户余额支付确认
 */
public class AccountPayDialog extends DialogFragment {
    private View defineView;
    private TextView sure;
    private ImageView dap_delete;
    private TextView dap_need_pay;
    private TextView dap_curr_money;
    private TextView dap_surplus_money;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        defineView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_account_pay, null);
        sure = (TextView) defineView.findViewById(R.id.sure);
        dap_delete = (ImageView) defineView.findViewById(R.id.dap_delete);
        dap_need_pay = (TextView) defineView.findViewById(R.id.dap_need_pay);
        dap_curr_money = (TextView) defineView.findViewById(R.id.dap_curr_money);
        dap_surplus_money = (TextView) defineView.findViewById(R.id.dap_surplus_money);

        DecimalFormat decimalFormat=new DecimalFormat("0.00");

        Bundle bundle=getArguments();
        PayAccountSurplusResp payAccountSurplusResp=(PayAccountSurplusResp)bundle.getSerializable(IntentFinal.INTENT_PAY_DIALOG_OBJ);

        dap_need_pay.setText(String.valueOf(decimalFormat.format((float)payAccountSurplusResp.getSpendMoney()/100)));
        dap_curr_money.setText(String.valueOf(decimalFormat.format((float)payAccountSurplusResp.getMoney()/100)));

        int surplus=payAccountSurplusResp.getMoney()-payAccountSurplusResp.getSpendMoney();
        dap_surplus_money.setText(String.valueOf(decimalFormat.format((float)surplus/100)));

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (accountPayListener != null)
                    accountPayListener.onAccountPay();
            }
        });
        dap_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(defineView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private AccountPayListener accountPayListener;
    public void setOnAccountPayListener(AccountPayListener accountPayListener) {
        this.accountPayListener = accountPayListener;
    }

    public interface AccountPayListener {
        void onAccountPay();
    }


}
