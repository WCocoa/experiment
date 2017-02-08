package com.qiantang.neighbourmother.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PropertyConfig {

	private SharedPreferences share = null;
	private Editor editor;
	private Context context;

	private static PropertyConfig mPropertyConfig;

	public static PropertyConfig getInstance(Context context) {
		if (mPropertyConfig == null)
			mPropertyConfig = new PropertyConfig(context);
		return mPropertyConfig;
	}

	private PropertyConfig(Context context) {
		this.context = context;
		share = context.getSharedPreferences("ZHIFUORGAN", Context.MODE_PRIVATE);
		editor = share.edit();
	}

	public void save(String key, int value) {
		editor.putInt(key, value);
		editor.commit();
	}

	public void save(String key, long value) {
		editor.putLong(key, value);
		editor.commit();
	}

	public void save(String key, float value) {
		editor.putFloat(key, value);
		editor.commit();
	}

	public void save(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	public void save(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.commit();
	}

	public float getFloat(String key) {
		return share.getFloat(key, 0);
	}

	public int getInt(String key) {
		return share.getInt(key, 0);
	}
	public int getInt(String key,int defaultValue) {
		return share.getInt(key, defaultValue);
	}

	public String getString(String key) {
		return share.getString(key, "");
	}
	public boolean getBoolean(String key) {
		return share.getBoolean(key, false);
	}

	public Long getLong(String key) {
		return share.getLong(key, 0L);
	}

	public void saveLong(String key,Long value) {
		editor.putLong(key, value);
		editor.commit();
	}
	
}
