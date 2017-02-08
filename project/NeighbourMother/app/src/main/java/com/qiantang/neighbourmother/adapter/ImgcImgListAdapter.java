package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.model.ImgcImageFloder;

import java.util.ArrayList;

/**
 * 主Adapter
 * 
 * @author think
 * 
 */
public class ImgcImgListAdapter extends BaseAdapter {
	ArrayList<ImgcImageFloder> newsList;
	Activity activity;
	LayoutInflater inflater = null;

	public ImgcImgListAdapter(Activity activity, ArrayList<ImgcImageFloder> newsList) {
		this.activity = activity;
		this.newsList = newsList;
		inflater = LayoutInflater.from(activity);
	}

	@Override
	public int getCount() {
		return newsList == null ? 0 : newsList.size();
	}

	@Override
	public ImgcImageFloder getItem(int position) {
		if (newsList != null && newsList.size() != 0) {
			return newsList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mHolder;
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.item_imgc_imglist, null);
			mHolder = new ViewHolder();
			mHolder.textv = (TextView) view
					.findViewById(R.id.textv);
			mHolder.imgv = (ImageView) view.findViewById(R.id.imgv);

			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}

		ImgcImageFloder newEntity = newsList.get(position);
		mHolder.textv.setText(newEntity.getName() + " (" + newEntity.getCount() + ")");
		ImageLoader.getInstance().displayImage("file://" + newEntity.getFirstImagePath(), mHolder.imgv);
//		Glide.with(activity).load("file://" + newEntity.getFirstImagePath()).crossFade().into(mHolder.imgv);
//		ChooseImgLoader.getInstance(3,ChooseImgLoader.Type.LIFO).loadImage(newEntity.getFirstImagePath(), mHolder.imgv);
		
		return view;
	}

	static class ViewHolder {
		TextView textv;
		ImageView imgv;

	}

}
