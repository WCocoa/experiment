package com.qiantang.neighbourmother.ui.home;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.AttacheListAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.AttacheListHttp;
import com.qiantang.neighbourmother.business.request.AttacheLIstReq;
import com.qiantang.neighbourmother.business.response.AttacheListResp;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.model.ToAttacheDetailObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

import java.util.List;

/**
 * ClassName:专员列表
 * author: Cocoa
 * date: 2016/9/21.
 */
public class AttacheListActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;//返回
    private XListView xListView;//
    private AttacheListAdapter attacheListAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_attache_list;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        xListView = (XListView) findViewById(R.id.xListView);

    }

    @Override
    public void initData() {
        attacheListAdapter = new AttacheListAdapter(this);
        xListView.setAdapter(attacheListAdapter);
        xListView.setPullLoadEnable(false);
        getHttpData(true, true, 1);
    }

    private void getHttpData(boolean isFirstLoad, boolean isShowProgress, int what) {

        UserInfoResp userinfo = UserContacts.getUserInfo(this);
        String district = "";
        String community = "";
        int count = isFirstLoad == true ? 0 : attacheListAdapter.getDataList().size();
        if (userinfo != null) {
            district = userinfo.getDistrict() + "";
            community = userinfo.getUser_community();
        }
        new AttacheListHttp(this, handler, new AttacheLIstReq(district,community,count), what, isShowProgress);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i <= 0) {
                    return;
                }

                Intent intent = new Intent(AttacheListActivity.this, AttacheDetailsActivity.class);
                intent.putExtra(IntentFinal.INTENT_ATTACHE_OBJ, new ToAttacheDetailObj(attacheListAdapter.getDataList().get(i - 1).getUser_id(), 0));
                startActivityForResult(intent, DownOrderActivity.RESULT_ATTACHER);
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

                setData((List<AttacheListResp>) msg.obj, true);
                break;
            case 2:
                setData((List<AttacheListResp>) msg.obj, false);
                break;
        }
        closeProgressDialog();
    }

    private void setData(List<AttacheListResp> attacheList, boolean isFirst) {
        if (isFirst) {
            attacheListAdapter.getDataList().clear();
        }
        if (attacheList != null && attacheList.size() > 0) {
            attacheListAdapter.getDataList().addAll(attacheList);
            if (attacheList.size() < 10) {
                xListView.setPullLoadEnable(false);
            } else {
                xListView.setPullLoadEnable(true);
            }
        } else {
            ToastUtils.toastshort(this, isFirst ? getString(R.string.app_pull_not_data) : getString(R.string.app_pull_already_last_page));
        }
        attacheListAdapter.notifyDataSetChanged();
        xListView.aotuRefreshComplete();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void clossRefresh() {
        super.clossRefresh();
        xListView.aotuRefreshComplete();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == DownOrderActivity.RESULT_ATTACHER) {
            setResult(DownOrderActivity.RESULT_ATTACHER, data);
            finish();
        }
    }
}