package com.qiantang.neighbourmother.ui.home;

import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.ServiceIntroduceHttp;
import com.qiantang.neighbourmother.business.response.ServiceIntroduceResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.IntentFinal;

import java.util.List;


/**
 * ClassName:服务介绍
 * author: Cocoa
 * date: 2016/9/18.
 */
public class ServiceIntroduceActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                  back;
    private FrameLayout                fl_content;
    private ServiceIntrCommFragment    serviceIntrCommFragment;//学前
    //    private ServiceIntrCommFragment    primarySchoolFragment;//小学
//    private ServiceIntrCommFragment    juniorHighSchoolFragment;//初中
//    private ServiceIntrCommFragment    seniorHighSchoolFragment;//高中
//    private RelativeLayout             rl_pre_school;//学前
//    private TextView                   pre_school_text;//学前文本
//    private RelativeLayout             rl_primary_school;//小学
//    private TextView                   primary_school_text;//小学
//    private RelativeLayout             rl_juior_high_school;//初中
//    private TextView                   juior_high_school_text;//初中文本
//    private RelativeLayout             rl_senior_high_school;//高中
//    private TextView                   senior_high_school_text;//高中文本
//    private LinearLayout               service_tab;//
//    private View                       line1;
//    private View                       line2;
//    private View                       line3;
//    private View                       line4;
    private List<ServiceIntroduceResp> serviceIntroduce;
    private int                        position;
    private TextView                   title;//标题

    public int getPosition() {
        return position;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_service_introduce;
    }

    @Override
    public void initView() {

//        rl_pre_school = (RelativeLayout) findViewById(rl_pre_school);
//        pre_school_text = (TextView) findViewById(R.id.pre_school_text);
//        line1 = findViewById(R.id.line1);
//        line2 = findViewById(R.id.line2);
//        line3 = findViewById(R.id.line3);
//        line4 = findViewById(R.id.line4);
//        rl_primary_school = (RelativeLayout) findViewById(R.id.rl_primary_school);
//        primary_school_text = (TextView) findViewById(R.id.primary_school_text);
//        rl_juior_high_school = (RelativeLayout) findViewById(R.id.rl_juior_high_school);
//        juior_high_school_text = (TextView) findViewById(R.id.juior_high_school_text);
//        rl_senior_high_school = (RelativeLayout) findViewById(R.id.rl_senior_high_school);
//        senior_high_school_text = (TextView) findViewById(senior_high_school_text);
        back = (ImageView) findViewById(R.id.back);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
//        service_tab = (LinearLayout) findViewById(service_tab);
        title = (TextView) findViewById(R.id.title);
//        service_tab.setVisibility(View.INVISIBLE);
    }


    @Override
    public void initData() {

        position = getIntent().getIntExtra(IntentFinal.INTENT_HOME_SERVICE, 1);
        serviceIntrCommFragment = new ServiceIntrCommFragment();
//        primarySchoolFragment = new ServiceIntrCommFragment();
//        juniorHighSchoolFragment = new ServiceIntrCommFragment();
//        seniorHighSchoolFragment = new ServiceIntrCommFragment();

        if (position == 1) {
            title.setText(R.string.pick_up_title);
        } else if (position == 2) {
            title.setText(R.string.nures_title);
        } else {
            title.setText(R.string.service_introduce_title);
        }
        getHttpData(position);

    }

    private void getHttpData(int position) {
        new ServiceIntroduceHttp(this, handler, position, 1, true);
    }

//
//    private void setTabFragmentContent(int pos) {
//        FragmentManager     manager     = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        switch (pos) {
//            case 0:
//                transaction.replace(R.id.fl_content, preschoolFragment);
//                transaction.commit();
//                break;
//            case 1:
//                transaction.replace(R.id.fl_content, primarySchoolFragment);
//                transaction.commit();
//                break;
//            case 2:
//                transaction.replace(R.id.fl_content, juniorHighSchoolFragment);
//                transaction.commit();
//                break;
//            case 3:
//                transaction.replace(R.id.fl_content, seniorHighSchoolFragment);
//                transaction.commit();
//                break;
//        }
//    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
//        rl_pre_school.setOnClickListener(this);
//        rl_primary_school.setOnClickListener(this);
//        rl_juior_high_school.setOnClickListener(this);
//        rl_senior_high_school.setOnClickListener(this);
//        rl_pre_school.setOnClickListener(this);

    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                serviceIntroduce = (List<ServiceIntroduceResp>) msg.obj;
                setData(serviceIntroduce);
                break;
        }
        closeProgressDialog();
    }

    public void setData(List<ServiceIntroduceResp> services) {
//        List<TextView> textViews = new ArrayList<>();
//        textViews.add(pre_school_text);
//        textViews.add(primary_school_text);
//        textViews.add(juior_high_school_text);
//        textViews.add(senior_high_school_text);
//
//        List<RelativeLayout> layouts = new ArrayList<>();
//        layouts.add(rl_pre_school);
//        layouts.add(rl_primary_school);
//        layouts.add(rl_juior_high_school);
//        layouts.add(rl_senior_high_school);
//
//        List<View> views = new ArrayList<>();
//        views.add(line1);
//        views.add(line2);
//        views.add(line3);
//        views.add(line4);
//
//        boolean isFrist = true;
//        for (int i = 0; i < services.size(); i++) {
//            services.get(i).setPos(position);
//
//            textViews.get(i).setText(services.get(i).getService_phase_name());
//            if (services.get(i).getEnable() == 0) {
//                textViews.get(i).setTextColor(getResources().getColor(R.color.service_text_enable));
//                layouts.get(i).setEnabled(false);
//            }
//
//            if (services.get(i).getEnable() == 1 && isFrist) {
//                textViews.get(i).setSelected(true);
//                views.get(i).setVisibility(View.VISIBLE);
//                setTabFragmentContent(i);
//                isFrist = false;
//            } else {
//                views.get(i).setVisibility(View.INVISIBLE);
//                textViews.get(i).setSelected(false);
//            }
//        }
////        setTabFragmentContent(position);
//
//        preschoolFragment.setIndex(0);
//        primarySchoolFragment.setIndex(1);
//        juniorHighSchoolFragment.setIndex(2);
//        seniorHighSchoolFragment.setIndex(3);
//
//        service_tab.setVisibility(View.VISIBLE);
        FragmentManager     manager     = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_content, serviceIntrCommFragment);
        transaction.commit();
        serviceIntrCommFragment.setIndex(position - 1);
        serviceIntroduceResps = services;
    }

    private List<ServiceIntroduceResp> serviceIntroduceResps;

    public List<ServiceIntroduceResp> getServiceIntroduceResps() {
        return serviceIntroduceResps;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
//            case rl_pre_school:
//                setTabFragmentContent(0);
//
//                tabChangeOfnew(pre_school_text,
//                        primary_school_text, juior_high_school_text, senior_high_school_text, 0);
//                break;
//            case rl_primary_school:
//                setTabFragmentContent(1);
//                tabChangeOfnew(pre_school_text,
//                        primary_school_text, juior_high_school_text, senior_high_school_text, 1);
//                break;
//            case rl_juior_high_school:
//                setTabFragmentContent(2);
//                tabChangeOfnew(pre_school_text,
//                        primary_school_text, juior_high_school_text, senior_high_school_text, 2);
//                break;
//            case rl_senior_high_school:
//                setTabFragmentContent(3);
//                tabChangeOfnew(pre_school_text,
//                        primary_school_text, juior_high_school_text, senior_high_school_text, 3);
//                break;
        }
    }
//
//    private void tabChangeOfnew(TextView tv0, TextView tv1, TextView tv2, TextView tv3, int pos) {
//        switch (pos) {
//            case 0:
//                tv0.setSelected(true);
//                tv1.setSelected(false);
//                tv2.setSelected(false);
//                tv3.setSelected(false);
//                line1.setVisibility(View.VISIBLE);
//                line2.setVisibility(View.INVISIBLE);
//                line3.setVisibility(View.INVISIBLE);
//                line4.setVisibility(View.INVISIBLE);
//                break;
//            case 1:
//                tv1.setSelected(true);
//                tv0.setSelected(false);
//                tv2.setSelected(false);
//                tv3.setSelected(false);
//                line1.setVisibility(View.INVISIBLE);
//                line2.setVisibility(View.VISIBLE);
//                line3.setVisibility(View.INVISIBLE);
//                line4.setVisibility(View.INVISIBLE);
//                break;
//            case 2:
//                tv2.setSelected(true);
//                tv1.setSelected(false);
//                tv0.setSelected(false);
//                tv3.setSelected(false);
//                line1.setVisibility(View.INVISIBLE);
//                line2.setVisibility(View.INVISIBLE);
//                line3.setVisibility(View.VISIBLE);
//                line4.setVisibility(View.INVISIBLE);
//                break;
//            case 3:
//                tv3.setSelected(true);
//                tv1.setSelected(false);
//                tv2.setSelected(false);
//                tv0.setSelected(false);
//                line1.setVisibility(View.INVISIBLE);
//                line2.setVisibility(View.INVISIBLE);
//                line3.setVisibility(View.INVISIBLE);
//                line4.setVisibility(View.VISIBLE);
//                break;
//        }
//    }


}