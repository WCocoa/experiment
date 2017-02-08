package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * Created by Administrator on 2015/11/12.
 * 账户余额支付确认
 */
public class TeacherJoinDialog extends DialogFragment {
    private View defineView;
    private TextView sure;
    private ImageView delete;
    private EditText name;
    private EditText tel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        defineView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_teacherjoin, null);
        sure = (TextView) defineView.findViewById(R.id.sure);
        delete = (ImageView) defineView.findViewById(R.id.delete);
        name = (EditText) defineView.findViewById(R.id.name);
        tel = (EditText) defineView.findViewById(R.id.tel);


//        Bundle bundle=getArguments();
//        PayAccountSurplusResp payAccountSurplusResp=(PayAccountSurplusResp)bundle.getSerializable(IntentFinal.INTENT_PAY_DIALOG_OBJ);
//
//        dap_need_pay.setText(String.valueOf(decimalFormat.format((float)payAccountSurplusResp.getSpendMoney()/100)));
//        dap_curr_money.setText(String.valueOf(decimalFormat.format((float)payAccountSurplusResp.getMoney()/100)));
//
//        int surplus=payAccountSurplusResp.getMoney()-payAccountSurplusResp.getSpendMoney();
//        dap_surplus_money.setText(String.valueOf(decimalFormat.format((float)surplus/100)));
//
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String str_name= name.getText().toString().trim();
               String str_tel= tel.getText().toString().trim();
                if(TextUtils.isEmpty(str_name)){
                    ToastUtils.toastLong(getActivity(),R.string.teacherjoin_d_name_hint);
                    return;
                }

                if(str_tel.length()<11){
                    ToastUtils.toastLong(getActivity(),R.string.teacherjoin_d_tel_hint);
                    return;
                }

                if (teacherJoinListener != null)
                    teacherJoinListener.onTeacherJoin(str_name,str_tel);
                dismiss();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Dialog dialog = new Dialog(getActivity(),R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(defineView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private TeacherJoinListener teacherJoinListener;
    public void setOnAccountPayListener(TeacherJoinListener teacherJoinListener) {
        this.teacherJoinListener = teacherJoinListener;
    }

    public interface TeacherJoinListener {
        void onTeacherJoin(String name,String tel);
    }


}
