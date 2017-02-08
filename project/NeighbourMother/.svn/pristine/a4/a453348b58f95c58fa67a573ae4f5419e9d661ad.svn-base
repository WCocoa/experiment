package com.qiantang.neighbourmother.ui.activity;

import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.model.PhotoDisplayObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.widget.photoview.PhotoView;


public class PhotoViewActivity extends BaseActivity {

	private PhotoView imageView;
	private LinearLayout linear;

	private PhotoDisplayObj photoDisplayObj;
	private String imgUri;
	private int defaultImg;
	@Override
	public int getContentView() {
		// TODO Auto-generated method stub
		return R.layout.activity_photoview;
	}

	@Override
	public void initView() {
		
		imageView = (PhotoView) findViewById(R.id.imageView);
		linear = (LinearLayout) findViewById(R.id.linear);

		 photoDisplayObj=(PhotoDisplayObj)getIntent().getSerializableExtra(
				IntentFinal.INTENT_TO_PHOTOVIEW_ACTIVITY);
		if(photoDisplayObj!=null){
			defaultImg=R.mipmap.default_img;
			imgUri=photoDisplayObj.getImagePath();
		}else{
			defaultImg=R.mipmap.default_img;
			 imgUri = getIntent().getStringExtra(
					IntentFinal.INTENT_IMGURL_TO_PHOTOVIEWACTIVITY);
		}
		
		AppLog.D("imgUrl:" + imgUri);

		this.display(imageView, imgUri,defaultImg);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		imageView
				.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
					@Override
					public boolean onSingleTapConfirmed(MotionEvent e) {
						finish();
						return false;
					}

					@Override
					public boolean onDoubleTap(MotionEvent e) {
						return false;
					}

					@Override
					public boolean onDoubleTapEvent(MotionEvent e) {
						return false;
					}
				});
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}


	@Override
	protected void updateUI(Message msg) {
		// TODO Auto-generated method stub
	}

}
