package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;

/**
 * ClassName:下单页面孩子信息未填写提示
 * author: Cocoa
 * date: 2017/1/16.
 */

public class OrderChildInfoDialog extends DialogFragment {

    private View defineView;
    private TextView sure;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        defineView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_order_child_info, null);
        sure = (TextView) defineView.findViewById(R.id.sure);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderChildInfoListener != null) {
                    orderChildInfoListener.onSure();
                }
            }
        });

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(defineView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


    private OrderChildInfoListener orderChildInfoListener;

    public void setOnSureListener(OrderChildInfoListener orderChildInfoListener) {
        this.orderChildInfoListener = orderChildInfoListener;
    }

    public interface OrderChildInfoListener {
        public void onSure();

    }
}
