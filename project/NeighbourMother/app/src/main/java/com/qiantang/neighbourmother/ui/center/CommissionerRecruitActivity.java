package com.qiantang.neighbourmother.ui.center;

import android.content.Intent;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.CommicurpagerAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.ApplyAttacheQuHttp;
import com.qiantang.neighbourmother.business.request.ApplyAttacheQuReq;
import com.qiantang.neighbourmother.business.response.ApplyAttacheQuResp;
import com.qiantang.neighbourmother.model.ComiscTypeObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.IntentFinal;

import java.util.ArrayList;

/**
 * ClassName:孩子信息
 * author: Cocoa
 * date: 2016/9/20.
 */
public class CommissionerRecruitActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 1接送2看护3辅导4专车
     */
    public static int       SPECIAL_RECUIT_TYPE;
    private       ImageView back;
    private       TextView  top_text;
    private       TabLayout tab_title;
    private       ViewPager viewPager;

    @Override
    public int getContentView() {
        return R.layout.activity_commissionerrecruit;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        top_text = (TextView) findViewById(R.id.top_text);
        tab_title = (TabLayout) findViewById(R.id.tab_title);
        viewPager = (ViewPager) findViewById(R.id.view_pager_content);
    }

    @Override
    public void initData() {
        initServiceType();
    }

    private void initServiceType() {
        tab_title.setupWithViewPager(viewPager);
        final TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(tab_title);
        viewPager.addOnPageChangeListener(listener);

        ArrayList<ComiscTypeObj> comiscTypeObjs = new ArrayList<ComiscTypeObj>();
//        View                     js             = LayoutInflater.from(this).inflate(R.layout.layout_commisrec_service_js, null);
//        js.findViewById(R.id.submit_js).setOnClickListener(this);
        View kh = LayoutInflater.from(this).inflate(R.layout.layout_commisrec_service_kh, null);
        kh.findViewById(R.id.submit_kh).setOnClickListener(this);
        View fd = LayoutInflater.from(this).inflate(R.layout.layout_commisrec_service_fd, null);
        fd.findViewById(R.id.submit_fd).setOnClickListener(this);
//        View zc = LayoutInflater.from(this).inflate(R.layout.layout_commisrec_service_zc, null);
//        zc.findViewById(R.id.submit_zc).setOnClickListener(this);

//        comiscTypeObjs.add(new ComiscTypeObj(getString(R.string.commirec_service_js), js));
        comiscTypeObjs.add(new ComiscTypeObj(getString(R.string.commirec_service_kh), kh));
        comiscTypeObjs.add(new ComiscTypeObj(getString(R.string.commirec_service_fd), fd));
//        comiscTypeObjs.add(new ComiscTypeObj(getString(R.string.commirec_service_zc), zc));
        viewPager.setAdapter(new CommicurpagerAdapter(comiscTypeObjs));
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        tab_title.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.select();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:

                ApplyAttacheQuResp applyAttacheQuResp = (ApplyAttacheQuResp) msg.obj;
                Intent intent = new Intent(this, CommissionerApplyOneActivity.class);
                intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP, applyAttacheQuResp);
                closeProgressDialog();
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.submit_js:
                SPECIAL_RECUIT_TYPE = 1;
                new ApplyAttacheQuHttp(this, handler, new ApplyAttacheQuReq("1"), 1);
                break;
            case R.id.submit_kh:
                SPECIAL_RECUIT_TYPE = 2;
                new ApplyAttacheQuHttp(this, handler, new ApplyAttacheQuReq("2"), 1);
                break;
            case R.id.submit_fd:
                SPECIAL_RECUIT_TYPE = 3;
                new ApplyAttacheQuHttp(this, handler, new ApplyAttacheQuReq("3"), 1);
                break;
            case R.id.submit_zc:
                SPECIAL_RECUIT_TYPE = 4;
                new ApplyAttacheQuHttp(this, handler, new ApplyAttacheQuReq("4"), 1);
                break;
        }
    }


}
