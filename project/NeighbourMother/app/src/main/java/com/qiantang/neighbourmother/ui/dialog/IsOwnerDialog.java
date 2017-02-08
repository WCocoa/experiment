package com.qiantang.neighbourmother.ui.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.qiantang.neighbourmother.R;


/**
 * Created by Administrator on 2015/11/12.
 * 是否放弃用户信息修改
 */
public class IsOwnerDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(getString(R.string.isOwnerDialog_isOwner));
            final String[] sex =getResources().getStringArray(R.array.isOwnerArray);

            builder.setSingleChoiceItems(sex, 0,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(isOwnerListener!=null)
                                isOwnerListener.isOwner(which);
                        }
                    });
                return builder.create();
    }


    private IsOwnerListener isOwnerListener;
    public void setOnIsOwnerListener(IsOwnerListener isOwnerListener){
        this.isOwnerListener=isOwnerListener;
    }

    public interface IsOwnerListener {
        public void isOwner(int owner);
    }
}
