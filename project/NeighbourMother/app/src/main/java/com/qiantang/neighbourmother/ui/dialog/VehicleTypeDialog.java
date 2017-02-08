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
public class VehicleTypeDialog extends DialogFragment {

    private String type;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.vct_title));
        final String[] vehiclet = getResources().getStringArray(R.array.vehicleType_array);
        int checked = 0;
        if (TextUtils.isEmpty(type)) {

            checked = 0;
        } else {
            if (vehiclet != null && vehiclet.length > 0) {
                for (int i = 0; i < vehiclet.length; i++) {
                    if (type.equals(vehiclet[i])) {
                        checked = i;
                    }
                }
            }
        }
        builder.setSingleChoiceItems(vehiclet, checked,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (sureListener != null)
                            sureListener.onVetySure(vehiclet[which]);
                    }
                });

        return builder.create();
    }

    private VetySureListener sureListener;

    public void setOnSureListener(VetySureListener sureListener) {
        this.sureListener = sureListener;
    }

    public interface VetySureListener {
        public void onVetySure(String vehicleType);
    }
}
