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
public class SexDialog extends DialogFragment {
    private String sex;

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.showSexDialog_sex));
        final String[] sexs = getResources().getStringArray(R.array.showSexDialog_array);
        int check = 1;
        if (!TextUtils.isEmpty(sex)) {
            for (int i = 0; i < sexs.length; i++) {
                if (sex.equals(sexs[i])) {
                    check = i;
                }
            }
        }
        builder.setSingleChoiceItems(sexs, check,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (sexListener != null)
                            sexListener.onSex(which);
                    }
                });

        return builder.create();
    }


    private SexListener sexListener;

    public void setOnSexListener(SexListener sexListener) {
        this.sexListener = sexListener;
    }

    public interface SexListener {
        public void onSex(int sex);
    }
}
