package com.qiantang.neighbourmother.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.ImgMCFinal;
import com.qiantang.neighbourmother.util.ImgcSelectImgCall;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.util.ViewHolder;

import java.util.LinkedList;
import java.util.List;


public class ImgcDetailAdapter extends ImgcCommonAdapter<String> {

	private ImgcSelectImgCall mSelectImgCall;

	/**
	 * 用户选择的图片，存储为图片的完整路径
	 */
	public static List<String> mSelectedImage = new LinkedList<String>();

	/**
	 * 文件夹路径
	 */
	private String mDirPath;
private Context context;
	public ImgcDetailAdapter(Context context, List<String> mDatas, int itemLayoutId,
							 String dirPath) {
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
		this.context = context;
	}

	@Override
	public void convert(final ViewHolder helper, final String item) {
		// 设置no_pic
		helper.setImageResource(R.id.id_item_image, R.mipmap.default_img);
		//		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

		// 设置no_selected
//		helper.setImageResource(R.id.id_item_select,
//				R.drawable.picture_unselected);
		// 设置图片

		helper.setImageResource(R.id.id_item_select,
				R.color.img_choose_color);

		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);

		ImageLoader.getInstance().displayImage("file://" + mDirPath + "/" + item, mImageView);
//
//		Glide.with(context).load("file://" + mDirPath + "/" + item).crossFade().into(mImageView);
		mImageView.setColorFilter(null);
		// 设置ImageView的点击事件
		mImageView.setOnClickListener(new OnClickListener() {
			// 选择，则将图片变暗，反之则反之
			@Override
			public void onClick(View v) {

				// 已经选择过该图片
				if (mSelectedImage.contains(mDirPath + "/" + item)) {
					mSelectedImage.remove(mDirPath + "/" + item);
//					mSelect.setImageResource(R.drawable.picture_unselected);
					mSelect.setImageResource(0);
					mImageView.setColorFilter(null);
				} else
				// 未选择该图片
				{
					if(mSelectedImage.size()>= ImgMCFinal.CHOOSE_LIB_TOTAL-ImgMCFinal.selectedNum){
						ToastUtils.toastshort(context, context.getResources().getString(R.string.string_more_five_img_prefix)+ImgMCFinal.CHOOSE_LIB_TOTAL+context.getResources().getString(R.string.string_more_five_img_suffix));
					}else{
						mSelectedImage.add(mDirPath + "/" + item);
						mSelect.setImageResource(R.mipmap.pictures_selected);
						mImageView.setColorFilter(Color.parseColor("#77000000"));
					}
				}

				mSelectImgCall.getCount(mSelectedImage.size());

			}
		});

		/**
		 * 已经选择过的图片，显示出选择过的效果
		 */
		if (mSelectedImage.contains(mDirPath + "/" + item)) {
			mSelect.setImageResource(R.mipmap.pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}

	}

	public void setOnSelectImgCall(ImgcSelectImgCall mSelectImgCall) {
		this.mSelectImgCall = mSelectImgCall;
		if (mSelectImgCall != null)
			mSelectImgCall.getCount(mSelectedImage.size());
	}
	
}
