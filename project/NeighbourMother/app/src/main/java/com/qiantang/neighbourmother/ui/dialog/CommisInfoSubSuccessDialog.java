package com.qiantang.neighbourmother.ui.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * Created by Administrator on 2015/11/12.
 * 专员资料提交成功
 */
public class CommisInfoSubSuccessDialog extends DialogFragment {
    private View defineView;
    private TextView sure;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        defineView= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_commis_info_submit_success,null);
        sure=(TextView) defineView.findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dismiss();
                    if(sureListener!=null)
                        sureListener.onSure();
            }
        });

        Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(defineView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK)
                    return true;
                else
                return false;
            }
        });
                return dialog;
    }

    private SureListener sureListener;
    public void setOnSureListener(SureListener sureListener){
        this.sureListener=sureListener;
    }

    public interface SureListener {
         void onSure();
    }
}
