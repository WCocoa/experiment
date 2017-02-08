package com.qiantang.neighbourmother.ui.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.qiantang.neighbourmother.R;


/**
 * Created by Administrator on 2015/11/12.
 *
 */
public class RelationShipDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(getString(R.string.rsd_title));
            final String[] vehiclet =getResources().getStringArray(R.array.relationShip_array);

            builder.setSingleChoiceItems(vehiclet, 0,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(sureListener!=null)
                                sureListener.onVetySure(which);
                        }
                    });

                return builder.create();
    }

    private RelaSureListener sureListener;
    public void setOnReSureListener(RelaSureListener sureListener){
        this.sureListener=sureListener;
    }

    public interface RelaSureListener {
        public void onVetySure(int reType);
    }
}
