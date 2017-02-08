package com.qiantang.neighbourmother.ui.center;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.JsAttacheApplySubHttp;
import com.qiantang.neighbourmother.business.data.UploadImageHttp;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.business.response.ApplyAttacheQuResp;
import com.qiantang.neighbourmother.logic.AuthUtil;
import com.qiantang.neighbourmother.model.AuthObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.CommisInfoSubSuccessDialog;
import com.qiantang.neighbourmother.ui.dialog.CommisInfoSubSuccessDialog.SureListener;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLScreenUtil;
import com.qiantang.neighbourmother.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * ClassName:孩子信息
 * author: Cocoa
 * date: 2016/9/20.
 */
public class CommissionerApplyTwoActivity extends BaseActivity implements View.OnClickListener, SureListener {

    private TextView back;
    private RelativeLayout rela_standard;
    private ImageView iv_standard;
    private ImageView iv_standard_delete;
    private RelativeLayout rela_idcard;
    private ImageView iv_idcard;
    private ImageView iv_idcard_delete;
    private TextView btn_previous;
    private TextView btn_next;

    private AuthUtil authUtil;

    private ApplyAttacheQuResp resp;
    private ApplyAttacheQuResp req;

    @Override
    public int getContentView() {
        return R.layout.activity_commissioner_apply_two;
    }

    @Override
    public void initView() {
        back = (TextView) findViewById(R.id.back);
        iv_standard = (ImageView) findViewById(R.id.iv_standard);
        iv_standard_delete = (ImageView) findViewById(R.id.iv_standard_delete);
        iv_idcard = (ImageView) findViewById(R.id.iv_idcard);
        iv_idcard_delete = (ImageView) findViewById(R.id.iv_idcard_delete);
        btn_previous = (TextView) findViewById(R.id.btn_previous);
        btn_next = (TextView) findViewById(R.id.btn_next);
        initCell();
    }

    @Override
    public void initData() {

        resp = (ApplyAttacheQuResp) getIntent().getSerializableExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP);
        req = (ApplyAttacheQuResp) getIntent().getSerializableExtra(IntentFinal.RECUIT_ATTACHE_INFO_REQ);

        authUtil = new AuthUtil(this, handler);
        authUtil.add(new AuthObj(iv_standard, iv_standard_delete, 0, null, null));
        authUtil.add(new AuthObj(iv_idcard, iv_idcard_delete, 0, null, null));

        setData(resp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 2:
                setResult(2);
                finish();
                break;
            default:
                authUtil.onActivityResult(requestCode, resultCode, data);
                break;
        }
        closeProgressDialog();
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        btn_previous.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                String[] imgs = (String[]) msg.obj;
                if (imgs != null) {
                    switch (imgs.length) {
                        case 1:
                            if (!TextUtils.isEmpty(authUtil.get(0).getLocImgPath())) {
                                req.setPic_standard(imgs[0]);
                            }
                            if (!TextUtils.isEmpty(authUtil.get(1).getLocImgPath())) {
                                req.setPic_idcard(imgs[0]);
                            }

                            break;
                        case 2:
                            req.setPic_standard(imgs[0]);
                            req.setPic_idcard(imgs[1]);
                            break;
                    }
                }
                startAc();
                break;

            case 2:
                closeProgressDialog();
                showSuccessDialog();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_previous:
                break;
            case R.id.btn_next:
                ArrayList<UpFileObj> upFileObjs = getUpFiles();
                if (upFileObjs != null && upFileObjs.size() == 0) {
                    req.setPic_standard(resp.getPic_standard());
                    req.setPic_idcard(resp.getPic_idcard());
                    startAc();

                } else if (upFileObjs != null && upFileObjs.size() > 0) {
                    new UploadImageHttp(this, handler, upFileObjs, true, 1);
                }

//            startActivity(new Intent(this,CommissionerApplyNurseActivity.class));
                break;
        }
    }

    private void initCell() {
        float unit = 15f;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int red_width = (displayMetrics.widthPixels - (int) QLScreenUtil.dpToPx(this, unit * 2 + 60)) / 2;

        cacuCellWH(iv_standard, red_width, red_width, 0, 0, (int) QLScreenUtil.dpToPx(this, unit), 0);
        cacuCellWH(iv_idcard, red_width, red_width, (int) QLScreenUtil.dpToPx(this, unit), 0, 0, 0);
    }

    private void cacuCellWH(View view, int widht, int height, int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(left, top, right, bottom);
        layoutParams.height = height;
        layoutParams.width = widht;
        view.setLayoutParams(layoutParams);
        view.postInvalidate();
    }

    CommisInfoSubSuccessDialog commisInfoSubSuccessDialog;

    private void showSuccessDialog() {
        if (commisInfoSubSuccessDialog == null) {
            commisInfoSubSuccessDialog = new CommisInfoSubSuccessDialog();
            commisInfoSubSuccessDialog.setOnSureListener(this);
        }
        commisInfoSubSuccessDialog.show(getSupportFragmentManager(), "commisInfoSubSuccessDialog");
    }


    @Override
    public void onSure() {
        setResult(2);
        finish();
    }


    private void setData(ApplyAttacheQuResp applyAttacheQuResp) {
        if (CommissionerRecruitActivity.SPECIAL_RECUIT_TYPE == 1) {
            btn_next.setText(R.string.placea_submit_finish);
        }

        String pic_standard = applyAttacheQuResp.getPic_standard();
        String idcard = applyAttacheQuResp.getPic_idcard();

        AuthObj authObj0 = authUtil.get(0);
        AuthObj authObj1 = authUtil.get(1);

        authObj0.setNetImgPath(pic_standard);
        authObj1.setNetImgPath(idcard);

        authObj0.setHaveImg(TextUtils.isEmpty(pic_standard) ? 0 : 1);
        authObj1.setHaveImg(TextUtils.isEmpty(idcard) ? 0 : 1);

        authUtil.initData();
    }

    private ArrayList<UpFileObj> getUpFiles() {
        AuthObj authObj0 = authUtil.get(0);
        AuthObj authObj1 = authUtil.get(1);

        if (TextUtils.isEmpty(authObj0.getNetImgPath()) && TextUtils.isEmpty(authObj0.getLocImgPath())) {
            ToastUtils.toastLong(this, R.string.placea_please_finish_info);
            return null;
        }

        if (TextUtils.isEmpty(authObj1.getNetImgPath()) && TextUtils.isEmpty(authObj1.getLocImgPath())) {
            ToastUtils.toastLong(this, R.string.placea_please_finish_info);
            return null;
        }

        ArrayList<UpFileObj> upFileObjs = new ArrayList<UpFileObj>();

        if (!TextUtils.isEmpty(authObj0.getLocImgPath())) {
            upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(authObj0.getLocImgPath())));
        }
        if (!TextUtils.isEmpty(authObj1.getLocImgPath())) {
            upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(authObj1.getLocImgPath())));
        }
        return upFileObjs;
    }

    private void startAc() {
        Intent intent = null;
        switch (CommissionerRecruitActivity.SPECIAL_RECUIT_TYPE) {
            case 1:
                new JsAttacheApplySubHttp(this, handler, req, 2);
                break;
            case 2:
                intent = new Intent(this, CommissionerApplyNurseActivity.class);
                intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP, resp);
                intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_REQ, req);
                startActivityForResult(intent, 1);
                break;
            case 3:
                intent = new Intent(this, CommissionerApplyCoachActivity.class);
                intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP, resp);
                intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_REQ, req);
                startActivityForResult(intent, 1);
                break;
            case 4:
                intent = new Intent(this, CommissionerApplySpecialCarActivity.class);
                intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP, resp);
                intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_REQ, req);
                startActivityForResult(intent, 1);
                break;
        }
    }


}
