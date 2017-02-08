package com.qiantang.neighbourmother.model;

import java.io.Serializable;

public class PhotoDisplayObj implements Serializable {
	private String imagePath;
	/**1，活动过来的图片*/
	private int type;
	public PhotoDisplayObj(){}
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public PhotoDisplayObj(String imagePath, int type) {
		super();
		this.imagePath = imagePath;
		this.type = type;
	}

}
