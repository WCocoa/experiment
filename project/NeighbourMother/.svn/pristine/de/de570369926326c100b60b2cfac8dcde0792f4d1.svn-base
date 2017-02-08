package com.qiantang.neighbourmother.ui.order.user;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.LookOverServiceAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.LookOverServiceHttp;
import com.qiantang.neighbourmother.business.response.LookOverServiceResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.order.attache.UploadPicturesActivity;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

import java.util.List;

import static com.baidu.platform.comapi.util.f.A;

/**
 * ClassName:查看服务
 * author: Cocoa
 * date: 2016/9/27.
 */

public class LookOverServiceActivity extends BaseActivity implements View.OnClickListener {

    private ImageView                 back;//返回
    private LookOverServiceAdapter    lookOverServiceAdapter;
    private XListView                 xListView;//
    private RelativeLayout            rl_add;//继续添加隐藏显示
    private TextView                  btn_add;//继续添加
    private String                    order_id;
    private List<LookOverServiceResp> lookOverServiceResps;
    private RelativeLayout            rl_default_page;

    @Override
    public int getContentView() {
        return R.layout.activity_look_over_service;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        xListView = (XListView) findViewById(R.id.xListView);
        rl_add = (RelativeLayout) findViewById(R.id.rl_add);
        btn_add = (TextView) findViewById(R.id.btn_add);
        rl_default_page = (RelativeLayout) findViewById(R.id.rl_default_page);
    }

    @Override
    public void initData() {

        order_id = getIntent().getStringExtra(IntentFinal.INTENT_ATTACHE_ORDER_DETAILS_TO_SERVICE);
        boolean flag = getIntent().getBooleanExtra(IntentFinal.INTENT_ATTACHE_OR_USER_ORDER_DETAILS_TO_SERVICE, false);

        if (flag) {
            rl_add.setVisibility(View.VISIBLE);
        } else {
            rl_add.setVisibility(View.GONE);
        }
        lookOverServiceAdapter = new LookOverServiceAdapter(this);
        xListView.setAdapter(lookOverServiceAdapter);
        xListView.setPullLoadEnable(false);
        getHttpData(true, true, 1);
    }


    private void getHttpData(boolean isFirst, boolean isShowProgress, int what) {
        int count = isFirst == true ? 0 : lookOverServiceAdapter.getDataList().size();
        new LookOverServiceHttp(this, handler,  order_id ,count, what, isShowProgress);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getHttpData(true, false, 1);
            }

            @Override
            public void onLoadMore() {
                getHttpData(false, false, 2);
            }
        });
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                lookOverServiceResps = (List<LookOverServiceResp>) msg.obj;
                setData(lookOverServiceResps, true);
                break;
            case 2:
                lookOverServiceResps = (List<LookOverServiceResp>) msg.obj;
                setData(lookOverServiceResps, false);
                break;
        }
        if (lookOverServiceAdapter.getDataList() == null || lookOverServiceAdapter.getDataList().size() <= 0) {
            rl_default_page.setVisibility(View.VISIBLE);
            xListView.setVisibility(View.GONE);
        } else {
            rl_default_page.setVisibility(View.GONE);
            xListView.setVisibility(View.VISIBLE);
        }
        closeProgressDialog();
    }

    private void setData(List<LookOverServiceResp> lookOverServiceResps, boolean isFirst) {
        if (isFirst) {
            lookOverServiceAdapter.getDataList().clear();
        }
        if (lookOverServiceResps != null && lookOverServiceResps.size() > 0) {
            lookOverServiceAdapter.getDataList().addAll(lookOverServiceResps);
            if (lookOverServiceResps.size() < 10) {
                xListView.setPullLoadEnable(false);
            } else {
                xListView.setPullLoadEnable(true);
            }
        } else {
            ToastUtils.toastshort(this, isFirst ? getString(R.string.app_pull_not_data) : getString(R.string.app_pull_already_last_page));
        }
        lookOverServiceAdapter.notifyDataSetChanged();
        xListView.aotuRefreshComplete();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            xListView.aotuRefresh();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_add:
                Intent intent = new Intent(this, UploadPicturesActivity.class);
                intent.putExtra(IntentFinal.INTENT_ATTACHE_LOOKOVER_SERVICE, order_id);
                startActivityForResult(intent, 1);
                break;
        }
    }
}
