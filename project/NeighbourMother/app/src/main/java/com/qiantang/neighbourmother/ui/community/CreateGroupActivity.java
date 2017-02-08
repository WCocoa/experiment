package com.qiantang.neighbourmother.ui.community;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.SubmitGroupHttp;
import com.qiantang.neighbourmother.business.data.UploadImageHttp;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.business.request.CreateGroupReq;
import com.qiantang.neighbourmother.logic.TakeImage;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.CreateGroupDialog;
import com.qiantang.neighbourmother.util.FileFinal;
import com.qiantang.neighbourmother.util.QLBitmapUtil;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.RoundAngleImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by quliang on 16-12-19.
 */

public class CreateGroupActivity extends BaseActivity implements View.OnClickListener {
    private ImageView           back;
    private TextView            create;
    private RoundAngleImageView imghead;
    private EditText            groupName;
    private EditText            groupTitle;
    private TakeImage           takeImage;
    private Bitmap              groupBitmap;
    private String              title;
    private String              theme;

    @Override
    public int getContentView() {
        return R.layout.activity_create_group;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        create = (TextView) findViewById(R.id.create);
        imghead = (RoundAngleImageView) findViewById(R.id.imghead);
        groupName = (EditText) findViewById(R.id.groupName);
        groupTitle = (EditText) findViewById(R.id.groupTitle);
        takeImage = new TakeImage(this, handler, true);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        create.setOnClickListener(this);
        imghead.setOnClickListener(this);
        takeImage.setOnPicClipListener(new TakeImage.PicClipListener() {
            @Override
            public void picClipCall(Bitmap bitmap) {
                if (bitmap != null) {
                    groupBitmap = bitmap;
                    QLBitmapUtil.saveBitmap(bitmap,
                            FileFinal.PATH_TEMP
                            + FileFinal.USER_IMG_HAED);
                    imghead.setImageBitmap(bitmap);
                }
            }
        });
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                String[] imgs = (String[]) msg.obj;
                if (imgs != null) {

                    new SubmitGroupHttp(this, handler, true, new CreateGroupReq(imgs[0], title, theme), 2);
                }
                break;
            case 2:
                showDialog();
                break;
        }
    }

    CreateGroupDialog createGroupDialog;

    private void showDialog() {
        if (createGroupDialog == null) {
            createGroupDialog = new CreateGroupDialog();
        }
        createGroupDialog.show(getSupportFragmentManager(), "CreateGroupDialog");
        createGroupDialog.setOnSureListener(new CreateGroupDialog.CreateGroupListener() {
            @Override
            public void onSure() {
                setResult(10);
                finish();
            }

            @Override
            public void onCancel() {

            }
        });
    }

    private boolean checkInput(String title, String theme) {
        if (groupBitmap == null) {
            ToastUtils.toastshort(this, R.string.input_image);
            return false;
        }
        if (TextUtils.isEmpty(title)) {
            ToastUtils.toastshort(this, R.string.input_title);
            return false;
        }
        if (TextUtils.isEmpty(theme)) {
            ToastUtils.toastshort(this, R.string.input_theme);
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takeImage.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.create:
                title = groupName.getText().toString().trim();
                theme = groupTitle.getText().toString().trim();
                if (checkInput(title, theme)) {
                    ArrayList<UpFileObj> upFileObjs = new ArrayList<UpFileObj>();
                    upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(FileFinal.PATH_TEMP
                                                                        + FileFinal.USER_IMG_HAED)));
                    new UploadImageHttp(this, handler, upFileObjs, true, 1);
                }
                break;
            case R.id.imghead:
                takeImage.showDialog();
                break;
        }
    }
}
