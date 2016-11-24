package com.cocoa.cocoautils.ui.home;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.adapter.HomeTopAdapter;
import com.cocoa.cocoautils.ui.base.BaseActivity;
import com.cocoa.cocoautils.ui.base.BaseFragment;
import com.cocoa.cocoautils.ui.home.presenter.HomeFragmentPresenter;
import com.cocoa.cocoautils.ui.home.view.HomeFragmentView;
import com.cocoa.cocoautils.widget.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * ClassName:设置
 * author: Cocoa
 * date: 2016/11/14.
 */

public class HomeFragment extends BaseFragment<HomeFragmentView, HomeFragmentPresenter> implements View.OnClickListener {

    private ViewPager      home_viewpager;
    private XRecyclerView  xrecycleviews;
    private HomeTopAdapter homeTopAdapter;

    @Override
    public void onClick(View view) {

    }

    @Override
    public HomeFragmentPresenter initPresenter() {
        return new HomeFragmentPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_home;

    }

    @Override
    public void initView(View view) {
        home_viewpager = (ViewPager) view.findViewById(R.id.home_viewpager);
        xrecycleviews = (XRecyclerView) view.findViewById(R.id.xrecycleviews);
        ((BaseActivity) getActivity()).calcuAdersWidth(home_viewpager, 0.5f);
    }

    @Override
    public void initData() {
        initViewPager();

    }

    private int[] pagerarray;
    private ArrayList<ImageView> views = new ArrayList<ImageView>();

    private void initViewPager() {
        pagerarray = new int[]{R.drawable.home_page_1,
                R.drawable.home_page_2, R.drawable.home_page_3, R.drawable.home_page_4, R.drawable.home_page_5, R.drawable.home_page_6
                , R.drawable.home_page_7, R.drawable.home_page_8, R.drawable.home_page_9, R.drawable.home_page_10};
        for (int i = 0; i < pagerarray.length; i++) {
            ImageView image = new ImageView(getActivity());
            views.add(image);


            Glide.with(this).load(pagerarray[i]).into(image);

        }
        homeTopAdapter = new HomeTopAdapter(views);
        home_viewpager.setAdapter(new HomeTopAdapter(views));
    }

    @Override
    public void initEvent() {

    }
}
