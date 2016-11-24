package com.cocoa.cocoautils.ui.main;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.adapter.MainTabAdapter;
import com.cocoa.cocoautils.ui.base.BaseActivity;
import com.cocoa.cocoautils.ui.folder.FolderFragment;
import com.cocoa.cocoautils.ui.home.HomeFragment;
import com.cocoa.cocoautils.ui.main.impl.MainInteractorImpl;
import com.cocoa.cocoautils.ui.main.interactor.MainInteractor;
import com.cocoa.cocoautils.ui.main.presenter.MainPresenter;
import com.cocoa.cocoautils.ui.main.view.MainView;
import com.cocoa.cocoautils.ui.run.RunFragment;
import com.cocoa.cocoautils.ui.setting.SettingsFragment;
import com.cocoa.cocoautils.ui.weather.WeatherFragment;
import com.cocoa.cocoautils.widget.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;
import java.util.List;

public class MainTabActivity extends BaseActivity<MainView, MainPresenter>
        implements MainView, NavigationView.OnNavigationItemSelectedListener {


    private Toolbar               toolbar;
    private FloatingActionButton  fab;
    private DrawerLayout          drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView        navigationView;
    private CoordinatorLayout     container;
    private ViewPager             vp_horizontal_ntb;
    private NavigationTabBar      navigationTabBar;
    private ViewPager             backdrop;
    private RecyclerView          recyclerView;
    private MainTabAdapter        mainTabAdapter;
    private List<Fragment>        fragments;


    WeatherFragment  weatherFragment;
    SettingsFragment settingsFragment;
    HomeFragment     homeFragment;
    FolderFragment   folderFragment;
    RunFragment      runFragment;
    private String[]                          colors;
    private ArrayList<NavigationTabBar.Model> models;
    private int[]                             pagerarray;

    @Override
    public int getContentView() {
        return R.layout.activity_main_tab;
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        container = (CoordinatorLayout) findViewById(R.id.container);
        vp_horizontal_ntb = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
//        backdrop = (ViewPager) findViewById(R.id.backdrop);

    }

    @Override
    public void initData() {
        MainInteractor mainInteractor = new MainInteractorImpl();
        models = new ArrayList<>();
        colors = getResources().getStringArray(R.array.default_preview);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        weatherFragment = new WeatherFragment();
        homeFragment = new HomeFragment();
        runFragment = new RunFragment();
        folderFragment = new FolderFragment();
        settingsFragment = new SettingsFragment();

        fragments = new ArrayList<Fragment>();
        fragments.add(homeFragment);
        fragments.add(weatherFragment);
        fragments.add(runFragment);
        fragments.add(folderFragment);
        fragments.add(settingsFragment);

        mainTabAdapter = new MainTabAdapter(getSupportFragmentManager(), fragments);

        vp_horizontal_ntb.setAdapter(mainTabAdapter);
        mainInteractor.initNavigationTabBar(this, models, toolbar, navigationTabBar, vp_horizontal_ntb, colors);

//        initNavigationTabBar();
//        initViewPager();
    }


//    private void initNavigationTabBar() {
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.icon_home),
//                        Color.parseColor(colors[0]))
//                        .title("Home")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.icon_weather),
//                        Color.parseColor(colors[1]))
//                        .title("Weather")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.icon_run),
//                        Color.parseColor(colors[2]))
//                        .title("Run")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.icon_folder),
//                        Color.parseColor(colors[3]))
//                        .title("Folder")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.icon_setting),
//                        Color.parseColor(colors[4]))
//                        .title("Setting")
//                        .build()
//        );
//
//        navigationTabBar.setModels(models);
//        navigationTabBar.setViewPager(vp_horizontal_ntb, 0);
//        toolbar.setTitle("首页");
//
//        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
//        navigationTabBar.setBehaviorEnabled(true);
//
//        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
//            @Override
//            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
//
//            }
//
//            @Override
//            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
//                model.hideBadge();
//            }
//        });
//        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(final int position) {
//                switch (position) {
//                    case 0:
//                        toolbar.setTitle("首页");
//                        break;
//                    case 1:
//                        toolbar.setTitle("天气");
//                        break;
//                    case 2:
//                        toolbar.setTitle("跑步");
//                        break;
//                    case 3:
//                        toolbar.setTitle("文件");
//                        break;
//                    case 4:
//                        toolbar.setTitle("设置");
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(final int state) {
//
//            }
//        });
//    }


    @Override
    public void initEvent() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp_horizontal_ntb.setCurrentItem(fragments.size() - 1);
            }
        });
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
