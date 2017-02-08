package com.qiantang.neighbourmother.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * 引導頁適配器
 *
 * @author wk
 * @date 创建时间：2015年11月12日 上午10:14:32
 */
public class GuidepagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> views = new ArrayList<ImageView>();

    public GuidepagerAdapter(ArrayList<ImageView> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img = views.get(position);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
