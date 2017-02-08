package com.qiantang.neighbourmother.ui.dialog;


import android.app.Dialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RatingBar;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * Created by Administrator on 2015/11/12.
 * 订单评价
 */
public class OrderScoreDialog extends DialogFragment {
    private View defineView;
    private TextView sure;
    private RatingBar ratingBar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        defineView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_order_score, null);
        ratingBar = (RatingBar) defineView.findViewById(R.id.ratingBar);
        LayerDrawable layerDrawable = (LayerDrawable) ratingBar.getProgressDrawable();
        layerDrawable.getDrawable(2).setColorFilter(getActivity().getResources().getColor(R.color.app_special_style_color_normal), PorterDuff.Mode.SRC_ATOP);
        ratingBar.setStepSize(1);
        ratingBar.setMax(5);
        sure = (TextView) defineView.findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating() < 1) {
                    ToastUtils.toastLong(getActivity(), R.string.orderscored_is_up_one);
                    return;
                }
                dismiss();
                if (sureScoreListener != null)
                    sureScoreListener.onSure((int) ratingBar.getRating());
            }
        });

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(defineView);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if(keyCode==KeyEvent.KEYCODE_BACK)
//                    return true;
//                else
//                return false;
//            }
//        });
        return dialog;
    }

    private SureScoreListener sureScoreListener;

    public void setOnSureScoreListener(SureScoreListener sureScoreListener) {
        this.sureScoreListener = sureScoreListener;
    }

    public interface SureScoreListener {
        void onSure(int rating);
    }
}
