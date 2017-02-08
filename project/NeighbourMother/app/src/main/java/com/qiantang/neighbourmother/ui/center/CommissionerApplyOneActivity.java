package com.qiantang.neighbourmother.ui.center;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.ApplyAttacheQuResp;
import com.qiantang.neighbourmother.model.PCityObj;
import com.qiantang.neighbourmother.model.PDistrictObj;
import com.qiantang.neighbourmother.model.PProvinceObj;
import com.qiantang.neighbourmother.sqlite.CityOperation;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.IsOwnerDialog;
import com.qiantang.neighbourmother.ui.dialog.SexDialog;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.picker.AddressPicker;

import java.util.ArrayList;

//import static com.qiantang.neighbourmother.R.id.et_tel;

/**
 * ClassName:孩子信息
 * author: Cocoa
 * date: 2016/9/20.
 */
public class CommissionerApplyOneActivity extends BaseActivity implements View.OnClickListener, SexDialog.SexListener, IsOwnerDialog.IsOwnerListener {

    private TextView       back;
    private TextView       apply_status;
    private EditText       et_name;
    private RelativeLayout rala_sex;
    private TextView       tv_sex;
    private EditText       et_age;
    //    private EditText et_tel;
    private RelativeLayout rela_province;
    private TextView       tv_province;
    private EditText       et_atneighbourhood;
    private EditText       et_detailAddress;
    private RelativeLayout rela_atOwner;
    private TextView       tv_atOwner;
    private RelativeLayout rela_techerDegree;
    private EditText       tv_techerDegree;
    private EditText       et_industry;
    private TextView       submit;

    private ApplyAttacheQuResp applyAttacheQuResp;

    private String[] sex_array;
    private String[] owner_array;

    @Override
    public int getContentView() {
        return R.layout.activity_commissioner_apply_one;
    }

    @Override
    public void initView() {
        back = (TextView) findViewById(R.id.back);
        apply_status = (TextView) findViewById(R.id.apply_status);
        et_name = (EditText) findViewById(R.id.et_name);
        rala_sex = (RelativeLayout) findViewById(R.id.rala_sex);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        et_age = (EditText) findViewById(R.id.et_age);
//        et_tel=(EditText)findViewById(R.id.et_tel);
        rela_province = (RelativeLayout) findViewById(R.id.rela_province);
        tv_province = (TextView) findViewById(R.id.tv_province);
        et_atneighbourhood = (EditText) findViewById(R.id.et_atneighbourhood);
        et_detailAddress = (EditText) findViewById(R.id.et_detailAddress);
        rela_atOwner = (RelativeLayout) findViewById(R.id.rela_atOwner);
        tv_atOwner = (TextView) findViewById(R.id.tv_atOwner);
        rela_techerDegree = (RelativeLayout) findViewById(R.id.rela_techerDegree);
        tv_techerDegree = (EditText) findViewById(R.id.tv_techerDegree);
        et_industry = (EditText) findViewById(R.id.et_industry);
        submit = (TextView) findViewById(R.id.submit);
    }

    @Override
    public void initData() {
        sex_array = getResources().getStringArray(R.array.showSexDialog_array);
        owner_array = getResources().getStringArray(R.array.isOwnerArray);
        applyAttacheQuResp = (ApplyAttacheQuResp) getIntent().getSerializableExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP);
        setData(applyAttacheQuResp);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        rala_sex.setOnClickListener(this);
        rela_province.setOnClickListener(this);
        rela_atOwner.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rala_sex:
                showSexDialog();
                break;
            case R.id.rela_province:
                showAddressPicker(view);
                break;
            case R.id.rela_atOwner:
                showIsOwnerDialog();
                break;
            case R.id.submit:

                ApplyAttacheQuResp req = getJsAttacheApplySubReq();
                if (req != null) {
                    Intent intent = new Intent(this, CommissionerApplyTwoActivity.class);
                    intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP, applyAttacheQuResp);
                    intent.putExtra(IntentFinal.RECUIT_ATTACHE_INFO_REQ, req);
                    startActivityForResult(intent, 1);
                }
                break;
        }
    }

    private int       sexType;
    private SexDialog sexDialog;

    private void showSexDialog() {
        if (sexDialog == null) {
            sexDialog = new SexDialog();
            sexDialog.setOnSexListener(this);
        }
        sexDialog.show(getSupportFragmentManager(), "sexDialog");
    }

    @Override
    public void onSex(int sex) {
        sexType = sex + 1;
        tv_sex.setText(sex_array[sex]);
    }

    private CityOperation cityOperation;
    private PProvinceObj  province;
    private PCityObj      city;
    private PDistrictObj  county;

    public void showAddressPicker(View view) {
        if (cityOperation == null)
            cityOperation = new CityOperation(this);
        ArrayList<PProvinceObj> data   = cityOperation.getProvince();
        AddressPicker           picker = new AddressPicker(this, data);
        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(PProvinceObj province, PCityObj city, PDistrictObj county) {
                CommissionerApplyOneActivity.this.province = province;
                CommissionerApplyOneActivity.this.city = city;
                CommissionerApplyOneActivity.this.county = county;
                tv_province.setText(province.getAreaName() + " " + city.getAreaName() + " " + county.getAreaName());
            }
        });
        picker.show();
    }

    private int           isOwner;
    private IsOwnerDialog isOwnerDialog;

    private void showIsOwnerDialog() {
        if (isOwnerDialog == null) {
            isOwnerDialog = new IsOwnerDialog();
            isOwnerDialog.setOnIsOwnerListener(this);
        }
        isOwnerDialog.show(getSupportFragmentManager(), "isOwnerDialog");
    }

    @Override
    public void isOwner(int owner) {
        isOwner = owner + 1;
        tv_atOwner.setText(owner_array[owner]);
    }


    private void setData(ApplyAttacheQuResp applyAttacheQuResp) {


        switch (applyAttacheQuResp.getApply_status()) {
            case 0:
                apply_status.setVisibility(View.VISIBLE);
                apply_status.setText(R.string.placea_apply_doing);
                apply_status.setBackgroundColor(getResources().getColor(R.color.placea_apply_doing));
                break;
            case 1:
                apply_status.setVisibility(View.VISIBLE);
                apply_status.setText(R.string.placea_apply_success);
                apply_status.setBackgroundColor(getResources().getColor(R.color.placea_apply_success));
                break;

            case 2:
                apply_status.setVisibility(View.VISIBLE);
                apply_status.setText(R.string.placea_apply_failure);
                apply_status.setBackgroundColor(getResources().getColor(R.color.placea_apply_failure));
                break;
            case 10:
                apply_status.setVisibility(View.GONE);
                break;
            default:
                apply_status.setVisibility(View.GONE);
                break;
        }

        et_name.setText(applyAttacheQuResp.getUser_name());

        sexType = applyAttacheQuResp.getUser_gender();
        AppLog.D("sexType:" + sexType);
        if (applyAttacheQuResp.getUser_gender() > 0) {
            tv_sex.setText(sex_array[applyAttacheQuResp.getUser_gender() - 1]);
        } else {
            tv_sex.setText("");
        }

        et_age.setText(applyAttacheQuResp.getUser_age());
//        et_tel.setText(applyAttacheQuResp.getUser_tel());

        if (applyAttacheQuResp.getProvince() != 0) {
            province = new PProvinceObj(applyAttacheQuResp.getProvince(), applyAttacheQuResp.getProvince_name());
        }
        if (applyAttacheQuResp.getCity() != 0) {
            city = new PCityObj(applyAttacheQuResp.getCity(), applyAttacheQuResp.getCity_name());
        }
        if (applyAttacheQuResp.getDistrict() != 0) {
            county = new PDistrictObj(applyAttacheQuResp.getDistrict(), applyAttacheQuResp.getDistrict_name());
        }

        tv_province.setText(applyAttacheQuResp.getProvince_name() + " " + applyAttacheQuResp.getCity_name() + " " + applyAttacheQuResp.getDistrict_name());
        et_atneighbourhood.setText(applyAttacheQuResp.getUser_community());
        et_detailAddress.setText(applyAttacheQuResp.getUser_address());

        isOwner = applyAttacheQuResp.getOwner();
        AppLog.D("isOwner:" + isOwner);
        if (applyAttacheQuResp.getOwner() > 0) {
            tv_atOwner.setText(owner_array[applyAttacheQuResp.getOwner() - 1]);
        } else {
            tv_atOwner.setText("");
        }

        tv_techerDegree.setText(applyAttacheQuResp.getEducation());
        et_industry.setText(applyAttacheQuResp.getIndustry());
    }

    private ApplyAttacheQuResp getJsAttacheApplySubReq() {

        String name = et_name.getText().toString().trim();
        String age  = et_age.getText().toString().trim();
//        String tel=et_tel.getText().toString().trim();
        String atneighbourhood = et_atneighbourhood.getText().toString().trim();
        String detailAddress   = et_detailAddress.getText().toString().trim();
        String techerDegree    = tv_techerDegree.getText().toString().trim();
        String industry        = et_industry.getText().toString().trim();
// ||TextUtils.isEmpty(tel)
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age)
            || TextUtils.isEmpty(atneighbourhood)
            || TextUtils.isEmpty(detailAddress) || TextUtils.isEmpty(techerDegree)
            || TextUtils.isEmpty(industry) || sexType == 0 || isOwner == 0
            || province == null || city == null || county == null) {
            ToastUtils.toastLong(this, R.string.placea_please_finish_info);
            return null;
        }

        ApplyAttacheQuResp jsAttacheApplySubReq = new ApplyAttacheQuResp();
        jsAttacheApplySubReq.setUser_name(name);
        jsAttacheApplySubReq.setUser_gender(sexType);
        jsAttacheApplySubReq.setUser_age(age);
//        jsAttacheApplySubReq.setUser_tel(tel);
        jsAttacheApplySubReq.setProvince(province.getAreaId());
        jsAttacheApplySubReq.setProvince_name(province.getAreaName());
        jsAttacheApplySubReq.setCity(city.getAreaId());
        jsAttacheApplySubReq.setCity_name(city.getAreaName());
        jsAttacheApplySubReq.setDistrict(county.getAreaId());
        jsAttacheApplySubReq.setDistrict_name(county.getAreaName());
        jsAttacheApplySubReq.setUser_community(atneighbourhood);
        jsAttacheApplySubReq.setUser_address(detailAddress);
        jsAttacheApplySubReq.setOwner(isOwner);
        jsAttacheApplySubReq.setEducation(techerDegree);
        jsAttacheApplySubReq.setIndustry(industry);

        return jsAttacheApplySubReq;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                setResult(2);
                finish();
                break;
        }
    }
}