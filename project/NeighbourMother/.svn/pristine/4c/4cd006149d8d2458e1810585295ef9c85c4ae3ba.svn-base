package com.qiantang.neighbourmother.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qiantang.neighbourmother.model.ComiscTypeObj;

import java.util.ArrayList;

/**
 * 招募专员页面适配器
 *
 * @date 创建时间：2015年11月12日 上午10:14:32
 */
public class CommicurpagerAdapter extends PagerAdapter {
    private ArrayList<ComiscTypeObj> comiscTypeObjs = new ArrayList<ComiscTypeObj>();

    public CommicurpagerAdapter(ArrayList<ComiscTypeObj> comiscTypeObjs) {
        this.comiscTypeObjs = comiscTypeObjs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return comiscTypeObjs.get(position).getTitle();
    }
    @Override
    public int getCount() {
        return comiscTypeObjs.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(comiscTypeObjs.get(position).getView());
        return comiscTypeObjs.get(position).getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
