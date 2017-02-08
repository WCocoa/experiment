package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.VersionResp;
import com.qiantang.neighbourmother.service.DownloadService;
import com.qiantang.neighbourmother.util.IntentFinal;


/**
 * Created by Administrator on 2015/11/12.
 * 是否放弃用户信息修改
 */
public class DownOrderSureDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.dor_dialog_title));
        builder.setMessage(R.string.dor_dialog_content);
        builder.setPositiveButton(R.string.dor_dialog_sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
dismiss();
                if(downOrderSureListener!=null)
                    downOrderSureListener.onDownOrderSure();
            }
        });

        builder.setNegativeButton(R.string.exitLogin_cancer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }


    private DownOrderSureListener downOrderSureListener;
    public void setDownOrderSureListener(DownOrderSureListener downOrderSureListener){
        this.downOrderSureListener=downOrderSureListener;
    }

    public interface DownOrderSureListener {
        public void onDownOrderSure();
    }
}
