package com.qiantang.neighbourmother.ui.order.attache;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.LookOverServiceAdapter;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

/**
 * ClassName:查看服务
 * author: Cocoa
 * date: 2016/9/27.
 */

public class AttacheLookOverServiceActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;//返回
    private LookOverServiceAdapter lookOverServiceAdapter;
    private XListView xListView;//
    private RelativeLayout rl_add;//隐藏添加按钮
    private TextView btn_add;//继续添加

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
    }

    @Override
    public void initData() {
        rl_add.setVisibility(View.VISIBLE);
        lookOverServiceAdapter = new LookOverServiceAdapter(this);
        xListView.setAdapter(lookOverServiceAdapter);
    }


    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_add:
                startActivity(new Intent(this, UploadPicturesActivity.class));
                break;
        }
    }
}
