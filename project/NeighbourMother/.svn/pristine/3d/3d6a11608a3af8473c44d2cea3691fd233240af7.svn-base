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
public class DeletePostDialog extends DialogFragment {

    private View defineView;
    private TextView cancel;
    private TextView sure;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        defineView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete_post, null);
        cancel = (TextView) defineView.findViewById(R.id.cancel);
        sure = (TextView) defineView.findViewById(R.id.sure);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deletePostListener != null) {
                    deletePostListener.onCancel();
                }
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deletePostListener != null) {
                    deletePostListener.onSure();
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


    private DeletePostListener deletePostListener;

    public void setOnSureListener(DeletePostListener deletePostListener) {
        this.deletePostListener = deletePostListener;
    }

    public interface DeletePostListener {
        public void onSure();

        public void onCancel();
    }
}
