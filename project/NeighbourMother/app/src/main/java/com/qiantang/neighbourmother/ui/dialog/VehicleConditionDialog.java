package com.qiantang.neighbourmother.ui.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;

import com.qiantang.neighbourmother.R;


/**
 * Created by Administrator on 2015/11/12.
 * 是否放弃用户信息修改
 */
public class VehicleConditionDialog extends DialogFragment {
    private String condition;

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.vcd_title));
        final String[] vehiclec = getResources().getStringArray(R.array.vehicleCondition_array);
        int checked = 0;
        if (TextUtils.isEmpty(condition)) {
            checked = 0;
        } else {
            if (vehiclec != null && vehiclec.length > 0) {
                for (int i = 0; i < vehiclec.length; i++) {
                    if (condition.equals(vehiclec[i])) {
                        checked = i;
                    }
                }
            }
        }
        builder.setSingleChoiceItems(vehiclec, checked,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (sureListener != null)
                            sureListener.onVecoSure(vehiclec[which]);
                    }
                });

        return builder.create();
    }


    private VecoSureListener sureListener;

    public void setOnSureListener(VecoSureListener sureListener) {
        this.sureListener = sureListener;
    }

    public interface VecoSureListener {
        public void onVecoSure(String vehicleCondition);
    }
}
