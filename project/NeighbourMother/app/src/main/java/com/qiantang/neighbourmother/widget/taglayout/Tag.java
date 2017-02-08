package com.qiantang.neighbourmother.widget.taglayout;

import android.graphics.Color;

import java.io.Serializable;

public class Tag implements Serializable {
	
	/**
	 * 
	 */
	private int id;
	private boolean isChecked;
	private String title;
	/*index 0选中状态 1未选中*/
	private int colorlist= Color.parseColor("#ffffff");

	public Tag() {
	}


	public Tag(int id, boolean isChecked, String title, int colorlist) {
		this.id = id;
		this.isChecked = isChecked;
		this.title = title;
		this.colorlist = colorlist;
	}

	public int getId() {
		return this.id;
	}

	public int getColorlist() {
		return colorlist;
	}

	public void setColorlist(int colorlist) {
		this.colorlist = colorlist;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean isChecked() {
		return this.isChecked;
	}


	public void setChecked(boolean paramBoolean) {
		this.isChecked = paramBoolean;
	}

	public void setId(int paramInt) {
		this.id = paramInt;
	}



	public void setTitle(String paramString) {
		this.title = paramString;
	}


}
