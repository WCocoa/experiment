package com.qiantang.neighbourmother.ui.community;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.ConmUserOneSelfAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.data.ConUserOneselfHttp;
import com.qiantang.neighbourmother.business.data.DeletePostHttp;
import com.qiantang.neighbourmother.business.response.ConmUserOneSelfResp;
import com.qiantang.neighbourmother.model.ConmmunityUserObj;
import com.qiantang.neighbourmother.model.PostObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.DeletePostDialog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.CircleImageView;
import com.qiantang.neighbourmother.widget.refreshview.XListView;


/**
 * ClassName:社群个人信息
 * author: Cocoa
 * date: 2016/12/7.
 */

public class ConmUserOneSelfActivity extends BaseActivity implements View.OnClickListener {

    private XListView              xListView;
    private ConmUserOneSelfAdapter conmmunityPersonalAdapter;
    private String                 user_id;
    private RelativeLayout         rela_title_top;//隐藏title根节点
    private ImageView              visible_back;//隐藏title返回
    private TextView               visible_title;//隐藏title标题

    @Override
    public int getContentView() {
        return R.layout.activity_user_one_self;
    }

    @Override
    public void initView() {
        xListView = (XListView) findViewById(R.id.xListView);
        rela_title_top = (RelativeLayout) findViewById(R.id.rela_title_top);
        visible_back = (ImageView) findViewById(R.id.visible_back);
        visible_title = (TextView) findViewById(R.id.visible_title);
        initHeader();
    }


    @Override
    public void initData() {
        conmmunityPersonalAdapter = new ConmUserOneSelfAdapter(this);
        user_id = getIntent().getStringExtra(IntentFinal.INTENT_USER_ID_TO_USER_DETAILS);
        xListView.addHeaderView(head);
        xListView.setAdapter(conmmunityPersonalAdapter);
        xListView.setPullLoadEnable(false);
        getHttpData(true, true, 1);
    }

    private void getHttpData(boolean isFirstLoad, boolean isShowProgress, int what) {
        int    offset = isFirstLoad ? 0 : conmmunityPersonalAdapter.getDataList().size();
        new ConUserOneselfHttp(this, handler, offset, what, isShowProgress);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        visible_back.setOnClickListener(this);

        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 1) {
                    Intent intent = new Intent(ConmUserOneSelfActivity.this, PosterDetailActivity.class);
                    intent.putExtra(IntentFinal.INTENT_POSTER_DETAIL_OBJ, conmmunityPersonalAdapter.getDataList().get(position - 2));
                    startActivity(intent);
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

        xListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    showDialog(conmmunityPersonalAdapter.getDataList().get(position - 2));
                    return true;
                } else {
                    return false;
                }


            }
        });

        xListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > 0) {
                    rela_title_top.setVisibility(View.VISIBLE);
                    AlphaAnimation showAnimation = new AlphaAnimation(0, 1);
                    rela_title_top.startAnimation(showAnimation);
                    showAnimation.setInterpolator(new LinearInterpolator());
                    showAnimation.setDuration(500);
                    showAnimation.start();
                } else {
                    if (rela_title_top.getVisibility() == View.VISIBLE) {
                        rela_title_top.setVisibility(View.GONE);
                        AlphaAnimation endAnimation = new AlphaAnimation(1, 0);
                        rela_title_top.startAnimation(endAnimation);
                        endAnimation.setInterpolator(new LinearInterpolator());
                        endAnimation.setDuration(500);
                        endAnimation.start();
                    }
                }
            }
        });
    }

    DeletePostDialog deletePostDialog;
    PostObj          post;

    private void showDialog(final PostObj postObj) {
        post = new PostObj();
        if (deletePostDialog == null) {
            deletePostDialog = new DeletePostDialog();
        }
        deletePostDialog.show(getSupportFragmentManager(), "DeleteOrderDialog");
        deletePostDialog.setOnSureListener(new DeletePostDialog.DeletePostListener() {
            @Override
            public void onSure() {
                if (!TextUtils.isEmpty(postObj.getPost_id())) {
                    post.setPost_id(postObj.getPost_id());
                    new DeletePostHttp(ConmUserOneSelfActivity.this, handler, postObj.getPost_id(), 4, true);

                }
                deletePostDialog.dismiss();
            }

            @Override
            public void onCancel() {
                deletePostDialog.dismiss();
            }
        });
    }

    ConmmunityUserObj conmmunityUserObj;

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                ConmUserOneSelfResp conmmunityUserDetailsResp = (ConmUserOneSelfResp) msg.obj;
                conmmunityUserObj = conmmunityUserDetailsResp.getUser();
                setHeader(conmmunityUserObj);
                setData(conmmunityUserDetailsResp, true);
                break;
            case 2:
                setData((ConmUserOneSelfResp) msg.obj, false);
                break;
//            case 3:
//                conmmunityUserObj.setFans(conmmunityUserObj.getFans()+1);
//                fans_num.setText(conmmunityUserObj.getFans()+"");
//                break;
            case 4:
                int pos = conmmunityPersonalAdapter.getDataList().size();
                for (int i = 0; i < pos; i++) {
                    PostObj postList = conmmunityPersonalAdapter.getDataList().get(i);
                    if (post.getPost_id().equals(postList.getPost_id())) {
                        conmmunityPersonalAdapter.getDataList().remove(i);
                        break;
                    }
                }
                if (conmmunityUserObj.getPost() > 0) {
                    conmmunityUserObj.setPost(conmmunityUserObj.getPost() - 1);
                } else {
                    conmmunityUserObj.setPost(0);
                }
                post_num.setText("TA的帖子（" + conmmunityUserObj.getPost() + "）");
                conmmunityPersonalAdapter.notifyDataSetChanged();
                ToastUtils.toastshort(this, R.string.delete_order_success);
                isDelflag = true;
                break;
        }
        closeProgressDialog();
    }

    private void setHeader(ConmmunityUserObj conmmunityUserObj) {
        if (conmmunityUserObj.getUser_gender() == 1) {
            personal_head_sex.setSelected(false);
        } else if (conmmunityUserObj.getUser_gender() == 2) {
            personal_head_sex.setSelected(true);
        } else {
            personal_head_sex.setVisibility(View.INVISIBLE);
        }
        if (conmmunityUserObj.getStar() == 1) {
            is_star.setVisibility(View.VISIBLE);
        } else {
            is_star.setVisibility(View.INVISIBLE);
        }
        display(header_image, APIConfig.BASE_IMG_URL + conmmunityUserObj.getUser_avatar(), R.mipmap.default_img);
        personal_head_name.setText(conmmunityUserObj.getUser_name());
        conmmunity_focus.setText(conmmunityUserObj.getFollow());
        fans_num.setText(conmmunityUserObj.getFans() + "");
        post_num.setText("TA的帖子（" + conmmunityUserObj.getPost() + "）");
        visible_title.setText(conmmunityUserObj.getUser_name());
        details_root.setVisibility(View.VISIBLE);

    }

    private boolean isDelflag = false;

    private void setData(ConmUserOneSelfResp conmmunityUserDetailsResp, boolean isFirst) {


        if (isFirst) {

            conmmunityPersonalAdapter.getDataList().clear();

        }
        if (conmmunityUserDetailsResp.getPost() != null && conmmunityUserDetailsResp.getPost().size() > 0) {
            conmmunityPersonalAdapter.getDataList().addAll(conmmunityUserDetailsResp.getPost());
            if (conmmunityUserDetailsResp.getPost().size() < 10) {
                xListView.setPullLoadEnable(false);
            } else {
                xListView.setPullLoadEnable(true);
            }
        } else {
            ToastUtils.toastshort(this, isFirst ? getString(R.string.app_pull_not_data) : getString(R.string.app_pull_already_last_page));
        }
        conmmunityPersonalAdapter.notifyDataSetChanged();
        xListView.aotuRefreshComplete();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isDelflag){
            setResult(10);
        }else {
            setResult(-10);
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (isDelflag){
                    setResult(10);
                }else {
                    setResult(-10);
                }

                finish();
                break;
            case R.id.visible_back:
                finish();
                break;
//            case btn_fcous:
//                new ConFocusHttp(this, API.USER_DETAILS_FOCUS, handler, true, user_id, 3);
//                break;
        }
    }

    private View            head;
    private ImageView       back;//返回
    private ImageView       is_star;//明星达人标记
    private ImageView       personal_head_sex;//性别
    private CircleImageView header_image;//头像
    //    private TextView        btn_fcous;//关注
    private TextView        personal_head_name;//昵称
    private TextView        conmmunity_focus;//关注
    private TextView        fans_num;//粉丝
    private TextView        post_num;//帖子数量
    private RelativeLayout  details_root;//根节点


    private void initHeader() {
        head = getLayoutInflater().inflate(R.layout.layout_user_one_self_header, null);
        back = (ImageView) head.findViewById(R.id.back);
        details_root = (RelativeLayout) head.findViewById(R.id.details_root);
        header_image = (CircleImageView) head.findViewById(R.id.header_image);
        is_star = (ImageView) head.findViewById(R.id.is_star);
        personal_head_sex = (ImageView) head.findViewById(R.id.personal_head_sex);
//        btn_fcous = (TextView) head.findViewById(R.id.btn_fcous);
        personal_head_name = (TextView) head.findViewById(R.id.personal_head_name);
        conmmunity_focus = (TextView) head.findViewById(R.id.conmmunity_focus);
        fans_num = (TextView) head.findViewById(R.id.fans_num);
        post_num = (TextView) head.findViewById(R.id.post_num);

    }


}
