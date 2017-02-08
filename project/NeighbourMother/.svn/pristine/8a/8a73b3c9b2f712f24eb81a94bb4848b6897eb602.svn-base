package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * Created by Administrator on 2015/11/12.
 * 是否放弃用户信息修改
 */
public class NeighbourhoodDialog extends DialogFragment {

    private View defineView;
    private EditText edit;
    private Button sure;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        defineView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_neighbourhood, null);
        edit = (EditText) defineView.findViewById(R.id.edit);
        sure = (Button) defineView.findViewById(R.id.sure);

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if(s.toString().trim().equals("2017")){
//                    APIConfig.RUN= APIConfig.TEST;
//                    APIConfig.BASE_IMG_URL= APIConfig.IMG_TEST;
//                              ToastUtils.toastLong(getActivity(),R.string.eda_model_switch);
//                }
            }
        });




        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edit.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.toastLong(getActivity(), R.string.neighbourd_edit_hint);
                } else {

                    dismiss();
                    if (sureListener != null)
                        sureListener.onSure(content);
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


    private SureListener sureListener;

    public void setOnSureListener(SureListener sureListener) {
        this.sureListener = sureListener;
    }

    public interface SureListener {
        public void onSure(String neighbourhood);
    }
}
