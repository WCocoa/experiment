package com.cocoa.cocoautils.ui.home;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.adapter.HomeAdapter;
import com.cocoa.cocoautils.adapter.HomeTopAdapter;
import com.cocoa.cocoautils.ui.base.BaseActivity;
import com.cocoa.cocoautils.ui.base.BaseFragment;
import com.cocoa.cocoautils.ui.home.presenter.HomeFragmentPresenter;
import com.cocoa.cocoautils.ui.home.view.HomeFragmentView;
import com.cocoa.cocoautils.widget.xrecyclerview.XRecyclerView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName:设置
 * author: Cocoa
 * date: 2016/11/14.
 */

public class HomeFragment extends BaseFragment<HomeFragmentView, HomeFragmentPresenter> implements View.OnClickListener {

    private ViewPager      home_viewpager;
    private XRecyclerView  xrecycleviews;
    private HomeTopAdapter homeTopAdapter;
    private View           headview;
    private HomeAdapter    homeAdapter;
    private TextView       mTitle;
    private Toolbar        mToolbar;
    private AppBarLayout   mAppBar;
    private MaterialSearchView   mSearchView;

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
//        initHeadViewData();
        xrecycleviews = (XRecyclerView) view.findViewById(R.id.xrecycleviews);
        home_viewpager = (ViewPager) view.findViewById(R.id.view_pager);
        ((BaseActivity) getActivity()).calcuAdersWidth(home_viewpager, 0.5f);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mSearchView = (MaterialSearchView) view.findViewById(R.id.search);
        mTitle = (TextView)view. findViewById(R.id.title);
        mAppBar = (AppBarLayout) view.findViewById(R.id.appbar);
//        xrecycleviews.addHeaderView(headview);

    }

    private void initHeadViewData() {
        headview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_home_header, null);

    }

    @Override
    public void initData() {
        xrecycleviews.setPullRefreshEnabled(false);
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
        LinearLayoutManager liner = new LinearLayoutManager(getActivity());
        liner.setOrientation(LinearLayoutManager.VERTICAL);
        List<String> arrayList = Arrays.asList("1", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2");
        xrecycleviews.setLayoutManager(liner);

        homeAdapter = new HomeAdapter(getActivity(), arrayList);
        xrecycleviews.setAdapter(homeAdapter);
//        homeAdapter.getList().addAll(arrayList);
//        homeAdapter.notifyDataSetChanged();
        ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                float percent = Math.abs(verticalOffset * 1.0f / totalScrollRange);
                if (mNavigationIcon != null) {
                    mNavigationIcon.setColorFilter((int) mArgbEvaluator.evaluate(percent, Color.WHITE, Color.BLACK), PorterDuff.Mode.SRC_IN);
                }
                if (mSearchItemIcon != null) {
                    mSearchItemIcon.setColorFilter((int) mArgbEvaluator.evaluate(percent, Color.WHITE, Color.BLACK), PorterDuff.Mode.SRC_IN);
                }
                mTitle.setTextColor((int) mArgbEvaluator.evaluate(percent, Color.WHITE, Color.BLACK));
            }
        });
    }


    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    private Drawable mNavigationIcon;
    private Drawable mSearchItemIcon;
    @Override
    public void initEvent() {

    }
}
