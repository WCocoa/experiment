package com.qiantang.neighbourmother.ui.activity;


import android.content.Intent;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.ImgcDetailAdapter;
import com.qiantang.neighbourmother.model.ImgcImageFloder;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.ImgMCFinal;
import com.qiantang.neighbourmother.util.ImgcSelectImgCall;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImgcImgDetailActivity extends BaseActivity implements OnClickListener {
	private GridView id_gridView;
	private ImageView imgdetail_return;
	private TextView imgdetail_cancer;
	private Button bottom_send;
	private List<String> imgs=new ArrayList<String>();
	
	ArrayList<String> aimgs=new ArrayList<String>();
	private File mImgDir;
	private ImgcDetailAdapter mMyApdapter;
	private ImgcImageFloder mImageFloder;
	
	@Override
	public int getContentView() {
		// TODO Auto-generated method stub
		return R.layout.activity_imgdetail;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mImageFloder=(ImgcImageFloder)getIntent().getSerializableExtra(ImgMCFinal.INTNET_IMG_FOLDER);
		imgs=getIntent().getStringArrayListExtra(ImgMCFinal.INTNET_IMG_CHOOSE);
		id_gridView=(GridView)findViewById(R.id.id_gridView);
		imgdetail_return=(ImageView)findViewById(R.id.imgdetail_return);
		imgdetail_cancer=(TextView)findViewById(R.id.imgdetail_cancer);
		bottom_send=(Button)findViewById(R.id.bottom_send);
		
		mImgDir = new File(mImageFloder.getDir());
		imgs= Arrays.asList(mImgDir.list(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String filename)
			{
				if (filename.endsWith(".jpg") || filename.endsWith(".png")
						|| filename.endsWith(".jpeg"))
					return true;
				return false;
			}
		}));
		
		mMyApdapter=new ImgcDetailAdapter(this, imgs, R.layout.item_imgdetail, mImgDir.getAbsolutePath());
		id_gridView.setAdapter(mMyApdapter);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		bottom_send.setOnClickListener(this);
		imgdetail_return.setOnClickListener(this);
		imgdetail_cancer.setOnClickListener(this);
		
		mMyApdapter.setOnSelectImgCall(new ImgcSelectImgCall() {
			@Override
			public void getCount(int count) {
				bottom_send.setText("发送("+count+")");	
			}
		});	
	}



	@Override
	protected void updateUI(Message msg) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onClick(View arg0) {
		Intent intent=null ;
		if(arg0.getId()==R.id.imgdetail_return){
			intent=new Intent();
			aimgs.clear();
			aimgs.addAll(ImgcDetailAdapter.mSelectedImage);
			intent.putStringArrayListExtra(ImgMCFinal.INTNET_IMG_CHOOSE, aimgs);
			setResult(1, intent);
			finish();
		}else if(arg0.getId()==R.id.imgdetail_cancer){
			setResult(2, null);
			finish();
		}else if(arg0.getId()==R.id.bottom_send){
			showProgressDialog();
			intent=new Intent();
			aimgs.clear();
			aimgs.addAll(ImgcDetailAdapter.mSelectedImage);
			System.out.println("select_img:"+aimgs.size());
			intent.putStringArrayListExtra(ImgMCFinal.INTNET_IMG_CHOOSE, aimgs);
			setResult(3, intent);
			finish();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
//		return super.onKeyDown(keyCode, event);
		boolean b=false;
		if(keyCode== KeyEvent.KEYCODE_BACK){
			setResult(2, null);
			finish();
			b=true;
		}
		return b;
	}

}
