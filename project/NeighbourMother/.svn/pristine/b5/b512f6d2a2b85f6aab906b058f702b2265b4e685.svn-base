package com.qiantang.neighbourmother.ui.community;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.SendPosterAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.PublicPosterHttp;
import com.qiantang.neighbourmother.business.data.UploadImageHttp;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.business.request.PublicPosterReq;
import com.qiantang.neighbourmother.business.response.DiscussionGroupResp;
import com.qiantang.neighbourmother.logic.CreateTagsTextView;
import com.qiantang.neighbourmother.model.PublicPosterItemObj;
import com.qiantang.neighbourmother.model.SposterObj;
import com.qiantang.neighbourmother.model.TagObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;
import com.qiantang.neighbourmother.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quliang on 16-12-5.
 */

public class SendPosterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView    back;
    private ListView     listview;
    private LinearLayout add_content;
    private LinearLayout add_tag;
    private LinearLayout send_post;
    private TextView add_tag_tv;

    private SendPosterAdapter   sendPosterAdapter;
    private CreateTagsTextView  createTagsTextView;
    private String              strTags;
    private DiscussionGroupResp discussionGroupResp;

    @Override
    public int getContentView() {
        return R.layout.activity_sendposter;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        listview = (ListView) findViewById(R.id.listview);
        add_content = (LinearLayout) findViewById(R.id.add_content);
        add_tag = (LinearLayout) findViewById(R.id.add_tag);
        send_post = (LinearLayout) findViewById(R.id.send_post);
        add_tag_tv = (TextView) findViewById(R.id.add_tag_tv);
    }

    @Override
    public void initData() {
        discussionGroupResp = (DiscussionGroupResp) getIntent().getSerializableExtra(IntentFinal.INTENT_DISCUSSION_GROUP_OBJ);
        createTagsTextView = new CreateTagsTextView();
        loadFooter();
        sendPosterAdapter = new SendPosterAdapter(this, handler);
        sendPosterAdapter.getDataList().add(new SposterObj());
        listview.addFooterView(footer);
        listview.setAdapter(sendPosterAdapter);

    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        add_content.setOnClickListener(this);
        add_tag.setOnClickListener(this);
        send_post.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                String[] imgs = (String[]) msg.obj;
                if (imgs != null) {
                    switch (imgs.length) {
                        case 1:
                            sendPosterAdapter.getDataList().get(0).setNetImgPath(imgs[0]);

                            break;
                        case 2:
                            sendPosterAdapter.getDataList().get(0).setNetImgPath(imgs[0]);
                            if (!TextUtils.isEmpty(sendPosterAdapter.getDataList().get(1).getLocImgPath())) {
                                sendPosterAdapter.getDataList().get(1).setNetImgPath(imgs[1]);
                            } else if (!TextUtils.isEmpty(sendPosterAdapter.getDataList().get(2).getLocImgPath())) {
                                sendPosterAdapter.getDataList().get(2).setNetImgPath(imgs[1]);
                            }
                            break;
                        case 3:
                            sendPosterAdapter.getDataList().get(0).setNetImgPath(imgs[0]);
                            sendPosterAdapter.getDataList().get(1).setNetImgPath(imgs[1]);
                            sendPosterAdapter.getDataList().get(2).setNetImgPath(imgs[2]);
                            break;
                    }


                    PublicPosterReq publicPosterReq = new PublicPosterReq();
                    publicPosterReq.setLabel(strTags);
                    ArrayList<PublicPosterItemObj> publicPosterItemObjs = new ArrayList<PublicPosterItemObj>();
                    for (int i = 0; i < sendPosterAdapter.getDataList().size(); i++) {
                        SposterObj sposterObj = sendPosterAdapter.getDataList().get(i);
                        if (i == 0) {
                            publicPosterReq.setGroup_id(discussionGroupResp == null ? "0" : discussionGroupResp.getGroup_id());
                            publicPosterReq.setTitle(sposterObj.getTitle());
                        }

                        PublicPosterItemObj publicPosterItemObj = new PublicPosterItemObj();
                        publicPosterItemObj.setPic(TextUtils.isEmpty(sposterObj.getNetImgPath()) ? "" : sposterObj.getNetImgPath());
                        publicPosterItemObj.setText(TextUtils.isEmpty(sposterObj.getContent()) ? "" : sposterObj.getContent());
                        publicPosterItemObjs.add(publicPosterItemObj);
                    }
                    publicPosterReq.setContent(publicPosterItemObjs);
                    new PublicPosterHttp(this, handler, publicPosterReq, 2);
                }
                break;
            case 2:
                closeProgressDialog();
                setResult(10);
                finish();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_content:
                sendPosterAdapter.notifyDataSetChanged();
                enableAddImg();
                break;

            case R.id.add_tag:
                startActivityForResult(new Intent(this, TagActivity.class), 1);
                break;
            case R.id.send_post:
//                sendPosterAdapter.notifyDataSetChanged();
                SposterObj sposterObj0 = sendPosterAdapter.getDataList().get(0);
                if (firstItem(sposterObj0)) {

                    ArrayList<UpFileObj> upFileObjs = new ArrayList<UpFileObj>();

                    for (int i = 0; i < sendPosterAdapter.getDataList().size(); i++) {
                        SposterObj sposterObj = sendPosterAdapter.getDataList().get(i);
                        if (!TextUtils.isEmpty(sposterObj.getLocImgPath())) {
                            upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(sposterObj.getLocImgPath())));
                        }
                    }
                    new UploadImageHttp(this, handler, upFileObjs, true, 1);
                }
                break;
        }
    }

    private boolean firstItem(SposterObj sposterObj0) {
        boolean ret = false;

        if (TextUtils.isEmpty(sposterObj0.getLocImgPath())) {
            ToastUtils.toastLong(this, R.string.sposter_please_add_img);
            return ret;
        }

        if (TextUtils.isEmpty(sposterObj0.getTitle())) {
            ToastUtils.toastLong(this, R.string.sposter_please_add_title);
            return ret;
        }

        if (TextUtils.isEmpty(sposterObj0.getContent())) {
            ToastUtils.toastLong(this, R.string.sposter_please_add_content);
            return ret;
        }
        return true;
    }

    private void enableAddImg() {
        int length = sendPosterAdapter.getDataList().size();
        if (length < 3) {
            switch (sendPosterAdapter.getDataList().size()) {
                case 1:
                    if (firstItem(sendPosterAdapter.getDataList().get(0))) {
                        sendPosterAdapter.getDataList().add(new SposterObj());
                        sendPosterAdapter.notifyDataSetChanged();
                    }
                    break;
                case 2:
                    SposterObj sposterObj1 = sendPosterAdapter.getDataList().get(1);
                    if (TextUtils.isEmpty(sposterObj1.getContent())) {
                        ToastUtils.toastLong(this, R.string.sposter_please_add_content);
                        return;
                    }
                    sendPosterAdapter.getDataList().add(new SposterObj());
                    sendPosterAdapter.notifyDataSetChanged();
                    break;
            }
        } else {
            ToastUtils.toastLong(this, R.string.sposter_already_max);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sendPosterAdapter.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 3:
                String str = PropertyConfig.getInstance(this).getString(IntentFinal.PREF_PUBLIC_COMM_ALREADY_CHOOSE_TAG);
                ArrayList<TagObj> tags = new Gson().fromJson(str, new TypeToken<List<TagObj>>() {
                }.getType());

                if (tags != null && tags.size() > 0) {

                    StringBuilder sb = new StringBuilder("");
                    for (TagObj ta : tags) {
                        AppLog.D("ta.getTitle():" + ta.getName());
                        sb.append(ta.getLable_id() + ",");
                    }
                    strTags = sb.toString();
                    if (!TextUtils.isEmpty(strTags) && strTags.contains(",")) {
                        strTags = strTags.substring(0, strTags.length() - 1);
                    }
                    createTagsTextView.newsCreateTag(this, tags, linear_tag);

                    footer.setVisibility(View.VISIBLE);

                    add_tag_tv.setText(R.string.sposter_already_add_tag);
                    add_tag_tv.setTextColor(getResources().getColor(R.color.app_special_style_color_normal));
                } else {
                    footer.setVisibility(View.GONE);
                    add_tag_tv.setText(R.string.sposter_add_tag);
                    add_tag_tv.setTextColor(getResources().getColor(R.color.app_item_normal));
                }
                break;
        }

    }

    private View         footer;
    private LinearLayout linear_tag;

    private void loadFooter() {
        footer = (View) getLayoutInflater().inflate(R.layout.layout_add_tag, null);
        footer.setVisibility(View.GONE);
        linear_tag = (LinearLayout) footer.findViewById(R.id.linear_tag);
    }
}
