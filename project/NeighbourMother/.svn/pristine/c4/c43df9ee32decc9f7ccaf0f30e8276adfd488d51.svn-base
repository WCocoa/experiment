package com.qiantang.neighbourmother.ui.community;

import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.StarExpertAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.ApplyForStarHttp;
import com.qiantang.neighbourmother.business.data.StarListHttp;
import com.qiantang.neighbourmother.business.response.StarListResp;
import com.qiantang.neighbourmother.logic.StarDecoration;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.ApplyForStarDialog;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.xrecyclerview.ProgressStyle;
import com.qiantang.xrecyclerview.XRecyclerView;

import java.util.List;


/**
 * ClassName:明星达人
 * author: Cocoa
 * date: 2016/12/7.
 */

public class StarExpertActivity extends BaseActivity implements View.OnClickListener {

    private ImageView         back;//返回
    private XRecyclerView     xrecycleview;
    private StarExpertAdapter starExpertAdapter;
    private LinearLayout      apply_for_star;//申请明星达人

    @Override
    public int getContentView() {
        return R.layout.activity_star_expert;
    }

    @Override
    public void initView() {
        xrecycleview = (XRecyclerView) findViewById(R.id.xrecycleview);
        back = (ImageView) findViewById(R.id.back);
        apply_for_star = (LinearLayout) findViewById(R.id.apply_for_star);
    }

    @Override
    public void initData() {
        starExpertAdapter = new StarExpertAdapter(this);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecycleview.setLayoutManager(layoutManager);

        int size = (int) getResources().getDimension(R.dimen.app_left_right_size);

        xrecycleview.addItemDecoration(new StarDecoration(2, size, true));
        xrecycleview.setRefreshProgressStyle(ProgressStyle.SysProgress);
//        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrecycleview.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        xrecycleview.setArrowImageView(R.mipmap.iconfont_downgrey);

        xrecycleview.setAdapter(starExpertAdapter);
//        starExpertAdapter.notifyDataSetChanged();

        getHttpData(true, true, 1);
    }

    private void getHttpData(boolean isFirstLoad, boolean isShowProgress, int what) {
        int    offset = isFirstLoad ? 0 : starExpertAdapter.getList().size();

        new StarListHttp(this, handler, offset, what, isShowProgress);
    }


    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        xrecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getHttpData(true, false, 1);
            }

            @Override
            public void onLoadMore() {
                getHttpData(false, false, 2);
            }
        });
        apply_for_star.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                setData((List<StarListResp>) msg.obj, true);
                break;
            case 2:
                setData((List<StarListResp>) msg.obj, false);
                break;
            case 3:
                showDialog();
                break;
        }
        closeProgressDialog();
    }

    ApplyForStarDialog dialog;

    private void showDialog() {
        if (dialog == null) {
            dialog = new ApplyForStarDialog();
        }

        dialog.show(getSupportFragmentManager(), "apply_for_star");
        dialog.setOnSureListener(new ApplyForStarDialog.ApplyForStarListener() {
            @Override
            public void onSure() {

                dialog.dismiss();
            }
        });
    }


    private void setData(List<StarListResp> starListResps, boolean isFirst) {


        if (isFirst) {

            starExpertAdapter.getList().clear();

        }
        if (starListResps != null && starListResps.size() > 0) {
            starExpertAdapter.getList().addAll(starListResps);
            if (starListResps.size() < 10) {
                xrecycleview.setLoadingMoreEnabled(false);
            } else {
                xrecycleview.setLoadingMoreEnabled(true);
            }
        } else {
            ToastUtils.toastshort(this, isFirst ? getString(R.string.app_pull_not_data) : getString(R.string.app_pull_already_last_page));
        }
        starExpertAdapter.notifyDataSetChanged();
        xrecycleview.refreshComplete();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.apply_for_star:
                new ApplyForStarHttp(this, handler, true, 3);
                break;
        }
    }
}
