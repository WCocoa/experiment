package com.qiantang.neighbourmother.ui.center;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.NeighbourMotherApplication;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.data.UploadImageHttp;
import com.qiantang.neighbourmother.business.data.UserInfoSubHttp;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.business.request.UserInfoSubReq;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.logic.TakeImage;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.model.LocInfoObj;
import com.qiantang.neighbourmother.model.PCityObj;
import com.qiantang.neighbourmother.model.PDistrictObj;
import com.qiantang.neighbourmother.model.PProvinceObj;
import com.qiantang.neighbourmother.sqlite.CityOperation;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.SexDialog;
import com.qiantang.neighbourmother.ui.home.HomeFragment;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.FileFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;
import com.qiantang.neighbourmother.util.QLBitmapUtil;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.CircleImageView;
import com.qiantang.neighbourmother.widget.picker.AddressPicker;

import java.io.File;
import java.util.ArrayList;

/**
 * ClassName:编辑资料
 * author: Cocoa
 * date: 2016/9/21.
 */
public class EditDataActivity extends BaseActivity implements View.OnClickListener, SexDialog.SexListener {
    private ImageView       back;//返回
    private RelativeLayout  rl_head_photo;//头像选择
    private CircleImageView head_photo;//头像
    private EditText        input_nickname;//昵称
    private RelativeLayout  rl_sex;//选择性别
    private TextView        tv_sex;//性别
    private RelativeLayout  rl_provinces_city_district;//选择省市区
    private TextView        tv_provinces_city_district;//省市区
    private EditText        input_housing_estate;//所在小区
    private EditText        input_detailed_address;//详细地址
    private TakeImage       takeImage;
    private TextView        btn_save;//保存
    private TextView        nickname_text;//用户身份：昵称 专员身份：姓名

    private UserInfoResp   userInfoResp;
    private UserInfoSubReq userInfoSubReq;
    private int            role;

    @Override
    public int getContentView() {
        return R.layout.activity_edit_data;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        nickname_text = (TextView) findViewById(R.id.nickname_text);
        rl_head_photo = (RelativeLayout) findViewById(R.id.rl_head_photo);
        head_photo = (CircleImageView) findViewById(R.id.head_photo);
        input_nickname = (EditText) findViewById(R.id.input_nickname);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        rl_provinces_city_district = (RelativeLayout) findViewById(R.id.rl_provinces_city_district);
        tv_provinces_city_district = (TextView) findViewById(R.id.tv_provinces_city_district);
        input_housing_estate = (EditText) findViewById(R.id.input_housing_estate);
        input_detailed_address = (EditText) findViewById(R.id.input_detailed_address);
        takeImage = new TakeImage(this, handler, true);
        btn_save = (TextView) findViewById(R.id.btn_save);
    }

    @Override
    public void initData() {
        role = PropertyConfig.getInstance(this).getInt(UserContacts.USER_ROLE);
        if (role == 1) {
            nickname_text.setText(R.string.nickname_text);
        } else {
            nickname_text.setText(R.string.username_text);
        }
        setUserInfo();

    }

    private Bitmap headBitmap;

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        rl_head_photo.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        rl_provinces_city_district.setOnClickListener(this);

        takeImage.setOnPicClipListener(new TakeImage.PicClipListener() {
            @Override
            public void picClipCall(Bitmap bitmap) {

                headBitmap = bitmap;
                QLBitmapUtil.saveBitmap(bitmap,
                        FileFinal.PATH_TEMP
                        + FileFinal.USER_IMG_HAED);
                head_photo.setImageBitmap(bitmap);
                AppLog.D("setOnPicClipListener.headBitmap:"+headBitmap);
            }
        });
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                String[] imgs = (String[]) msg.obj;
                if (imgs != null) {
                    userInfoSubReq.setUser_avatar(imgs[0]);
                    new UserInfoSubHttp(this, handler, userInfoSubReq, 2);
                }
                break;
            case 2:
                UserInfoSubReq userInfoSubReq1 = (UserInfoSubReq) msg.obj;
                saveUserInfo(userInfoSubReq1);
                setResult(2);
                finish();
                closeProgressDialog();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_head_photo:
                takeImage.showDialog();
                break;
            case R.id.rl_sex:
                initSexDialog();
                break;
            case R.id.rl_provinces_city_district:
                showAddressPicker(view);
                break;
            case R.id.btn_save:
                userInfoSubReq = getReq(userInfoResp.getUser_avatar());
                if (userInfoSubReq != null) {
                    if (headBitmap != null) {
                        ArrayList<UpFileObj> upFileObjs = new ArrayList<UpFileObj>();
                        upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(FileFinal.PATH_TEMP
                                                                            + FileFinal.USER_IMG_HAED)));
                        new UploadImageHttp(this, handler, upFileObjs, true, 1);
                    } else {
                        new UserInfoSubHttp(this, handler, userInfoSubReq, 2);
                    }
                    break;
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takeImage.onActivityResult(requestCode, resultCode, data);
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
                EditDataActivity.this.province = province;
                EditDataActivity.this.city = city;
                EditDataActivity.this.county = county;
                tv_provinces_city_district.setText(province.getAreaName() + " " + city.getAreaName() + " " + county.getAreaName());
            }
        });
        picker.show();

    }

    private int       sexType;
    private SexDialog sexDialog;

    private void initSexDialog() {
        if (sexDialog == null) {
            sexDialog = new SexDialog();
            sexDialog.setOnSexListener(this);
        }
        sexDialog.setSex(tv_sex.getText().toString().trim());
        sexDialog.show(getSupportFragmentManager(), "sexDialog");
    }

    private void setUserInfo() {
        if (cityOperation == null)
            cityOperation = new CityOperation(this);
        userInfoResp = UserContacts.getUserInfo(this);
        AppLog.D("userInfoResp.getUser_avatar():" + userInfoResp.getUser_avatar());
        display(head_photo, APIConfig.BASE_IMG_URL + userInfoResp.getUser_avatar(), R.mipmap.default_img);
        if (role == 1) {
            input_nickname.setText(userInfoResp.getUser_nickname());
        } else {
            input_nickname.setText(userInfoResp.getUser_name());
        }

        sexType = userInfoResp.getUser_gender();
        switch (userInfoResp.getUser_gender()) {
            case 0:
                tv_sex.setText("");
                break;
            case 1:
                tv_sex.setText("男");
                break;
            case 2:
                tv_sex.setText("女");
                break;
        }

        if (userInfoResp.getProvince() != 0) {
            province = new PProvinceObj(userInfoResp.getProvince(), userInfoResp.getProvince_name());
        }
        if (userInfoResp.getCity() != 0) {
            city = new PCityObj(userInfoResp.getCity(), userInfoResp.getCity_name());
        }
        if (userInfoResp.getDistrict() != 0) {
            county = new PDistrictObj(userInfoResp.getDistrict(), userInfoResp.getDistrict_name());
        }

        tv_provinces_city_district.setText(userInfoResp.getProvince_name() + " " + userInfoResp.getCity_name() + " " + userInfoResp.getDistrict_name());
        input_housing_estate.setText(userInfoResp.getUser_community());
        input_detailed_address.setText(userInfoResp.getUser_address());
    }

    @Override
    public void onSex(int sex) {
        sexType = sex + 1;
        switch (sexType) {
            case 0:
                tv_sex.setText("");
                break;
            case 1:
                tv_sex.setText("男");
                break;
            case 2:
                tv_sex.setText("女");
                break;
        }
    }

    private UserInfoSubReq getReq(String imgPath) {
        UserInfoSubReq userInfoSubReq = new UserInfoSubReq();

        if (TextUtils.isEmpty(userInfoResp.getUser_avatar()) && headBitmap == null) {
            ToastUtils.toastLong(this, R.string.eda_please_choose_head);
            return null;
        }


        String strNick = input_nickname.getText().toString().trim();
        if (role == 1) {

            if (TextUtils.isEmpty(strNick)) {
                ToastUtils.toastLong(this, R.string.eda_please_input_nickname);
                return null;
            }
        } else {

            if (TextUtils.isEmpty(strNick)) {
                ToastUtils.toastLong(this, R.string.eda_please_input_name);
                return null;
            }
        }

        if (sexType == 0) {
            ToastUtils.toastLong(this, R.string.eda_please_choose_sex);
            return null;
        }

        if (province == null) {
            ToastUtils.toastLong(this, R.string.eda_please_input_pcd_info);
            return null;
        }

        if (city == null) {
            ToastUtils.toastLong(this, R.string.eda_please_input_pcd_info);
            return null;
        }

        if (county == null) {
            ToastUtils.toastLong(this, R.string.eda_please_input_pcd_info);
            return null;
        }

        String strCommunity = input_housing_estate.getText().toString().trim();
        if (TextUtils.isEmpty(strCommunity)) {
            ToastUtils.toastLong(this, R.string.eda_please_input_xq_info);
            return null;
        }

        String strAddress = input_detailed_address.getText().toString().trim();
//        if (TextUtils.isEmpty(strAddress)) {
//            ToastUtils.toastLong(this, R.string.eda_please_input_address_info);
//            return null;
//        }


        userInfoSubReq.setUser_avatar(imgPath);

        userInfoSubReq.setProvince(province.getAreaId());
        userInfoSubReq.setCity(city.getAreaId());
        userInfoSubReq.setDistrict(county.getAreaId());

        userInfoSubReq.setUser_community(strCommunity);

        userInfoSubReq.setUser_address(strAddress);

//        userInfoSubReq.setUser_nickname(strNick);


        userInfoSubReq.setUser_gender(sexType);


        userInfoSubReq.setType(role);

        if (role == 1) {
            userInfoSubReq.setUser_nickname(strNick);
            userInfoSubReq.setUser_name(userInfoResp.getUser_name());
        } else {
            userInfoSubReq.setUser_nickname(userInfoResp.getUser_nickname());
            userInfoSubReq.setUser_name(strNick);
        }


        return userInfoSubReq;
    }

    private void saveUserInfo(UserInfoSubReq userInfoSubReq) {
        UserInfoResp userInfoResp1 = UserContacts.getUserInfo(this);

        userInfoResp1.setCity(userInfoSubReq.getCity());
        userInfoResp1.setDistrict(userInfoSubReq.getDistrict());

        if (cityOperation == null)
            cityOperation = new CityOperation(this);

        userInfoResp1.setProvince(userInfoSubReq.getProvince());
        userInfoResp1.setProvince_name(province.getAreaName());

        userInfoResp1.setCity(userInfoSubReq.getCity());
        userInfoResp1.setCity_name(city.getAreaName());

        userInfoResp1.setDistrict(userInfoSubReq.getDistrict());
        userInfoResp1.setDistrict_name(county.getAreaName());

        userInfoResp1.setUser_name(userInfoSubReq.getUser_name());

        userInfoResp1.setUser_community(userInfoSubReq.getUser_community());
        userInfoResp1.setUser_avatar(userInfoSubReq.getUser_avatar());
        userInfoResp1.setUser_nickname(userInfoSubReq.getUser_nickname());
        userInfoResp1.setUser_gender(userInfoSubReq.getUser_gender());
        userInfoResp1.setUser_address(userInfoSubReq.getUser_address());
        UserContacts.saveUserInfo(this, userInfoResp1, false);

        LocInfoObj locInfoObj = ((NeighbourMotherApplication) getApplication()).cur_locInfoObj;

        if (locInfoObj.getProvince_id() != userInfoSubReq.getProvince() || locInfoObj.getCity_id() != userInfoSubReq.getCity()
            || locInfoObj.getDistrict_id() != userInfoSubReq.getDistrict()
            || !locInfoObj.getNeighbourhoodName().equals(userInfoResp1.getUser_community())) {


            locInfoObj.setProvince_id(userInfoResp1.getProvince());
            locInfoObj.setProvince_name(userInfoResp1.getProvince_name());

            locInfoObj.setCity_id(userInfoResp1.getCity());
            locInfoObj.setCity_name(userInfoResp1.getCity_name());

            locInfoObj.setDistrict_id(userInfoResp1.getDistrict());
            locInfoObj.setDistrict_name(userInfoResp1.getDistrict_name());

            locInfoObj.setNeighbourhoodName(userInfoResp1.getUser_community());
            HomeFragment.behavior = 2;
            AppLog.D("地址编辑成功");
        }
    }
}