package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.qiantang.neighbourmother.R;


/**
 * Created by Administrator on 2015/11/12.
 * 是否删除订单
 */
public class CancerOrderDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage(R.string.cancer_order_title);
        builder.setTitle(R.string.cancer_order_title);
        builder.setMessage("如果取消订单，我们会尽快核准您的信息，并且在24小时内退款。");
        builder.setPositiveButton(R.string.cancer_order_sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cancerOrderListener != null) {
                    cancerOrderListener.onCancerSure();
                }
            }
        });
        builder.setNegativeButton(R.string.cancer_order_cancel,null);

        builder.setCancelable(false);
        return builder.create();
    }

    private CancerOrderListener cancerOrderListener;

    public void setOnSureListener(CancerOrderListener cancerOrderListener) {
        this.cancerOrderListener = cancerOrderListener;
    }

    public interface CancerOrderListener {
        public void onCancerSure();
    }
}
