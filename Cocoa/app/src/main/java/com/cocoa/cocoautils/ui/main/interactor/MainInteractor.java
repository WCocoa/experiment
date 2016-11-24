package com.cocoa.cocoautils.ui.main.interactor;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cocoa.cocoautils.widget.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/11/24.
 */

public interface MainInteractor {
    void initNavigationTabBar(Context context, ArrayList<NavigationTabBar.Model> models, Toolbar toolbar, NavigationTabBar navigationTabBar, ViewPager viewPager, String[] colors);

}
