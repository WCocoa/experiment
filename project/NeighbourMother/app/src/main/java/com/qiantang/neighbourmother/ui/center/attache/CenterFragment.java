package com.qiantang.neighbourmother.ui.center.attache;

import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.data.GetCenterMoneyHttp;
import com.qiantang.neighbourmother.business.response.CenterMoneyResp;
import com.qiantang.neighbourmother.business.response.ChildInfoResp;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.logic.ChildContacts;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.BaseFragment;
import com.qiantang.neighbourmother.ui.activity.PhotoViewActivity;
import com.qiantang.neighbourmother.ui.center.AboutActivity;
import com.qiantang.neighbourmother.ui.center.ChildrenInfoActivity;
import com.qiantang.neighbourmother.ui.center.CommissionerRecruitActivity;
import com.qiantang.neighbourmother.ui.center.EditDataActivity;
import com.qiantang.neighbourmother.ui.center.SettingsActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.widget.CircleImageView;

import static com.qiantang.neighbourmother.business.API.CENTER_MONEY;


/**
 * ClassName:中心
 * author: Cocoa
 * date: 2016/9/17.
 */
public class CenterFragment extends BaseFragment implements View.OnClickListener {

    private TextView        edit_info;//编辑信息
    private CircleImageView image_head_portrait;//用户头像
    private TextView        user_name;//用户名
    private TextView        service_text;
    private ImageView       user_sex;//用户性别

    private RelativeLayout  rl_children_info;//孩子信息点击
    private CircleImageView children_image_head;//孩子头像
    private TextView        children_name;//孩子姓名
    private TextView        children_sex;//孩子性别
    private TextView        children_school;//孩子学校及班级
    private RelativeLayout  rl_commissioner_info;//专员资料
    private RelativeLayout  rl_commissioner_recruit;//招募专员
    private RelativeLayout  rl_setting;//设置
    private RelativeLayout  rl_customer_service_phone;//客服电话
    private RelativeLayout  rl_about_us;//产品介绍
    private RelativeLayout  rl_account_balance;//账户余额
    private TextView        balance;//显示金额
    private CenterMoneyResp centerMoneyResp;
    private ImageView       user_type;

    private ImageView icon_right6;//收入账户右边箭头
    private UserInfoResp userInfoResp;

    @Override
    public int getContentView() {
        return R.layout.fragment_attache_center;
    }

    @Override
    public void initView(View view) {
        edit_info = (TextView) view.findViewById(R.id.edit_info);
        image_head_portrait = (CircleImageView) view.findViewById(R.id.image_head_portrait);
        user_name = (TextView) view.findViewById(R.id.user_name);
        user_sex = (ImageView) view.findViewById(R.id.user_sex);
        rl_children_info = (RelativeLayout) view.findViewById(R.id.rl_children_info);
        children_image_head = (CircleImageView) view.findViewById(R.id.children_image_head);
        children_name = (TextView) view.findViewById(R.id.children_name);
        children_sex = (TextView) view.findViewById(R.id.children_sex);
        children_school = (TextView) view.findViewById(R.id.children_school);
        rl_commissioner_info = (RelativeLayout) view.findViewById(R.id.rl_commissioner_info);
        rl_commissioner_recruit = (RelativeLayout) view.findViewById(R.id.rl_commissioner_recruit);
        rl_setting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        rl_customer_service_phone = (RelativeLayout) view.findViewById(R.id.rl_customer_service_phone);
        rl_about_us = (RelativeLayout) view.findViewById(R.id.rl_about_us);
        service_text = (TextView) view.findViewById(R.id.service_text);
        rl_account_balance = (RelativeLayout) view.findViewById(R.id.rl_account_balance);
        balance = (TextView) view.findViewById(R.id.balance);
        user_type = (ImageView) view.findViewById(R.id.user_type);
        icon_right6 = (ImageView) view.findViewById(R.id.icon_right6);
    }

    @Override
    public void initData() {
//        edit_info.setVisibility(View.INVISIBLE);
        setUserInfo();


    }

    @Override
    public void onResume() {
        super.onResume();
        new GetCenterMoneyHttp(getActivity(), handler, 2, 1, false);
        setChildInfo(ChildContacts.getChildInfo(getActivity()));
    }

    @Override
    public void initEvent() {
        edit_info.setOnClickListener(this);
        rl_children_info.setOnClickListener(this);
        rl_commissioner_info.setOnClickListener(this);
        rl_commissioner_recruit.setOnClickListener(this);
        rl_customer_service_phone.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
        rl_about_us.setOnClickListener(this);
        rl_account_balance.setOnClickListener(this);
        image_head_portrait.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                centerMoneyResp = (CenterMoneyResp) msg.obj;
                setMoney(centerMoneyResp);
                closeProgressDialog();
                break;
        }
    }

    private void setMoney(CenterMoneyResp centerMoneyResp) {
        if (centerMoneyResp != null)
            balance.setText(String.format("%1.2f", centerMoneyResp.getMoney() / 100));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_info:
                startActivityForResult(new Intent(getActivity(), EditDataActivity.class), 1);
                break;
            case R.id.rl_children_info:
                startActivity(new Intent(getActivity(), ChildrenInfoActivity.class));
                break;
            case R.id.rl_commissioner_info:
                startActivity(new Intent(getActivity(), AttacheOneselfDetailsActivity.class));
                break;
            case R.id.rl_commissioner_recruit:
                startActivity(new Intent(getActivity(), CommissionerRecruitActivity.class));
                break;
            case R.id.rl_setting:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.rl_customer_service_phone:
                String tel = getString(R.string.cusromer_phone_call);
                if (!tel.equals("")) {
                    Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                                                                         + tel));
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                }
                break;
            case R.id.rl_about_us:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.rl_account_balance:
                Intent intent = new Intent(getActivity(), AttacheAccountActivity.class);

                startActivity(intent);
                break;
            case R.id.image_head_portrait:
                Intent in = new Intent(getActivity(), PhotoViewActivity.class);
                in.putExtra(IntentFinal.INTENT_IMGURL_TO_PHOTOVIEWACTIVITY, APIConfig.BASE_IMG_URL + userInfoResp.getUser_avatar());
                startActivity(in);
                break;
        }
    }

    private void setUserInfo() {
//        UserInfoResp userInfoResp = UserContacts.getUserInfo(getActivity());
//        ((BaseActivity) getActivity()).display(image_head_portrait, API.BASE_IMG_URL + userInfoResp.getUser_avatar(), R.mipmap.default_img);
//        user_name.setText(userInfoResp.getUser_name());
//        user_sex.setSelected(userInfoResp.getUser_gender() == 2);
//        service_text.setText();
        userInfoResp = UserContacts.getUserInfo(getActivity());
        ((BaseActivity) getActivity()).display(image_head_portrait, APIConfig.BASE_IMG_URL + userInfoResp.getUser_avatar(), R.mipmap.default_img);
        user_name.setText(userInfoResp.getUser_name());
        AppLog.D("userInfoResp.getUser_gender():" + userInfoResp.getUser_gender());
        switch (userInfoResp.getUser_gender()) {
            case 1:
                user_sex.setSelected(false);
                break;
            case 2:
                user_sex.setSelected(true);
                break;
            default:
                user_sex.setVisibility(View.INVISIBLE);
                break;
        }

        switch (userInfoResp.getUser_type()) {
            case 1:
                user_type.setVisibility(View.INVISIBLE);
                break;
            case 2:
                user_type.setVisibility(View.VISIBLE);
                user_type.setImageResource(R.mipmap.icon_commissioner);
                break;
            case 3:
                user_type.setVisibility(View.VISIBLE);
                rl_children_info.setVisibility(View.GONE);
                icon_right6.setVisibility(View.INVISIBLE);
                rl_account_balance.setClickable(false);
                user_type.setImageResource(R.mipmap.icon_assistant);
                break;
            default:
                user_type.setVisibility(View.INVISIBLE);
                break;
        }
        StringBuilder builder = new StringBuilder();
        String[]      type    = userInfoResp.getServant_type_array_string();
        for (int i = 0; i < type.length; i++) {
            builder.append(type[i]);
            if (i == type.length - 1) {
                builder.append("");
            } else {
                builder.append("  ");
            }
        }
        service_text.setText(builder.toString());

    }

    private void setChildInfo(ChildInfoResp childInfo) {
//        ((BaseActivity) getActivity()).display(children_image_head, API.BASE_IMG_URL + childInfo.getChild_avatar(), R.mipmap.default_img);
//        user_name.setText(childInfo.getChild_name());
//        children_sex.setText(childInfo.getChild_gender() == 1 ? "男" : "女");
//        user_name.setText(childInfo.getChild_school() + " " + childInfo.getChild_grade());

        if (childInfo == null)
            return;

        ((BaseActivity) getActivity()).display(children_image_head, APIConfig.BASE_IMG_URL + childInfo.getChild_avatar(), R.mipmap.default_img);
        children_name.setText(childInfo.getChild_name());

        switch (childInfo.getChild_gender()) {
            case 0:
                children_sex.setText("");
                break;
            case 1:
                children_sex.setText("男");
                break;
            case 2:
                children_sex.setText("女");
                break;
        }
        if (TextUtils.isEmpty(childInfo.getChild_school()) || TextUtils.isEmpty(childInfo.getChild_grade())) {
            children_school.setText("");
        } else {
            children_school.setText(childInfo.getChild_school() + " " + childInfo.getChild_grade());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                setUserInfo();
                break;
        }
    }
}
