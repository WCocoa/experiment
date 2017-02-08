package com.qiantang.neighbourmother.ui.center;

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
import com.qiantang.neighbourmother.business.data.ZcAttacheApplySubHttp;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.business.response.ApplyAttacheQuResp;
import com.qiantang.neighbourmother.logic.AuthUtil;
import com.qiantang.neighbourmother.model.AuthObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.CommisInfoSubSuccessDialog;
import com.qiantang.neighbourmother.ui.dialog.CommisInfoSubSuccessDialog.SureListener;
import com.qiantang.neighbourmother.ui.dialog.VehicleConditionDialog;
import com.qiantang.neighbourmother.ui.dialog.VehicleTypeDialog;
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
public class CommissionerApplySpecialCarActivity extends BaseActivity implements View.OnClickListener, SureListener, VehicleConditionDialog.VecoSureListener
        , VehicleTypeDialog.VetySureListener {

    private TextView back;
    private EditText et_vbrand;
    private RelativeLayout rala_vtype;
    private TextView tv_vtype;
    private RelativeLayout rala_vcondition;
    private TextView tv_vcondition;
    private EditText et_vage;
    private ImageView iv_vimg;
    private ImageView iv_vimg_delete;
    private EditText et_dage;
    private EditText et_dcn;
    private TextView submit;

    private AuthUtil authUtil;
    private ApplyAttacheQuResp resp;
    private ApplyAttacheQuResp req;

    private String[] vehicleTypeArr;
    private String[] vehicleConditionArr;

    @Override
    public int getContentView() {
        return R.layout.activity_commissioner_apply_specialcar;
    }

    @Override
    public void initView() {
        back = (TextView) findViewById(R.id.back);
        et_vbrand = (EditText) findViewById(R.id.et_vbrand);
        rala_vtype = (RelativeLayout) findViewById(R.id.rala_vtype);
        tv_vtype = (TextView) findViewById(R.id.tv_vtype);
        rala_vcondition = (RelativeLayout) findViewById(R.id.rala_vcondition);
        tv_vcondition = (TextView) findViewById(R.id.tv_vcondition);
        et_vage = (EditText) findViewById(R.id.et_vage);
        iv_vimg = (ImageView) findViewById(R.id.iv_vimg);
        iv_vimg_delete = (ImageView) findViewById(R.id.iv_vimg_delete);
        et_dage = (EditText) findViewById(R.id.et_dage);
        et_dcn = (EditText) findViewById(R.id.et_dcn);
        submit = (TextView) findViewById(R.id.submit);
        initCell();
    }

    @Override
    public void initData() {
        vehicleTypeArr = getResources().getStringArray(R.array.vehicleType_array);
        vehicleConditionArr = getResources().getStringArray(R.array.vehicleCondition_array);

        authUtil = new AuthUtil(this, handler);
        authUtil.add(new AuthObj(iv_vimg, iv_vimg_delete, 0, null, null));

        resp = (ApplyAttacheQuResp) getIntent().getSerializableExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP);
        req = (ApplyAttacheQuResp) getIntent().getSerializableExtra(IntentFinal.RECUIT_ATTACHE_INFO_REQ);

        setData(resp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        authUtil.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initEvent() {
        rala_vcondition.setOnClickListener(this);
        rala_vtype.setOnClickListener(this);
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                String[] imgs = (String[]) msg.obj;
                if (imgs != null) {
                    switch (imgs.length) {
                        case 1:
                            req.setCar_pic(imgs[0]);
                            break;

                    }
                }
                subHttp();
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

            case R.id.submit:
                ArrayList<UpFileObj> upFileObjs = getUpFiles();
                if (upFileObjs != null && upFileObjs.size() == 0) {
                    req.setCar_pic(resp.getCar_pic());
                    subHttp();
                } else if (upFileObjs != null && upFileObjs.size() > 0) {
                    new UploadImageHttp(this, handler, upFileObjs, true, 1);
                }

                break;
            case R.id.rala_vcondition:
                showVehicleConditionDialog();
                break;

            case R.id.rala_vtype:
                showVehicleTypeDialog();
                break;
        }
    }

    private void initCell() {
        float unit = 15f;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int red_width = (displayMetrics.widthPixels - (int) QLScreenUtil.dpToPx(this, unit * 2 + 60)) / 2;

        cacuCellWH(iv_vimg, red_width, red_width, (int) QLScreenUtil.dpToPx(this, 20), 0, (int) QLScreenUtil.dpToPx(this, unit), 0);
    }

    private void cacuCellWH(View view, int widht, int height, int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(left, top, right, bottom);
        layoutParams.height = height;
        layoutParams.width = widht;
        view.setLayoutParams(layoutParams);
        view.postInvalidate();
    }


    private CommisInfoSubSuccessDialog commisInfoSubSuccessDialog;

    private void showSuccessDialog() {
        if (commisInfoSubSuccessDialog == null) {
            commisInfoSubSuccessDialog = new CommisInfoSubSuccessDialog();
            commisInfoSubSuccessDialog.setOnSureListener(this);
        }
        commisInfoSubSuccessDialog.show(getSupportFragmentManager(), "commisInfoSubSuccessDialog");
    }

    private VehicleConditionDialog vehicleConditionDialog;

    private void showVehicleConditionDialog() {
        if (vehicleConditionDialog == null) {
            vehicleConditionDialog = new VehicleConditionDialog();

            vehicleConditionDialog.setOnSureListener(this);
        }
        vehicleConditionDialog.setCondition(str_vcondition);
        vehicleConditionDialog.show(getSupportFragmentManager(), "vehicleConditionDialog");
    }

    private VehicleTypeDialog vehicleTypeDialog;

    private void showVehicleTypeDialog() {
        if (vehicleTypeDialog == null) {
            vehicleTypeDialog = new VehicleTypeDialog();

            vehicleTypeDialog.setOnSureListener(this);
        }
        vehicleTypeDialog.setType(str_vtype);
        vehicleTypeDialog.show(getSupportFragmentManager(), "vehicleTypeDialog");
    }

    @Override
    public void onSure() {
        setResult(2);
        finish();
    }

    private String str_vcondition;

    @Override
    public void onVecoSure(String vehicleCondition) {
        str_vcondition = vehicleCondition;
        tv_vcondition.setText(str_vcondition);
    }

    private String str_vtype;

    @Override
    public void onVetySure(String vehicleType) {
        str_vtype = vehicleType;
        tv_vtype.setText(str_vtype);
    }

    private void setData(ApplyAttacheQuResp applyAttacheQuResp) {

        et_vbrand.setText(applyAttacheQuResp.getCar_number());

        str_vcondition = applyAttacheQuResp.getCar_condition();
        str_vtype = applyAttacheQuResp.getCar_model();

        tv_vtype.setText(str_vtype);

        tv_vcondition.setText(str_vcondition);

        et_vage.setText(applyAttacheQuResp.getCar_age());
        et_dage.setText(applyAttacheQuResp.getCar_driving());
        et_dcn.setText(applyAttacheQuResp.getCar_license_number());

        authUtil.initData();

    }

    private ArrayList<UpFileObj> getUpFiles() {
        String str_vbrand = et_vbrand.getText().toString().trim();
        String str_vage = et_vage.getText().toString().trim();
        String str_dage = et_dage.getText().toString().trim();
        String str_dcn = et_dcn.getText().toString().trim();
        str_vtype = tv_vtype.getText().toString().trim();
        str_vcondition = tv_vcondition.getText().toString().trim();


        if (TextUtils.isEmpty(str_vbrand) || TextUtils.isEmpty(str_vage)
                || TextUtils.isEmpty(str_dage) || TextUtils.isEmpty(str_dcn)
                || TextUtils.isEmpty(str_vtype) || TextUtils.isEmpty(str_vcondition)
                || (TextUtils.isEmpty(authUtil.get(0).getLocImgPath())
                && TextUtils.isEmpty(authUtil.get(0).getNetImgPath()))) {
            ToastUtils.toastLong(this, R.string.placea_please_finish_info);
            return null;
        }

        ArrayList<UpFileObj> upFileObjs = new ArrayList<UpFileObj>();

        if (!TextUtils.isEmpty(authUtil.get(0).getLocImgPath())) {
            upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(authUtil.get(0).getLocImgPath())));
        }

        req.setCar_number(str_vbrand);
        req.setCar_model(str_vtype);
        req.setCar_condition(str_vcondition);
        req.setCar_age(str_vage);
        req.setCar_driving(str_dage);
        req.setCar_license_number(str_dcn);

        return upFileObjs;
    }

    private void subHttp() {
        new ZcAttacheApplySubHttp(this, handler, req, 2);
    }
}
