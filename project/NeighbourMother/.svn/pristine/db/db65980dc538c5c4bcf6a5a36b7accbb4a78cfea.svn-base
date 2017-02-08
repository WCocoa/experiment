package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.OrderDetailResp;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;

import java.text.DecimalFormat;


/**
 * Created by Administrator on 2015/11/12.
 * 专员资料提交成功
 */
public class AddMoneyDialog extends DialogFragment {
    private View defineView;
    private TextView sure;
    private TextView money;
    private TextView reason;
    private OrderDetailResp orderDetailResp;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        defineView= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_money,null);
        money=(TextView) defineView.findViewById(R.id.money);
        reason=(TextView) defineView.findViewById(R.id.reason);
        sure=(TextView) defineView.findViewById(R.id.sure);

        money.setText(new DecimalFormat("0.00").format((float)orderDetailResp.getOrder().getAdditional_money()/100));
        reason.setText(orderDetailResp.getOrder().getAdditional_reason());

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String str_money=money.getText().toString().trim();
//                String str_reason=reason.getText().toString().trim();

//                if(!TextUtils.isEmpty(str_money)&&!TextUtils.isEmpty(str_reason)){
//                    ToastUtils.toastLong(getActivity(),R.string.addmoney_please_finish_info);
//                    return ;
//                }
                    dismiss();
                    if(sureAddMoneyListener!=null)
                        sureAddMoneyListener.onAddMoneySure();
            }
        });

        Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(defineView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
////                if(keyCode==KeyEvent.KEYCODE_BACK)
//                    return true;
//                else
//                return false;
//            }
//        });
                return dialog;
    }

    private SureAddMoneyListener sureAddMoneyListener;
    public void setOnAddMoneyListener(SureAddMoneyListener sureAddMoneyListener){
        this.sureAddMoneyListener=sureAddMoneyListener;
    }

    public interface SureAddMoneyListener {
         void onAddMoneySure();
    }


    public OrderDetailResp getOrderDetailResp() {
        return orderDetailResp;
    }

    public void setOrderDetailResp(OrderDetailResp orderDetailResp) {
        this.orderDetailResp = orderDetailResp;
    }
}
