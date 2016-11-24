package com.cocoa.cocoautils.ui.weather;

import android.view.View;

import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.ui.base.BaseFragment;
import com.cocoa.cocoautils.ui.weather.presenter.WeatherPresenter;
import com.cocoa.cocoautils.ui.weather.view.WeatherView;


/**
 * ClassName:天气
 * author: Cocoa
 * date: 2016/11/14.
 */

public class WeatherFragment extends BaseFragment<WeatherView, WeatherPresenter> implements WeatherView,View.OnClickListener {
    @Override
    public void onClick(View view) {

    }


    @Override
    public int getContentView() {
        return R.layout.fragment_weather;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public WeatherPresenter initPresenter() {
        return new WeatherPresenter();
    }

    @Override
    public void initEvent() {

    }
}
