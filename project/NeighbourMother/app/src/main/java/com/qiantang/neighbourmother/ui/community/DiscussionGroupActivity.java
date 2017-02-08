package com.qiantang.neighbourmother.ui.community;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.DiscussionGroupAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.GroupListHttp;
import com.qiantang.neighbourmother.business.response.DiscussionGroupResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

import java.util.List;

/**
 * Created by quliang on 16-12-19.
 */

public class DiscussionGroupActivity extends BaseActivity implements View.OnClickListener {
    private ImageView              back;
    private XListView              xListView;
    private TextView               discussion_group;
    private DiscussionGroupAdapter discussionGroupAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_discussion_group;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        xListView = (XListView) findViewById(R.id.xListView);
        discussion_group = (TextView) findViewById(R.id.discussion_group);
    }

    @Override
    public void initData() {
        discussionGroupAdapter = new DiscussionGroupAdapter(this);
        xListView.setPullLoadEnable(false);
        xListView.setAdapter(discussionGroupAdapter);
        getHttpData(true, true, 1);

    }

    private void getHttpData(boolean isFirstLoad, boolean isShowProgress, int what) {
        int    offset = isFirstLoad ? 0 : discussionGroupAdapter.getDataList().size();

        new GroupListHttp(this, handler,offset, what, isShowProgress);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        discussion_group.setOnClickListener(this);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                Intent intent=new Intent(DiscussionGroupActivity.this,DiscussionGroupDetailActivity.class);
                intent.putExtra(IntentFinal.INTENT_DISCUSSION_GROUP_OBJ,discussionGroupAdapter.getDataList().get(position-1));
                startActivityForResult(intent,1);
                }
            }
        });

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
                setData((List<DiscussionGroupResp>) msg.obj, true);
                break;
            case 2:
                setData((List<DiscussionGroupResp>) msg.obj, false);
                break;
        }
        closeProgressDialog();
    }

    private void setData(List<DiscussionGroupResp> discussionGroupResps, boolean isFirst) {


        if (isFirst) {

            discussionGroupAdapter.getDataList().clear();

        }
        if (discussionGroupResps != null && discussionGroupResps.size() > 0) {
            discussionGroupAdapter.getDataList().addAll(discussionGroupResps);
            if (discussionGroupResps.size() < 10) {
                xListView.setPullLoadEnable(false);
            } else {
                xListView.setPullLoadEnable(true);
            }
        } else {
            ToastUtils.toastshort(this, isFirst ? getString(R.string.app_pull_not_data) : getString(R.string.app_pull_already_last_page));
        }
        discussionGroupAdapter.notifyDataSetChanged();
        xListView.aotuRefreshComplete();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==10){
            getHttpData(true, false, 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.discussion_group:
                startActivityForResult(new Intent(this, CreateGroupActivity.class), 1);
                break;
        }
    }


}
