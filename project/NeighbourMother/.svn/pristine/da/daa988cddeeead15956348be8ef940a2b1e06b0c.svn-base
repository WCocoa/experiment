package com.qiantang.neighbourmother.ui.center;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.data.ChildInfoHttp;
import com.qiantang.neighbourmother.business.response.ChildInfoResp;
import com.qiantang.neighbourmother.logic.ChildContacts;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.PropertyConfig;
import com.qiantang.neighbourmother.widget.CircleImageView;

/**
 * ClassName:孩子信息
 * author: Cocoa
 * date: 2016/9/20.
 */
public class ChildrenInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView       back;//返回
    private TextView        children_info_edit;//编辑
    private CircleImageView head_photo;//头像
    private TextView        tv_name;//真实姓名
    private TextView        tv_nickname;//昵称
    private TextView        tv_sex;//性别
    private TextView        tv_age;//年龄
    private TextView        tv_school;//学校
    private TextView        tv_grade;//年级
    private TextView        tv_class;//班级
    private TextView        tv_household_head_relation;//户主关系
    private TextView        tv_home_address;//家庭住址
    private TextView        tv_phone;//手机号
    private TextView        tv_emergency_contact;//紧急联系人
    private TextView        tv_emergency_phone;//紧急手机

    private String[] relationship_array;

    @Override
    public int getContentView() {
        return R.layout.activity_children_info;
    }

    @Override
    public void initView() {
        children_info_edit = (TextView) findViewById(R.id.children_info_edit);
        back = (ImageView) findViewById(R.id.back);
        head_photo = (CircleImageView) findViewById(R.id.head_photo);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_school = (TextView) findViewById(R.id.tv_school);
        tv_grade = (TextView) findViewById(R.id.tv_grade);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_class = (TextView) findViewById(R.id.tv_class);
        tv_household_head_relation = (TextView) findViewById(R.id.tv_household_head_relation);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_home_address = (TextView) findViewById(R.id.tv_home_address);
        tv_emergency_contact = (TextView) findViewById(R.id.tv_emergency_contact);
        tv_emergency_phone = (TextView) findViewById(R.id.tv_emergency_phone);
        children_info_edit.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initData() {
        relationship_array = getResources().getStringArray(R.array.relationShip_array);
        new ChildInfoHttp(this, handler, 1);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        children_info_edit.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                ChildInfoResp childInfoResp = (ChildInfoResp) msg.obj;
                AppLog.D("childInfoResp:" + childInfoResp);
                if (childInfoResp != null) {
                    ChildContacts.saveChildInfo(this, childInfoResp);
                    ChildContacts.getChildInfo(this);
                    setChildInfo(childInfoResp);
                }
                children_info_edit.setVisibility(View.VISIBLE);
                closeProgressDialog();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.children_info_edit:
                startActivityForResult(new Intent(this, ChildrenInfoEditActivity.class), 1);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void setChildInfo(ChildInfoResp childInfo) {
        AppLog.D("setChildInfo:" + childInfo);
        if (childInfo == null)
            return;

        display(head_photo, APIConfig.BASE_IMG_URL + childInfo.getChild_avatar(), R.mipmap.default_img);
        tv_name.setText(childInfo.getChild_name());
        tv_nickname.setText(childInfo.getChild_nickname());
        switch (childInfo.getChild_gender()) {
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
        tv_school.setText(childInfo.getChild_school());
        tv_grade.setText(childInfo.getChild_grade());
        tv_class.setText(childInfo.getChild_class());
        tv_age.setText(childInfo.getChild_age()+"");

        tv_nickname.setText(childInfo.getChild_nickname());

        if (childInfo.getChild_relationship() > 0) {
            tv_household_head_relation.setText(relationship_array[childInfo.getChild_relationship() - 1]);
        }
        tv_home_address.setText(childInfo.getChild_address());
        if (TextUtils.isEmpty(childInfo.getChild_phone())){

            tv_phone.setText(PropertyConfig.getInstance(this).getString(UserContacts.USER_ACOUNT));
        }else {
            tv_phone.setText(childInfo.getChild_phone());
        }

        tv_emergency_contact.setText(childInfo.getChild_contact());
        tv_emergency_phone.setText(childInfo.getChild_phone2());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                setChildInfo(ChildContacts.getChildInfo(this));
                break;
        }
    }
}
