package com.qiantang.neighbourmother.logic;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.qiantang.neighbourmother.adapter.ConmmunityAdPagerAdapter;
import com.qiantang.neighbourmother.model.AdObj;
import com.qiantang.neighbourmother.model.SliderObj;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class ConmmunityAdPager extends CreateAdPagerBase {
    private List<SliderObj> adObjs;

    public ConmmunityAdPager(Activity activity, LinearLayout ll, ViewPager viewPager, Handler handler, int what, boolean isAotuPageTurning) {

        super(activity, ll, viewPager, handler, what, isAotuPageTurning);
    }

    public void start(List<SliderObj> adObjs) {
        this.adObjs = adObjs;
        super.excu();
    }

    @Override
    void initData() {
        ConmmunityAdPagerAdapter conmmunityAdPagerAdapter = new ConmmunityAdPagerAdapter(activity, adObjs);
        viewPager.setAdapter(conmmunityAdPagerAdapter);
    }

    @Override
    int getLength() {
        return adObjs.size();
    }
}
