package com.cocoa.cocoautils.ui.main.impl;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.ui.main.interactor.MainInteractor;
import com.cocoa.cocoautils.widget.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/11/24.
 */

public class MainInteractorImpl implements MainInteractor {


    @Override
    public void initNavigationTabBar(Context context, ArrayList<NavigationTabBar.Model> models, final Toolbar toolbar, NavigationTabBar navigationTabBar, ViewPager viewPager, String[] colors) {

        models.add(
                new NavigationTabBar.Model.Builder(
                        context.getResources().getDrawable(R.drawable.icon_home),
                        Color.parseColor(colors[0]))
                        .title("Home")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        context.getResources().getDrawable(R.drawable.icon_weather),
                        Color.parseColor(colors[1]))
                        .title("Weather")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        context.getResources().getDrawable(R.drawable.icon_run),
                        Color.parseColor(colors[2]))
                        .title("Run")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        context.getResources().getDrawable(R.drawable.icon_folder),
                        Color.parseColor(colors[3]))
                        .title("Folder")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        context.getResources().getDrawable(R.drawable.icon_setting),
                        Color.parseColor(colors[4]))
                        .title("Setting")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
//        toolbar.setTitle("首页");

        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        navigationTabBar.setBehaviorEnabled(true);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {

            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                switch (position) {
                    case 0:
                        toolbar.setTitle("首页");
                        break;
                    case 1:
                        toolbar.setTitle("天气");
                        break;
                    case 2:
                        toolbar.setTitle("跑步");
                        break;
                    case 3:
                        toolbar.setTitle("文件");
                        break;
                    case 4:
                        toolbar.setTitle("设置");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
    }
}
