package com.qiantang.neighbourmother.ui.order.attache;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.UploadImageHttp;
import com.qiantang.neighbourmother.business.data.UploadPicturesHttp;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.business.request.UploadPictureReq;
import com.qiantang.neighbourmother.logic.AuthUtil;
import com.qiantang.neighbourmother.model.AuthObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLScreenUtil;
import com.qiantang.neighbourmother.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * ClassName:上传照片
 * author: Cocoa
 * date: 2016/9/27.
 */

public class UploadPicturesActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private ImageView iv_img1;
    private ImageView iv_img2;
    private ImageView iv_img3;
    private ImageView iv_img1_delete;
    private ImageView iv_img2_delete;
    private ImageView iv_img3_delete;
    private TextView btn_submit;//提交
    private String order_id;
    private AuthUtil authUtil;
    private EditText input_service_desc;
    private String service_desc;

    @Override
    public int getContentView() {
        return R.layout.activity_upload_pictures;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        input_service_desc = (EditText) findViewById(R.id.input_service_desc);
        iv_img1 = (ImageView) findViewById(R.id.iv_img1);
        iv_img2 = (ImageView) findViewById(R.id.iv_img2);
        iv_img3 = (ImageView) findViewById(R.id.iv_img3);
        iv_img1_delete = (ImageView) findViewById(R.id.iv_img1_delete);
        iv_img2_delete = (ImageView) findViewById(R.id.iv_img2_delete);
        iv_img3_delete = (ImageView) findViewById(R.id.iv_img3_delete);
        btn_submit = (TextView) findViewById(R.id.btn_submit);

        initCell();
        authUtil = new AuthUtil(this, handler);


    }

    @Override
    public void initData() {
        order_id = getIntent().getStringExtra(IntentFinal.INTENT_ATTACHE_LOOKOVER_SERVICE);
        authUtil.add(new AuthObj(iv_img1, iv_img1_delete, 0, null, null));
        authUtil.add(new AuthObj(iv_img2, iv_img2_delete, 0, null, null));
        authUtil.add(new AuthObj(iv_img3, iv_img3_delete, 0, null, null));
        authUtil.initData();
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                String[] imgs = (String[]) msg.obj;
                setImages(imgs);
                break;
            case 2:
                ToastUtils.toastshort(this, "上传成功！");
                setResult(1);
                finish();
                break;
        }
    }

    private void setImages(String[] imgs) {
        UploadPictureReq upload = new UploadPictureReq();
        if (imgs != null && imgs.length > 0) {
            if (TextUtils.isEmpty(imgs[0])) {
                upload.setImage_1("");
            } else {
                upload.setImage_1(imgs[0]);
            }
            if (imgs.length > 1) {
                if (TextUtils.isEmpty(imgs[1])) {
                    upload.setImage_2("");
                } else {
                    upload.setImage_2(imgs[1]);
                }
            }
            if (imgs.length > 2) {
                if (TextUtils.isEmpty(imgs[2])) {
                    upload.setImage_3("");
                } else {
                    upload.setImage_3(imgs[2]);
                }
            }
        }

        upload.setOrder_id(order_id);
        upload.setContent(service_desc);
        new UploadPicturesHttp(this, handler, upload, 2, true);

    }


    private ArrayList<UpFileObj> getSubmitImg() {

        service_desc = input_service_desc.getText().toString().trim();
        String locImg0 = authUtil.get(0).getLocImgPath();
        String locImg1 = authUtil.get(1).getLocImgPath();
        String locImg2 = authUtil.get(2).getLocImgPath();
        ArrayList<UpFileObj> upFileObjs = null;
        if (validate(service_desc, locImg0, locImg1, locImg2)) {

            upFileObjs = new ArrayList<UpFileObj>();
            if (!TextUtils.isEmpty(locImg0)) {
                upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(locImg0)));
                AppLog.D("file0:" + locImg0);
            }

            if (!TextUtils.isEmpty(locImg1)) {
                upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(locImg1)));
                AppLog.D("file1:" + locImg1);
            }

            if (!TextUtils.isEmpty(locImg2)) {
                upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(locImg2)));
                AppLog.D("file2:" + locImg2);
            }
        }
        return upFileObjs;
    }

    private boolean validate(String service_desc, String locImg0, String locImg1, String locImg2) {
        if (TextUtils.isEmpty(locImg0) && TextUtils.isEmpty(locImg1) && TextUtils.isEmpty(locImg2)) {
            ToastUtils.toastshort(this, "请选择图片！");
            return false;
        }



        return true;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_submit:
                ArrayList<UpFileObj> upFileObjs = getSubmitImg();
                if (upFileObjs != null)
                    new UploadImageHttp(this, handler, upFileObjs, true, 1);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        authUtil.onActivityResult(requestCode, resultCode, data);
    }


    private void initCell() {
        float unit = 15f;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int red_width = (displayMetrics.widthPixels - (int) QLScreenUtil.dpToPx(this, unit * 2 + 22)) / 3;

        cacuCellWH(iv_img1, red_width, red_width, 0, 0, (int) QLScreenUtil.dpToPx(this, 0), 0);
        cacuCellWH(iv_img2, red_width, red_width, (int) QLScreenUtil.dpToPx(this, 10), 0, (int) QLScreenUtil.dpToPx(this, 0), 0);
        cacuCellWH(iv_img3, red_width, red_width, (int) QLScreenUtil.dpToPx(this, 10), 0, (int) QLScreenUtil.dpToPx(this, 0), 0);


    }

    /**
     * 计算创建相对布局下图片寛高
     *
     * @param view
     * @param widht
     * @param height
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    private void cacuCellWH(View view, int widht, int height, int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(left, top, right, bottom);
        layoutParams.height = height;
        layoutParams.width = widht;
        view.setLayoutParams(layoutParams);
        view.postInvalidate();
    }
}
