package com.qiantang.neighbourmother.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;

import com.qiantang.neighbourmother.R;


/**
 * 提现
 */
public class WithdrawDepositDialog extends DialogFragment {
    private OnWithdrawDepositListener onWithdrawDepositListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.icon_susseed);
        builder.setTitle(getActivity().getString(R.string.orsd_title));
        builder.setMessage(getActivity().getString(R.string.wdd_message));
        builder.setPositiveButton(getActivity().getString(R.string.orsd_sure),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dismiss();
                        if (onWithdrawDepositListener != null) {
                            onWithdrawDepositListener.onClickConfrim();
                        }
                    }
                });

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_SEARCH)
                    return true;
                return false;
            }
        });
        return dialog;
    }

    public void setOnWithdrawDepositListener(OnWithdrawDepositListener onWithdrawDepositListener) {
        this.onWithdrawDepositListener = onWithdrawDepositListener;
    }

    public interface OnWithdrawDepositListener {
        void onClickConfrim();
    }

}
