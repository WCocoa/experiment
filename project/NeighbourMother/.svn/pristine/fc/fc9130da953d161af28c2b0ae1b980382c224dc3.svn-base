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
 * Created by Administrator on 2015/11/12.
 * 是否删除订单
 */
public class CreateGroupDialog extends DialogFragment {

    private View defineView;
    private TextView sure;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        defineView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_create_group, null);
        sure = (TextView) defineView.findViewById(R.id.sure);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (groupListener != null) {
                    groupListener.onSure();
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


    private CreateGroupListener groupListener;

    public void setOnSureListener(CreateGroupListener groupListener) {
        this.groupListener = groupListener;
    }

    public interface CreateGroupListener {
        public void onSure();

        public void onCancel();
    }
}
