package com.qiantang.neighbourmother.ui.order.attache;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.data.AttacheOrderAcceptHttp;
import com.qiantang.neighbourmother.business.data.AttacheOrderCompleteHttp;
import com.qiantang.neighbourmother.business.data.AttacheOrderDetailsHttp;
import com.qiantang.neighbourmother.business.data.AttacheOrderStartHttp;
import com.qiantang.neighbourmother.business.response.AttacheOrderDetailsResp;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.model.MapSetPointObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.activity.MapSetPointActivity;
import com.qiantang.neighbourmother.ui.dialog.OrderSuperadditionCostDialog;
import com.qiantang.neighbourmother.ui.order.user.LookOverServiceActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLTimeUtil;
import com.qiantang.neighbourmother.util.SpannableUtils;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.CircleImageView;


/**
 * ClassName:专员订单
 * author: Cocoa
 * date: 2016/9/27.
 */

public class AttacheOrderDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView               back;//返回
    private TextView                title;//标题
    private TextView                title_status;//标题状态
    private ImageView               order_refresh;//刷新
    private RelativeLayout          ll_children_info;//孩子信息
    private CircleImageView         user_image;//用户图像
    private TextView                user_name;//用户姓名
    private ImageView               user_sex;//性别
    private TextView                school;//学校
    private TextView                grade_class;//班级和年级
    private TextView                phone;//手机号
    private RelativeLayout          rl_phone;//拨打电话
    private TextView                choice_service_content;//选择服务内容
    private LinearLayout            ll_pick_up;//接送控制隐藏显示
    private TextView                service_date;//服务日期
    private TextView                service_starttime;//服务开始时间
    private TextView                et_js_address_start;//起点
    private TextView                et_js_address_end;//终点
    private LinearLayout            ll_js_address_start;//起点地图位置
    private LinearLayout            ll_js_address_end;//终点地图位置
    private ImageView               tv_js_address_end_prompt;//专员地址（显示 隐藏。）
    private LinearLayout            ll_nurse;//看护控制显示隐藏
    private TextView                nurse_address;//看护地点
    private TextView                repast_service;//餐饮服务
    private TextView                add_repast_text;//加餐显示文字
    private TextView                add_repast;//加餐金额
    private TextView                extra_tip;//额外小费
    private TextView                platform;//平台福利
    private ImageView               take_picture;//拍照
    private TextView                additional_expense;//追加费用
    private TextView                completed_immediately;//立即完成
    private TextView                real_payment_text;//待付款
    private TextView                real_payment_money;//金额
    private TextView                complete_service;//完成服务
    private ScrollView              sv_content;
    private RelativeLayout          rl_money;//
    private LinearLayout            ll_btn_content;//立即支付  追加小费 布局
    private AttacheOrderDetailsResp attacheOrderDetailsResp;
    private String                  order_id;
    private RelativeLayout          rl_pick_up;//接送
    private MapSetPointObj          place1MapSetPointObj;
    private MapSetPointObj          place2MapSetPointObj;
    private LinearLayout            ll_add_money;//追加金额
    private TextView                add_money;//显示追加金额
    private TextView                delete_info;//用户删除订单显示信息
    private TextView                service_demand;//服务要求
    private TextView                age;//年龄
    private Animation               rotate;
    private View                    ve_line;//年龄和学校之间的竖线

    @Override
    public int getContentView() {
        return R.layout.activity_attache_order_details;
    }

    @Override
    public void initView() {
        rl_pick_up = (RelativeLayout) findViewById(R.id.rl_pick_up);
        order_refresh = (ImageView) findViewById(R.id.order_refresh);
        ll_btn_content = (LinearLayout) findViewById(R.id.ll_btn_content);
        rl_money = (RelativeLayout) findViewById(R.id.rl_money);
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        title_status = (TextView) findViewById(R.id.title_status);
        sv_content = (ScrollView) findViewById(R.id.sv_content);
        user_image = (CircleImageView) findViewById(R.id.user_image);
        user_name = (TextView) findViewById(R.id.user_name);
        user_sex = (ImageView) findViewById(R.id.user_sex);
        school = (TextView) findViewById(R.id.school);
        grade_class = (TextView) findViewById(R.id.grade_class);
        phone = (TextView) findViewById(R.id.phone);
        rl_phone = (RelativeLayout) findViewById(R.id.rl_phone);
        choice_service_content = (TextView) findViewById(R.id.choice_service_content);
//        ll_pick_up = (LinearLayout) findViewById(R.id.ll_pick_up);
        service_date = (TextView) findViewById(R.id.service_date);
        ve_line = (View) findViewById(R.id.ve_line);
        service_starttime = (TextView) findViewById(R.id.service_starttime);
        et_js_address_start = (TextView) findViewById(R.id.et_js_address_start);
        et_js_address_end = (TextView) findViewById(R.id.et_js_address_end);
        ll_js_address_start = (LinearLayout) findViewById(R.id.ll_js_address_start);
        ll_js_address_end = (LinearLayout) findViewById(R.id.ll_js_address_end);
        tv_js_address_end_prompt = (ImageView) findViewById(R.id.tv_js_address_end_prompt);
        ll_nurse = (LinearLayout) findViewById(R.id.ll_nurse);
        nurse_address = (TextView) findViewById(R.id.nurse_address);
//        repast_service = (TextView) findViewById(R.id.repast_service);
        add_repast_text = (TextView) findViewById(R.id.add_repast_text);
        add_repast_text = (TextView) findViewById(R.id.add_repast_text);
        add_repast = (TextView) findViewById(R.id.add_repast);
        extra_tip = (TextView) findViewById(R.id.extra_tip);
        service_demand = (TextView) findViewById(R.id.service_demand);
        age = (TextView) findViewById(R.id.age);
//        platform = (TextView) findViewById(R.id.platform);
        take_picture = (ImageView) findViewById(R.id.take_picture);
        additional_expense = (TextView) findViewById(R.id.additional_expense);
        completed_immediately = (TextView) findViewById(R.id.completed_immediately);
        real_payment_text = (TextView) findViewById(R.id.real_payment_text);
        real_payment_money = (TextView) findViewById(R.id.real_payment_money);
        complete_service = (TextView) findViewById(R.id.complete_service);
        delete_info = (TextView) findViewById(R.id.delete_info);
        ll_children_info = (RelativeLayout) findViewById(R.id.ll_children_info);

    }

    @Override
    public void initData() {
        order_id = getIntent().getStringExtra(IntentFinal.INTENT_ATTACHE_ORDER_LIST);
        getData();

    }

    private void getData() {
        initRefreshView();
        AppLog.D("order_id:" + order_id);
        new AttacheOrderDetailsHttp(this, handler, order_id, 1, true);
    }


    @Override
    public void initEvent() {
        ll_add_money = (LinearLayout) findViewById(R.id.ll_add_money);
        add_money = (TextView) findViewById(R.id.add_money);
        back.setOnClickListener(this);
        complete_service.setOnClickListener(this);
        completed_immediately.setOnClickListener(this);
        additional_expense.setOnClickListener(this);
        ll_js_address_start.setOnClickListener(this);
        ll_js_address_end.setOnClickListener(this);
        take_picture.setOnClickListener(this);
        ll_children_info.setOnClickListener(this);
        rl_phone.setOnClickListener(this);
        order_refresh.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                attacheOrderDetailsResp = (AttacheOrderDetailsResp) msg.obj;
                setData(attacheOrderDetailsResp);
                closeProgressDialog();
                break;
            case 2:
                ToastUtils.toastLong(this, R.string.please_wait_user_pay);
                initData();
                closeProgressDialog();
                break;
            case 3:
                ToastUtils.toastLong(this, R.string.begin_service_please_place);
                initData();
                closeProgressDialog();
                break;
            case 4:
                ToastUtils.toastLong(this, R.string.service_already_complete);
                initData();
                closeProgressDialog();
                break;
            case 5:
                ToastUtils.toastLong(this, R.string.add_already_complete);
                initData();
                closeProgressDialog();
                break;
        }


    }

    private void setData(AttacheOrderDetailsResp attacheOrderDetailsResp) {

        int[] serviceType = attacheOrderDetailsResp.getService_cate_id();

        boolean isPickUpShow = false;
        boolean isNurseShow  = false;
        if (serviceType.length > 0) {
            for (int i = 0; i < serviceType.length; i++) {
                switch (serviceType[i]) {
                    case 1:
                        isPickUpShow = true;
                        break;
                    case 2:
                        isPickUpShow = true;
                        break;
                    case 3:
                        isNurseShow = true;
                        break;
                    case 4:
                        isNurseShow = true;
                        break;
                    case 5:
                        isNurseShow = true;
                        break;
                }
            }
        }

        AppLog.D("attacheOrderDetailsResp" + attacheOrderDetailsResp.getOrder_status());
        setOrderStatus(attacheOrderDetailsResp);
        place1MapSetPointObj = new MapSetPointObj(attacheOrderDetailsResp.getPlace_1_lat(), attacheOrderDetailsResp.getPlace_1_lng());
        place2MapSetPointObj = new MapSetPointObj(attacheOrderDetailsResp.getPlace_2_lat(), attacheOrderDetailsResp.getPlace_2_lng());

        display(user_image, APIConfig.BASE_IMG_URL + attacheOrderDetailsResp.getChild_avatar(), R.mipmap.default_img);
        user_name.setText(attacheOrderDetailsResp.getChild_name());
        if (attacheOrderDetailsResp.getChild_gender() == 0) {
            user_sex.setVisibility(View.GONE);
        } else {
            user_sex.setSelected(attacheOrderDetailsResp.getChild_gender() == 1);
        }
        school.setText(attacheOrderDetailsResp.getChild_school());
        grade_class.setText(attacheOrderDetailsResp.getChild_grade() + "" + attacheOrderDetailsResp.getChild_class());
        phone.setText("联系电话" + attacheOrderDetailsResp.getChild_phone());
        choice_service_content.setText(attacheOrderDetailsResp.getOrder_title());
        if ( !TextUtils.isEmpty(attacheOrderDetailsResp.getChild_school())) {
            ve_line.setVisibility(View.VISIBLE);
        } else {
            ve_line.setVisibility(View.GONE);
        }
        age.setText(attacheOrderDetailsResp.getChild_age() + "岁");
        if (TextUtils.isEmpty(attacheOrderDetailsResp.getStart_date()) || attacheOrderDetailsResp.getStart_date().equals("0")) {
            service_date.setText("");
        } else {
            service_date.setText(QLTimeUtil.getStringTime(Long.parseLong(attacheOrderDetailsResp.getStart_date()) * 1000, QLTimeUtil.TIME_MODEL_2));
        }

//        if (TextUtils.isEmpty(attacheOrderDetailsResp.getStart_time()) || attacheOrderDetailsResp.getStart_time().equals("0")) {
//            service_starttime.setText("");
//        } else {
//            service_starttime.setText(QLTimeUtil.getStringTime(Long.parseLong(attacheOrderDetailsResp.getStart_time()) * 1000, QLTimeUtil.TIME_MODEL_TIME));
//        }

        if (!isPickUpShow) {
            rl_pick_up.setVisibility(View.GONE);
        } else {
            rl_pick_up.setVisibility(View.VISIBLE);
            String       address      = "";
            UserInfoResp userInfoResp = UserContacts.getUserInfo(this);
            if (userInfoResp != null) {
                address = userInfoResp.getProvince_name() + userInfoResp.getCity_name() + userInfoResp.getDistrict_name() + userInfoResp.getUser_community() + userInfoResp.getUser_address();
            }

//            service_starttime.setText(attacheOrderDetailsResp.getStart_time());
            et_js_address_start.setText(attacheOrderDetailsResp.getPlace_1());

            if (address.equals(attacheOrderDetailsResp.getPlace_2())) {
                tv_js_address_end_prompt.setVisibility(View.VISIBLE);
            } else {
                tv_js_address_end_prompt.setVisibility(View.GONE);
            }
            et_js_address_end.setText(attacheOrderDetailsResp.getPlace_2());
        }
        if (!isNurseShow) {
            ll_nurse.setVisibility(View.GONE);
        } else {
            ll_nurse.setVisibility(View.VISIBLE);
            nurse_address.setText(attacheOrderDetailsResp.getPlace_3());
        }
        add_repast_text.setText("加餐x" + attacheOrderDetailsResp.getSnack());
        add_repast.setText("￥" + String.format("%1.2f", (attacheOrderDetailsResp.getSnack_money() * attacheOrderDetailsResp.getSnack()) / 100.0));
        if (attacheOrderDetailsResp.getTips() > 0) {
            String tipMoney = "￥" + String.format("%1.2f", attacheOrderDetailsResp.getTips() / 100.0);
            extra_tip.setText(SpannableUtils.setTextForeground(tipMoney, 0,
                    tipMoney.length(), getResources().getColor(R.color.money_color)));

        } else {
            extra_tip.setText("无");
        }
        if (attacheOrderDetailsResp.getAdditional_money() > 0) {
            ll_add_money.setVisibility(View.VISIBLE);
            add_money.setText("￥" + String.format("%1.2f", attacheOrderDetailsResp.getAdditional_money() / 100.0));
            take_picture.setVisibility(View.GONE);
            complete_service.setVisibility(View.GONE);
        } else {
            ll_add_money.setVisibility(View.GONE);
            add_money.setText("");
        }

        if (!TextUtils.isEmpty(attacheOrderDetailsResp.getOrder_note())) {
            service_demand.setText(attacheOrderDetailsResp.getOrder_note());
        } else {
            service_demand.setText("无");
        }

//        double money;
//        if (attacheOrderDetailsResp.getAdditional_money() > 0 && attacheOrderDetailsResp.getAdditional_state() == 1) {
//            money = attacheOrderDetailsResp.getMoney() + attacheOrderDetailsResp.getAdditional_money();
//        } else {
//            money = attacheOrderDetailsResp.getMoney();
//        }
        real_payment_money.setText("￥" + String.format("%1.2f", (attacheOrderDetailsResp.getMoney()) / 100.0));
//        if (rotate != null)
//            order_refresh.clearAnimation();

        pauseRefreshView();
        sv_content.setVisibility(View.VISIBLE);
    }

    private int clickStatus;

    private void setOrderStatus(AttacheOrderDetailsResp attacheOrderDetailsResp) {
        take_picture.setVisibility(View.GONE);
        ll_btn_content.setVisibility(View.GONE);
        title_status.setTextColor(getResources().getColor(R.color.app_special_style_color_normal));
        switch (attacheOrderDetailsResp.getOrder_status()) {
            case 0:
//                title.setText(R.string.order_title);
//                title_status.setVisibility(View.GONE);
//                rl_money.setVisibility(View.GONE);
                title.setText(R.string.order_title_sub);
                title_status.setText(R.string.wait_affirm);
                real_payment_text.setText(R.string.wait_payment);
                complete_service.setVisibility(View.VISIBLE);
                rl_money.setVisibility(View.VISIBLE);
                complete_service.setText(R.string.affirm_order_receiving);
                clickStatus = 1;
                break;
            case 1:
                title.setText(R.string.order_title_sub);
                title_status.setText(R.string.wait_affirm);
                real_payment_text.setText(R.string.wait_payment);
                complete_service.setVisibility(View.VISIBLE);
                rl_money.setVisibility(View.VISIBLE);
                complete_service.setText(R.string.affirm_order_receiving);
                clickStatus = 1;
                break;
            case 2:
                title.setText(R.string.order_title_sub);
                title_status.setText(R.string.wait_to_pay);
                real_payment_text.setText(R.string.wait_payment);
                complete_service.setVisibility(View.GONE);
                rl_money.setVisibility(View.VISIBLE);
                break;
            case 3:
                title.setText(R.string.order_title_sub);
                title_status.setText(R.string.already_schedule);
                real_payment_text.setText(R.string.real_payment);
                complete_service.setVisibility(View.VISIBLE);
                rl_money.setVisibility(View.VISIBLE);
                complete_service.setText(R.string.begin_service);
                clickStatus = 2;
                break;
            case 4:
                title.setText(R.string.order_title_sub);
                title_status.setText(R.string.already_underway);
                real_payment_text.setText(R.string.real_payment);
                complete_service.setVisibility(View.VISIBLE);
                complete_service.setText(R.string.order_complete_service);
                clickStatus = 3;
                take_picture.setVisibility(View.VISIBLE);
                rl_money.setVisibility(View.VISIBLE);
                break;
            case 5:
                title.setText(R.string.order_title_sub);
                title_status.setText(R.string.already_complete);
                real_payment_text.setText(R.string.real_payment);
                title_status.setTextColor(getResources().getColor(R.color.complete_order));
                complete_service.setVisibility(View.GONE);
                rl_money.setVisibility(View.VISIBLE);
                break;
            case 6:
                complete_service.setVisibility(View.GONE);
                rl_money.setVisibility(View.VISIBLE);
                title.setText(R.string.order_title_sub);
                title_status.setText(R.string.already_cancel);
                if (attacheOrderDetailsResp.getOrder_state() == 1) {
                    real_payment_text.setText(R.string.real_payment);
                } else {
                    real_payment_text.setText(R.string.wait_payment);
                }
                title_status.setTextColor(getResources().getColor(R.color.fail_order));

                break;
            case 7:
                if (attacheOrderDetailsResp.getOrder_delete_user() == 0) {
                    delete_info.setVisibility(View.VISIBLE);
                } else {
                    delete_info.setVisibility(View.GONE);
                }
                title.setText(R.string.order_title_sub);
                title_status.setText(R.string.already_be_defeated);
                title_status.setTextColor(getResources().getColor(R.color.fail_order));
                real_payment_text.setVisibility(View.INVISIBLE);
                rl_money.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.complete_service:
                switch (clickStatus) {
                    case 1:
                        new AttacheOrderAcceptHttp(this, handler, order_id, 2, true);
                        break;
                    case 2:
                        new AttacheOrderStartHttp(this, handler, order_id, 3, true);
                        break;
                    case 3:
                        ll_btn_content.setVisibility(View.VISIBLE);
                        break;
                    default:
                        complete_service.setVisibility(View.INVISIBLE);
                        break;
                }

                break;
            case R.id.additional_expense:
                showDialog();
                break;
            case R.id.completed_immediately:
                new AttacheOrderCompleteHttp(this, handler, order_id, 4, true);
                break;
            case R.id.ll_js_address_start:
                intent = new Intent(this, MapSetPointActivity.class);
                intent.putExtra(IntentFinal.INTENT_MAP_SET_POINT, place1MapSetPointObj);
                intent.putExtra(IntentFinal.INTENT_ORDER_MAP_SET_POINT, true);
                startActivity(intent);
                break;
            case R.id.ll_js_address_end:
                intent = new Intent(this, MapSetPointActivity.class);
                intent.putExtra(IntentFinal.INTENT_MAP_SET_POINT, place2MapSetPointObj);
                intent.putExtra(IntentFinal.INTENT_ORDER_MAP_SET_POINT, true);
                startActivity(intent);
                break;
            case R.id.take_picture:
                intent = new Intent(this, LookOverServiceActivity.class);
                intent.putExtra(IntentFinal.INTENT_ATTACHE_ORDER_DETAILS_TO_SERVICE, order_id);
                intent.putExtra(IntentFinal.INTENT_ATTACHE_OR_USER_ORDER_DETAILS_TO_SERVICE, true);
                startActivity(intent);
                break;
            case R.id.ll_children_info:
                intent = new Intent(this, AttacheOrderChildrenInfoActivity.class);
                intent.putExtra(IntentFinal.INTENT_ATTACHE_ORDER_DETAILS_TO_CHILDREN_INFO, attacheOrderDetailsResp.getChild_id());
                startActivity(intent);
                break;
            case R.id.rl_phone:
                String phone = attacheOrderDetailsResp.getChild_phone();
                if (TextUtils.isEmpty(phone)) {
                    return;
                } else {
                    intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + phone);
                    intent.setData(data);
                    startActivity(intent);

                }
                break;
            case R.id.order_refresh:
                orderRefresh();
                break;
        }

    }

    @Override
    public void failureOperation(Object obj) {
        super.failureOperation(obj);
        pauseRefreshView();
    }

    private void orderRefresh() {
//        rotate = AnimationUtils.loadAnimation(this, R.anim.order_refresh_anim);
//        LinearInterpolator li = new LinearInterpolator();
//        rotate.setInterpolator(li);
//        order_refresh.startAnimation(rotate);
//        rotate.setFillAfter(!rotate.getFillAfter());
        if (animator != null) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (animator.isStarted()) animator.resume();
            else
                animator.start();
        }

        sv_content.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(order_id)) {
            getData();
        }
//        if (rotate != null)
//            rotate.cancel();
    }

    private ObjectAnimator animator;

    private void initRefreshView() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(order_refresh, "rotation", 0f, 360.0f);
            animator.setDuration(1000);
            animator.setInterpolator(new LinearInterpolator());//不停顿
            animator.setRepeatCount(-1);//设置动画重复次数
            animator.setRepeatMode(ValueAnimator.RESTART);//动画重复模式
        }
    }

    private void pauseRefreshView() {
        if (animator != null) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (animator.isStarted())
                animator.pause();
        }
    }

    OrderSuperadditionCostDialog dialog;

    private void showDialog() {
        if (dialog == null) {
            dialog = new OrderSuperadditionCostDialog();
            dialog.setData(this, handler, order_id);
        }
        dialog.show(getSupportFragmentManager(), "AttacheOrderDetailsActivity");
    }
}
