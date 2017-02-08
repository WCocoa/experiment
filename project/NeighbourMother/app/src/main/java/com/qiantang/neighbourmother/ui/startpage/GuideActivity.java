package com.qiantang.neighbourmother.ui.startpage;

import android.content.Intent;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.GuidepagerAdapter;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;

import java.util.ArrayList;

/**
 * ClassName:引导页
 * author: Cocoa
 * date: 2016/9/17.
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public static final String TAG        = GuideActivity.class.getSimpleName();
    private             int[]  pagerarray = {R.mipmap.guide_frist, R.mipmap.guide_second, R.mipmap.guide_third, R.mipmap.guide_four};
    private ViewPager vp_Guid_Pager;
    private Button    btn_start_taste;


    //  R.mipmap.guide_four, R.mipmap.guide_five};
    private ArrayList<ImageView> views = new ArrayList<ImageView>();

    @Override
    public int getContentView() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        vp_Guid_Pager = (ViewPager) findViewById(R.id.vp_guide_pager);
        btn_start_taste = (Button) findViewById(R.id.btn_start_taste);
    }

    @Override
    public void initData() {
        for (int i = 0; i < pagerarray.length; i++) {
            ImageView image = new ImageView(GuideActivity.this);
            views.add(image);
            ImageLoader.getInstance().displayImage("drawable://" + pagerarray[i], image);

        }
        vp_Guid_Pager.setAdapter(new GuidepagerAdapter(views));
    }

    @Override
    public void initEvent() {
        btn_start_taste.setOnClickListener(this);
        vp_Guid_Pager.setOnPageChangeListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_taste:
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                PropertyConfig.getInstance(this).save(IntentFinal.IS_FIRST_USE, true);
                finish();
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (views.size() - 1 == position) {
            btn_start_taste.setVisibility(View.VISIBLE);
        } else {
            btn_start_taste.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }
}