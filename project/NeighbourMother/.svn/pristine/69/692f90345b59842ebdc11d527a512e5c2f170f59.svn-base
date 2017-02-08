package com.qiantang.neighbourmother.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.qiantang.neighbourmother.R;


/**
 * @author quliang 
 * @version 2015-8-20 上午11:28:11 
 * desc
 */
public class ProcessDialog extends AlertDialog {

	AnimationDrawable ad;

	public ProcessDialog(Context context) {
		super(context, R.style.MyDialog);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_progress);
		ImageView img = (ImageView) findViewById(R.id.loading);
		ad = (AnimationDrawable) img.getBackground();
		ad.start();
	}

	@Override
	public void cancel() {
		super.cancel();
	}

}
