package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.VersionResp;
import com.qiantang.neighbourmother.service.DownloadService;
import com.qiantang.neighbourmother.util.IntentFinal;


/**
 * Created by Administrator on 2015/11/12.
 * 是否放弃用户信息修改
 */
public class VersionUpdateDialog extends DialogFragment {
//    private SureListener sureListener;
    private VersionResp versionResp;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        versionResp=(VersionResp)getArguments().getSerializable(IntentFinal.INTENT_VERSION_OBJ_KEY);
        StringBuilder msg=new StringBuilder();
        for (int i=0;i<versionResp.getDescription().length;i++){
            msg.append(versionResp.getDescription()[i]);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.about_version_dialog_title));
        builder.setMessage(msg.toString());
        builder.setPositiveButton(R.string.about_version_dialog_sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(getActivity(), DownloadService.class);
                intent.putExtra(IntentFinal.INTENT_VERSION_OBJ_KEY,versionResp);
                getActivity().startService(intent);
            }
        });

        if(versionResp.getStatus()==1)
        builder.setNegativeButton(R.string.exitLogin_cancer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }



}
