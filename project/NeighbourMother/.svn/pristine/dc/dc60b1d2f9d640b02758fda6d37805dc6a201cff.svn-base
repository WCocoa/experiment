package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.SuperadditionCostHttp;
import com.qiantang.neighbourmother.business.request.SuperadditionCostReq;
import com.qiantang.neighbourmother.logic.PriceInputFlter;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * Created by Administrator on 2015/11/12.
 * 追加费用
 */
public class OrderSuperadditionCostDialog extends DialogFragment {

    private MyTextWatcher textWatcher;
    private View defineView;
    private TextView cancel;
    private TextView sure;
    private EditText add_money;
    private EditText input_add_content;
    private Context context;
    private Handler handler;
    private String order_id;

    public void setData(Context context, Handler handler, String order_id) {
        this.context = context;
        this.handler = handler;
        this.order_id = order_id;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        textWatcher = new MyTextWatcher();
        defineView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_order_superaddition_cost, null);
        cancel = (TextView) defineView.findViewById(R.id.cancel);
        sure = (TextView) defineView.findViewById(R.id.sure);
        add_money = (EditText) defineView.findViewById(R.id.add_money);
        input_add_content = (EditText) defineView.findViewById(R.id.input_add_content);
        InputFilter[] inputFilters = {new PriceInputFlter()};
        add_money.setFilters(inputFilters);
        add_money.addTextChangedListener(textWatcher);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (deleteOrderListener != null) {
//                    deleteOrderListener.onCancel();
//                }
                dismiss();
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (deleteOrderListener != null) {
//                    deleteOrderListener.onSure();
//                }
                String addmoney = add_money.getText().toString().trim();
                String content = input_add_content.getText().toString().trim();
                if (TextUtils.isEmpty(addmoney)) {
                    ToastUtils.toastLong(getActivity(), "请输入追加金额！");
                    return;
                }
                double money = Double.parseDouble(addmoney);
                if (money <= 0) {
                    ToastUtils.toastLong(getActivity(), "追加金额不能为0！");
                    return;
                }
                money = money*100;


                if (TextUtils.isEmpty(content)) {
                    ToastUtils.toastLong(getActivity(), "请简述追加理由！");
                    return;
                }

                new SuperadditionCostHttp(context, handler, new SuperadditionCostReq(order_id, content, money), 5, true);
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

    private class MyTextWatcher implements TextWatcher {
        CharSequence changeChars = null;


        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().trim().startsWith(".")) {
                s.clear();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            changeChars = s;
        }
    }

//    private DeleteOrderListener deleteOrderListener;
//
//    public void setOnSureListener(DeleteOrderListener deleteOrderListener) {
//        this.deleteOrderListener = deleteOrderListener;
//    }
//
//    public interface DeleteOrderListener {
//        public void onSure();
//
//        public void onCancel();
//    }
}
