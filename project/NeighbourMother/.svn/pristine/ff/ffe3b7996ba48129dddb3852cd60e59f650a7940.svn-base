package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.ui.startpage.LoginActivity;
import com.qiantang.neighbourmother.util.IntentFinal;


/**
 * 退出dialog
 */
public class ExitLoginDialog extends DialogFragment {
    private ExitLoginListener exitLoginListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.exitLogin_title));
        builder.setPositiveButton(R.string.exitLogin_sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserContacts.clearUserInfo(getActivity());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra(IntentFinal.INTENT_LOGIN_TOKEN_FAILE, 1);
                startActivity(intent);
                getActivity().finish();
            }
        });

        builder.setNegativeButton(R.string.exitLogin_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }

    public void setOnExitLoginListener(ExitLoginListener exitLoginListener) {
        this.exitLoginListener = exitLoginListener;
    }

    public interface ExitLoginListener {
        void onExitLoginComplete();
    }
}
