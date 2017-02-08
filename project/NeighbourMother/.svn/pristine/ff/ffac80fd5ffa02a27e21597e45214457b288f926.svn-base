package com.qiantang.neighbourmother.logic;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.qiantang.neighbourmother.adapter.HomeAdPagerAdapter;
import com.qiantang.neighbourmother.model.AdObj;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class HomeCreateAdPager extends CreateAdPagerBase {
    private List<AdObj> adObjs;

    public HomeCreateAdPager(Activity activity, LinearLayout ll, ViewPager viewPager, Handler handler, int what, boolean isAotuPageTurning) {

        super(activity, ll, viewPager, handler, what, isAotuPageTurning);
    }

    public void start(List<AdObj> adObjs) {
        this.adObjs = adObjs;
        super.excu();
    }

    @Override
    void initData() {
        HomeAdPagerAdapter homeAdPagerAdapter = new HomeAdPagerAdapter(activity, adObjs);
        viewPager.setAdapter(homeAdPagerAdapter);
    }

    @Override
    int getLength() {
        return adObjs.size();
    }
}
