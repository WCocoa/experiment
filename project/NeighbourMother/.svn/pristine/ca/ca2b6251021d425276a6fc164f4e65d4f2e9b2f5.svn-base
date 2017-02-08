package com.qiantang.neighbourmother.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.qiantang.neighbourmother.model.ScreenResolution;


public class QLScreenUtil {
	
	public static float dpToPx(Context context, float dp) {
		if (context == null) {
			return -1;
		}
		return dp * context.getResources().getDisplayMetrics().density;
	}
	
	public static float pxToDp(Context context, float px) {
		if (context == null) {
			return -1;
		}
		return px / context.getResources().getDisplayMetrics().density;
	}
	
	public static float dpToPxInt(Context context, float dp) {
		return dpToPx(context, dp) + 0.5f;
	}

	public static float pxToDpCeilInt(Context context, float px) {
		return pxToDp(context, px) + 0.5f;
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static float pxToSpInt(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return pxValue / fontScale + 0.5f;
	}

	/**
	 * 获取屏幕分辨率
	 * @param mActivity
	 * @return ScreenResolution
	 */
	public static ScreenResolution getScreenResolution(Activity mActivity) {
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay()
				.getMetrics(mDisplayMetrics);
		return new ScreenResolution(mDisplayMetrics.widthPixels,
				mDisplayMetrics.heightPixels);
	}

}
