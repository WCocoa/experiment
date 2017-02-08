package com.qiantang.neighbourmother.ui.home;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.data.BaseHttp;
import com.qiantang.neighbourmother.business.data.OrderCancerHttp;
import com.qiantang.neighbourmother.business.data.OrderCreatHttp;
import com.qiantang.neighbourmother.business.data.OrderDetailHttp;
import com.qiantang.neighbourmother.business.data.OrderScoreHttp;
import com.qiantang.neighbourmother.business.data.OrderSureHttp;
import com.qiantang.neighbourmother.business.request.AttacheOrderIdReq;
import com.qiantang.neighbourmother.business.request.OrderCreateReq;
import com.qiantang.neighbourmother.business.request.OrderScoreReq;
import com.qiantang.neighbourmother.business.response.AttacheDetailResp;
import com.qiantang.neighbourmother.business.response.OrderDetailResp;
import com.qiantang.neighbourmother.business.response.OrderScoreResp;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.business.response.UserOrderListResp;
import com.qiantang.neighbourmother.logic.CountDownTime;
import com.qiantang.neighbourmother.logic.DownOrderLogic;
import com.qiantang.neighbourmother.logic.PlusMeal;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.model.ChoiceServReObj;
import com.qiantang.neighbourmother.model.MapSetPointObj;
import com.qiantang.neighbourmother.model.ToAttacheDetailObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.activity.DataPickerActivity;
import com.qiantang.neighbourmother.ui.activity.MapSetPointActivity;
import com.qiantang.neighbourmother.ui.activity.TimePickerActivity;
import com.qiantang.neighbourmother.ui.center.ChildrenInfoEditActivity;
import com.qiantang.neighbourmother.ui.dialog.AddMoneyDialog;
import com.qiantang.neighbourmother.ui.dialog.CancerOrderDialog;
import com.qiantang.neighbourmother.ui.dialog.DownOrderSureDialog;
import com.qiantang.neighbourmother.ui.dialog.OrderChildInfoDialog;
import com.qiantang.neighbourmother.ui.dialog.OrderScoreDialog;
import com.qiantang.neighbourmother.ui.order.user.LookOverServiceActivity;
import com.qiantang.neighbourmother.ui.order.user.OrderFragment;
import com.qiantang.neighbourmother.ui.order.user.OrderPayActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLTimeUtil;
import com.qiantang.neighbourmother.util.SpannableUtils;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.CircleImageView;
import com.qiantang.neighbourmother.widget.picker.DateTimePicker;

import java.util.Calendar;

/**
 * ClassName:服务介绍
 * author: Cocoa
 * date: 2016/9/18.
 */
public class DownOrderActivity extends BaseActivity implements View.OnClickListener, OrderScoreDialog.SureScoreListener
        , AddMoneyDialog.SureAddMoneyListener, DownOrderSureDialog.DownOrderSureListener
        , CancerOrderDialog.CancerOrderListener {
    public static final int WHAT_CREATE_ORDER   = 1;
    public static final int WHAT_SURE_ORDER     = 2;
    public static final int WHAT_GET_ORDERDETAL = 3;
    public static final int WHAT_ORDER_CANCER   = 4;
    public static final int WHAT_ORDER_COMM     = 5;
    public static final int WHAT_TIME           = 6;//倒计时

    public static final int RESULT_SERVICE               = 3;
    public static final int RESULT_CHOICE_DATA           = 4;
    public static final int RESULT_CHOICE_START_TIME     = 5;
    public static final int RESULT_MAP_CHOICE_POINT_FORM = 6;
    public static final int RESULT_MAP_CHOICE_POINT_TO   = 7;
    public static final int RESULT_PAY_RESULT_SUCCESS    = 8;
    public static final int RESULT_ATTACHER              = 9;
    public static final int RESULT_EDIT_CHILD_INFO       = 10;
    DownOrderSureDialog downOrderSureDialog;
    private boolean         isRefreshOrder;
    private TextView        title;
    private ImageView       refresh;
    private ImageView       back;
    private RelativeLayout  rela_param;
    private RelativeLayout  person_rela;//专员信息大布局
    private LinearLayout    ll_dorder_person_info;
    private CircleImageView person_headimg;//专员头像
    private TextView        person_name;//专员姓名
    private ImageView       attache_sex;
    private TextView attache_age;
    private TextView attache_post;
//    private TextView        person_age;//专员年龄
    private TextView        person_service_type;//服务类型
    private TextView        person_address;//专员地址
    private RelativeLayout  rela_choose_service;//选择服务
    private TextView        tv_choose_service;//选择服务
    private RelativeLayout  rela_service_date;//服务日期
    private TextView        tv_service_date;//服务日期
//    private RelativeLayout  rela_start_time;//开始时间
//    private TextView        tv_start_time;//开始时间
private RelativeLayout        rela_service_address;//服务地点
private EditText        ed_service_address;//服务地点
    private RelativeLayout        rela_service_require;
    private EditText        tv_service_require;
    private TextView        money_symbol;//额外消费符号

    private RelativeLayout rl_js_address;
    private LinearLayout   ll_nurse;
    private EditText       et_js_address_start;//接送起点
    private LinearLayout   ll_js_address_start;//接送起点
    private TextView       tv_js_address_start;//接送起点
    private EditText       et_js_address_end;//接送终点
    private LinearLayout   ll_js_address_end;//接送终点
    private TextView       tv_js_address_end;//接送终点k
    private ImageView      tv_js_address_end_prompt;//专员住址提示
    private EditText       et_nurse_address;//看护地点
    private RelativeLayout rela_cater_service;//餐饮服务
    private TextView       tv_cater_hint;//餐饮服务提示
    private TextView       tv_pcater_price;//加餐饮价格
    private TextView       pcater_subtract;//加餐减
    private TextView       pcater_plus;//加餐加
    private TextView       pcater_num;//加餐数量
    private TextView       delete_info;//删除帖子提示信息
    private EditText       extra_cast;//额外花费
    private RelativeLayout rela_platform_walfare;//平台福利
    private ScrollView     scrollView;
    private RelativeLayout rela_bottom;
    private LinearLayout   ll_order_valid_time;
    private TextView       confirmMoney;//确认金额
    /*tag0不做任何操作1创建订单2确认订单3支付订单4取消订单5打分评价6查看服务**/
    private TextView       confirmBtn;//确认按钮
    private TextView       order_valid_time;//订单倒计时

    private RelativeLayout rl_add_money;
    private TextView       add_money;
    private ImageView      icon_right;//顶部右边箭头
//    private ImageView      time_icon_right;//选择时间右边箭头

    private ImageView service_icon_right;//选择服务时间右边箭头
    private ImageView choice_service_icon_right;//选择服务右边箭头

    private CountDownTime         countDownTime;
    private AttacheAddressWatcher attacheAddressWatcher;
    private String                address;
    private OrderScoreDialog      orderScoreDialog;
    private AddMoneyDialog        addMoneyDialog;
    private int                   serviceType;
    private ObjectAnimator        animator;

    private DownOrderLogic downOrderLogic;

    @Override
    public int getContentView() {
        return R.layout.activity_downorder;
    }

    @Override
    public void initView() {
        refresh = (ImageView) findViewById(R.id.refresh);
        back = (ImageView) findViewById(R.id.back);
        rl_js_address = (RelativeLayout) findViewById(R.id.rl_js_address);
        icon_right = (ImageView) findViewById(R.id.icon_right);
//        time_icon_right = (ImageView) findViewById(R.id.time_icon_right);
        service_icon_right = (ImageView) findViewById(R.id.service_icon_right);
        choice_service_icon_right = (ImageView) findViewById(R.id.choice_service_icon_right);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        rela_param = (RelativeLayout) findViewById(R.id.rela_param);
        rela_bottom = (RelativeLayout) findViewById(R.id.rela_bottom);
        person_rela = (RelativeLayout) findViewById(R.id.person_rela);
        ll_dorder_person_info = (LinearLayout) findViewById(R.id.ll_dorder_person_info);
        attache_sex = (ImageView) findViewById(R.id.attache_sex);
        person_headimg = (CircleImageView) findViewById(R.id.person_headimg);
        title = (TextView) findViewById(R.id.title);
        person_name = (TextView) findViewById(R.id.person_name);
        attache_age = (TextView) findViewById(R.id.attache_age);
        attache_post = (TextView) findViewById(R.id.attache_post);

        person_service_type = (TextView) findViewById(R.id.person_service_type);
        person_address = (TextView) findViewById(R.id.person_address);
        rela_choose_service = (RelativeLayout) findViewById(R.id.rela_choose_service);
        tv_choose_service = (TextView) findViewById(R.id.tv_choose_service);
        rela_service_date = (RelativeLayout) findViewById(R.id.rela_service_date);
        tv_service_date = (TextView) findViewById(R.id.tv_service_date);
        rela_service_address = (RelativeLayout) findViewById(R.id.rela_service_address);
        ed_service_address = (EditText) findViewById(R.id.ed_service_address);
        rela_service_require = (RelativeLayout) findViewById(R.id.rela_service_require);
        tv_service_require = (EditText) findViewById(R.id.tv_service_require);
        money_symbol = (TextView) findViewById(R.id.money_symbol);

//        rela_start_time = (RelativeLayout) findViewById(R.id.rela_start_time);
//        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        et_js_address_start = (EditText) findViewById(R.id.et_js_address_start);
        ll_js_address_start = (LinearLayout) findViewById(R.id.ll_js_address_start);
        tv_js_address_start = (TextView) findViewById(R.id.tv_js_address_start);
        et_js_address_end = (EditText) findViewById(R.id.et_js_address_end);
        ll_js_address_end = (LinearLayout) findViewById(R.id.ll_js_address_end);
        tv_js_address_end = (TextView) findViewById(R.id.tv_js_address_end);
        tv_js_address_end_prompt = (ImageView) findViewById(R.id.tv_js_address_end_prompt);
        et_nurse_address = (EditText) findViewById(R.id.et_nurse_address);
        rela_cater_service = (RelativeLayout) findViewById(R.id.rela_cater_service);
        tv_cater_hint = (TextView) findViewById(R.id.tv_cater_hint);
        delete_info = (TextView) findViewById(R.id.delete_info);
        ll_nurse = (LinearLayout) findViewById(R.id.ll_nurse);

        tv_pcater_price = (TextView) findViewById(R.id.tv_pcater_price);
        pcater_subtract = (TextView) findViewById(R.id.pcater_subtract);
        pcater_plus = (TextView) findViewById(R.id.pcater_plus);
        pcater_num = (TextView) findViewById(R.id.pcater_num);
        extra_cast = (EditText) findViewById(R.id.extra_cast);
        rela_platform_walfare = (RelativeLayout) findViewById(R.id.rela_platform_walfare);
        confirmMoney = (TextView) findViewById(R.id.confirmMoney);
        confirmBtn = (TextView) findViewById(R.id.confirmBtn);
        order_valid_time = (TextView) findViewById(R.id.order_valid_time);
        ll_order_valid_time = (LinearLayout) findViewById(R.id.ll_order_valid_time);
        rl_add_money = (RelativeLayout) findViewById(R.id.rl_add_money);
        add_money = (TextView) findViewById(R.id.add_money);
/*tag0不做任何操作1创建订单2确认订单3支付订单4取消订单5打分评价6查看服务**/
        confirmBtn.setTag(0);
        //1选择专员2查看专员
        person_rela.setTag(1);
//0编辑定位1查看定位
        ll_js_address_start.setTag(0);
        ll_js_address_end.setTag(0);
        delete_info.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        String moneyText = getResources().getString(R.string.downorder_oay_money_suffix) + getResources().getString(R.string.app_money_symbol);
//                confirmMoney.setText(moneyText + new java.text.DecimalFormat("0.00").format(temp));
        String waitPayText = moneyText + new java.text.DecimalFormat("0.00").format(0);
//                AppLog.D("waitPayText:" + waitPayText);
        confirmMoney.setText(SpannableUtils.setTextForeground(waitPayText, moneyText.length()-1, waitPayText.length(), getResources().getColor(R.color.money_color)));

        downOrderLogic = new DownOrderLogic(this);
        initRefreshView();
        attacheAddressWatcher = new AttacheAddressWatcher();
        et_js_address_end.addTextChangedListener(attacheAddressWatcher);
        serviceType = getIntent().getIntExtra(IntentFinal.INTENT_TO_DOWN_ORDER_SERVICE, 0);

        PlusMeal plusMeal = new PlusMeal(this);
        plusMeal.setTv_pcater_price(tv_pcater_price);
        plusMeal.setPcater_subtract(pcater_subtract);
        plusMeal.setPcater_plus(pcater_plus);
        plusMeal.setPcater_num(pcater_num);
        plusMeal.setConfirmMoney(confirmMoney);
        plusMeal.setExtra_cast(extra_cast);
        plusMeal.setMoney_symbol(money_symbol);
        downOrderLogic.setPlusMeal(plusMeal);
        downOrderLogic.setToThisType(getIntent().getIntExtra(IntentFinal.INTENT_TO_DOWN_ORDER, 0));

//        进入下单页面类型0服务介绍页面进入1专员页面进入2订单列表页面进入
        switch (downOrderLogic.getToThisType()) {
            case 0:
                upData(-2);
                break;
            case 1:
                downOrderLogic.setAttacheDetailResp((AttacheDetailResp) getIntent().getSerializableExtra(IntentFinal.INTENT_ATTACHE_DETAIL_OBJ));
                setAttacheInfo(downOrderLogic.getAttacheDetailResp());
                upData(-1);
                break;
            case 2:
                downOrderLogic.setUserOrderListResp((UserOrderListResp) getIntent().getSerializableExtra(IntentFinal.INTENT_ORDER_ITEM_OBJ));
                new OrderDetailHttp(this, handler, downOrderLogic.getUserOrderListResp().getOrder_id(), WHAT_GET_ORDERDETAL);
                break;
        }
    }

    @Override
    public void initEvent() {

        refresh.setOnClickListener(this);
        back.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        person_rela.setOnClickListener(this);
        rela_choose_service.setOnClickListener(this);
        rela_service_date.setOnClickListener(this);
//        rela_start_time.setOnClickListener(this);
        ll_js_address_start.setOnClickListener(this);
        ll_js_address_end.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case BaseHttp.CHILD_INFO_INVLID:
               showEditChildDialog();
                break;
            case WHAT_CREATE_ORDER:
                downOrderLogic.setOrderDetailResp((OrderDetailResp) msg.obj, false);
                confirmBtn.setTag(2);
                upData(0);
                break;
            case WHAT_SURE_ORDER:
                upData(1);
                break;
            case WHAT_GET_ORDERDETAL:

                setData((OrderDetailResp) msg.obj);
                upData(downOrderLogic.getOrderDetailResp().getOrder().getOrder_status());

                if (downOrderLogic.getOrderDetailResp().getOrder().getAdditional_money() > 0 && downOrderLogic.getOrderDetailResp().getOrder().getAdditional_state() == 0) {
                    initAddMoneyDialog();
                }

                pauseRefreshView();
                break;
            case WHAT_ORDER_CANCER:
                upData(6);
                isRefreshOrder = true;
                break;
            case WHAT_ORDER_COMM:
                OrderScoreResp orderScoreResp = (OrderScoreResp) msg.obj;
                downOrderLogic.getOrderDetailResp().getOrder().setOrder_star(orderScoreResp.getOrder_star());
                upData(5);
                isRefreshOrder = true;
                break;
            case WHAT_TIME:
                setOrderTime((String) msg.obj);
                break;
        }
        closeProgressDialog();
    }

    //    倒计时
    private void setOrderTime(String stime) {
        if (TextUtils.isEmpty(stime)) {
            ll_order_valid_time.setVisibility(View.GONE);
            upData(7);
            if (countDownTime != null)
                countDownTime.onDestory();
        } else {
            order_valid_time.setText(stime);
            ll_order_valid_time.setVisibility(View.VISIBLE);
        }
    }

    private void setData(OrderDetailResp orderDetailResp) {
        downOrderLogic.setOrderDetailResp(orderDetailResp, true);
        setAttacheInfo(downOrderLogic.getAttacheDetailResp());

        //选择服务
        tv_choose_service.setText(downOrderLogic.getChoiceServReObj().getNameArray());

        tv_service_date.setText(downOrderLogic.getChoiceData() == null ? "" : downOrderLogic.getChoiceData());
//        tv_start_time.setText(downOrderLogic.getChoiceTime() == null ? "" : downOrderLogic.getChoiceTime());

//        if (downOrderLogic.getOrderDetailResp().getOrder().getPlace_1_lat() > 0 && downOrderLogic.getOrderDetailResp().getOrder().getPlace_1_lng() > 0) {
//            downOrderLogic.setFormMapp(new MapSetPointObj(downOrderLogic.getOrderDetailResp().getOrder().getPlace_1_lat(), downOrderLogic.getOrderDetailResp().getOrder().getPlace_1_lng()));
//            tv_js_address_start.setText(R.string.downorder_replace_loc);
//        }
//
//        et_js_address_start.setText(orderDetailResp.getOrder().getPlace_1());
//        if (downOrderLogic.getOrderDetailResp().getOrder().getPlace_2_lat() > 0 && downOrderLogic.getOrderDetailResp().getOrder().getPlace_2_lng() > 0) {
//            downOrderLogic.setToMapp(new MapSetPointObj(downOrderLogic.getOrderDetailResp().getOrder().getPlace_2_lat(), downOrderLogic.getOrderDetailResp().getOrder().getPlace_2_lng()));
//            tv_js_address_end.setText(R.string.downorder_replace_loc);
//        }

        et_js_address_end.setText(downOrderLogic.getOrderDetailResp().getOrder().getPlace_2());
        ed_service_address.setText(downOrderLogic.getOrderDetailResp().getOrder().getPlace_3());

//        AppLog.D("totalMoney3:" + downOrderLogic.getPlusMeal().getTotalMoney());
        extra_cast.setText(String.valueOf(orderDetailResp.getOrder().getTips() / 100));
//        et_nurse_address.setText(orderDetailResp.getOrder().getPlace_3());

//        AppLog.D("totalMoney4:" + downOrderLogic.getPlusMeal().getTotalMoney());

        addMoneyShow(orderDetailResp.getOrder().getAdditional_money() > 0
                     && orderDetailResp.getOrder().getAdditional_state() == 1, ((float) orderDetailResp.getOrder().getAdditional_money()) / 100);
        person_address.setVisibility(View.VISIBLE);
        tv_service_require.setText(orderDetailResp.getOrder().getOrder_note());
    }

    /**
     * 追加金额显示逻辑
     */
    private void addMoneyShow(boolean isShow, float money) {
        if (isShow) {
            rl_add_money.setVisibility(View.VISIBLE);
            add_money.setText("￥" + String.valueOf(money));
        } else {
            rl_add_money.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.back:
                finishAc(isRefreshOrder);
                break;
            case R.id.person_rela:
//                1选择专员2查看专员
                switch ((int) person_rela.getTag()) {
                    case 1:
                        intent = new Intent(this, AttacheListActivity.class);

                        intent.putExtra(IntentFinal.INTENT_ATTACHE_DETAILS_ARG, downOrderLogic.getOrderDetailResp() != null &&
                                                                                downOrderLogic.getOrderDetailResp().getOrder().getOrder_status() > 1);
                        startActivityForResult(intent, 1);
                        break;
                    case 2:
                        intent = new Intent(DownOrderActivity.this, AttacheDetailsActivity.class);
                        intent.putExtra(IntentFinal.INTENT_ATTACHE_OBJ, new ToAttacheDetailObj(downOrderLogic.getOrderDetailResp().getOrder().getServant_id(), 0));
                        intent.putExtra(IntentFinal.INTENT_ATTACHE_DETAILS_ARG, downOrderLogic.getOrderDetailResp() != null
                                                                                && downOrderLogic.getOrderDetailResp().getOrder().getOrder_status() > 1);
                        startActivity(intent);
                        break;
                }

                break;
            case R.id.rela_choose_service:
                intent = new Intent(this, ChoiceServiceActivity.class);
                AppLog.D("serviceType:" + serviceType);
                intent.putExtra(IntentFinal.INTENT_DOWN_ORDER_SERVICE_TYPE, serviceType);
                startActivityForResult(intent, 1);

                break;
            case R.id.rela_service_date:
                onYearMonthDayTimePicker(view);
//                startActivityForResult(new Intent(this, DataPickerActivity.class), 1);
                break;
//            case R.id.rela_start_time:
//                startActivityForResult(new Intent(this, TimePickerActivity.class), 1);
//                break;
            case R.id.ll_js_address_start:
                intent = new Intent(this, MapSetPointActivity.class);
                intent.putExtra(IntentFinal.INTENT_MAP_CHOICE_POINT_RESULT, RESULT_MAP_CHOICE_POINT_FORM);
                intent.putExtra(IntentFinal.INTENT_ORDER_MAP_SET_POINT, ((int) ll_js_address_start.getTag()) == 0 ? false : true);
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_js_address_end:
                intent = new Intent(this, MapSetPointActivity.class);
                intent.putExtra(IntentFinal.INTENT_MAP_CHOICE_POINT_RESULT, RESULT_MAP_CHOICE_POINT_TO);
                intent.putExtra(IntentFinal.INTENT_ORDER_MAP_SET_POINT, ((int) ll_js_address_start.getTag()) == 0 ? false : true);
                startActivityForResult(intent, 1);
                break;
            case R.id.confirmBtn:

                onclickConfirm();
                break;
            case R.id.refresh:
                refreshData();
                break;
        }
    }

    private void onclickConfirm() {
        Intent intent = null;
        /*tag0不做任何操作1创建订单2确认订单3支付订单4取消订单5打分评价6查看服务**/
        switch ((int) confirmBtn.getTag()) {
            case 1:
                OrderCreateReq orderCreateReq = downOrderLogic.getOrderCreateReq(et_js_address_start.getText().toString().trim()
                        , et_js_address_end.getText().toString().trim()
                        , ed_service_address.getText().toString().trim()
                        , extra_cast.getText().toString().trim(),tv_service_require.getText().toString().trim());

                if (orderCreateReq != null) {
                    if (downOrderLogic.judgeAttacheAndService()) {
                        new OrderCreatHttp(this, handler, orderCreateReq, WHAT_CREATE_ORDER);
                    } else {
                        initSubmitDialog();
                    }
                }
                break;
            case 2:
                OrderCreateReq orderCreateReq1 = downOrderLogic.getOrderCreateReq(et_js_address_start.getText().toString().trim()
                        , et_js_address_end.getText().toString().trim()
                        , ed_service_address.getText().toString().trim()
                        , extra_cast.getText().toString().trim(),tv_service_require.getText().toString().trim());

                if (orderCreateReq1 != null) {
                    orderCreateReq1.setOrder_id(downOrderLogic.getOrderDetailResp().getOrder().getOrder_id());
                    AppLog.D("downOrderLogic.getOrderDetailResp().getOrder_id():" + downOrderLogic.getOrderDetailResp().getOrder().getOrder_id());
                    if (downOrderLogic.judgeAttacheAndService()) {
                        new OrderSureHttp(this, handler, orderCreateReq1, WHAT_SURE_ORDER);
                    } else {
                        initSubmitDialog();
                    }
                }
                break;
            case 3:
                if (animator != null) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (animator.isStarted())
                        animator.pause();
                }

                intent = new Intent(this, OrderPayActivity.class);
                intent.putExtra(IntentFinal.INTENT_ORDER_DETAIL_OBJ, downOrderLogic.getOrderDetailResp());
                intent.putExtra(IntentFinal.INTENT_PAY_TYPE, 1);
                startActivityForResult(intent, 1);

                break;
            case 4:

                initCancerOrderDialog();
                break;

            case 5:
                initOrderScoreDialog();
                break;
            case 6:
                intent = new Intent(this, LookOverServiceActivity.class);
                String orderId = null;
                if (downOrderLogic.getUserOrderListResp() != null)
                    orderId = downOrderLogic.getUserOrderListResp().getOrder_id();
                else
                    orderId = downOrderLogic.getOrderDetailResp().getOrder().getOrder_id();
                intent.putExtra(IntentFinal.INTENT_ATTACHE_ORDER_DETAILS_TO_SERVICE, orderId);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void failureOperation(Object obj) {
        super.failureOperation(obj);
        pauseRefreshView();
    }

    private void initOrderScoreDialog() {
        if (orderScoreDialog == null) {
            orderScoreDialog = new OrderScoreDialog();
            orderScoreDialog.setOnSureScoreListener(this);
        }
        orderScoreDialog.show(getSupportFragmentManager(), "orderScoreDialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        person_address.setVisibility(View.VISIBLE);
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_ATTACHER:
                downOrderLogic.setAttacheDetailResp((AttacheDetailResp) data.getSerializableExtra(IntentFinal.INTENT_ATTACHE_DETAIL_OBJ));
                setAttacheInfo(downOrderLogic.getAttacheDetailResp());
                addressChange(false);

                break;
            case RESULT_SERVICE:
                downOrderLogic.setChoiceServReObj((ChoiceServReObj) data.getParcelableExtra(IntentFinal.INTENT_CHOICE_SERVICE_RESULT_OBJ));
                tv_choose_service.setText(downOrderLogic.getChoiceServReObj().getNameArray());
//                downOrderLogic.getPlusMeal().setServMoney(downOrderLogic.getChoiceServReObj().getMoney());
                addressChange(false);

                break;
            case RESULT_CHOICE_DATA:
                downOrderLogic.setChoiceData(QLTimeUtil.getStringTime(data.getLongExtra(IntentFinal.INTENT_DATA_STRING, 0), QLTimeUtil.TIME_MODEL));
                tv_service_date.setText(downOrderLogic.getChoiceData());
                break;
            case RESULT_CHOICE_START_TIME:
                downOrderLogic.setChoiceTime(data.getStringExtra(IntentFinal.INTENT_TIME_STRING));
//                tv_start_time.setText(downOrderLogic.getChoiceTime());
                break;
            case RESULT_MAP_CHOICE_POINT_FORM:
                downOrderLogic.setFormMapp((MapSetPointObj) data.getSerializableExtra(IntentFinal.INTENT_MAP_SET_POINT));
                tv_js_address_start.setText(R.string.downorder_replace_loc);
                break;

            case RESULT_MAP_CHOICE_POINT_TO:
                downOrderLogic.setToMapp((MapSetPointObj) data.getSerializableExtra(IntentFinal.INTENT_MAP_SET_POINT));
                tv_js_address_end.setText(R.string.downorder_replace_loc);
                break;

            case RESULT_PAY_RESULT_SUCCESS:
                AppLog.D("RESULT_PAY_RESULT_SUCCESS");
                isRefreshOrder = true;
                refreshData();
                break;
            case RESULT_EDIT_CHILD_INFO:
//                ToastUtils.toastLong(this,R.string.downorder_edit_child_info_success);
                Snackbar.make(rela_param,R.string.downorder_edit_child_info_success,Snackbar.LENGTH_LONG).show();
                break;

        }
    }

    //    是否为更新
    private void addressChange(boolean isUpdata) {
        if (downOrderLogic.getAttacheDetailResp() != null) {
            address = downOrderLogic.getAttacheDetailResp().getProvince_name()
                      + downOrderLogic.getAttacheDetailResp().getCity_name()
                      + downOrderLogic.getAttacheDetailResp().getDistrict_name()
                      + downOrderLogic.getAttacheDetailResp().getUser_community()
                      + downOrderLogic.getAttacheDetailResp().getUser_address();
            tv_js_address_end_prompt.setVisibility(address.equals(et_js_address_end.getText().toString().trim()) ? View.VISIBLE : View.GONE);
        }

        if (!isUpdata && downOrderLogic.getChoiceServReObj() != null && downOrderLogic.getAttacheDetailResp() != null) {

            et_nurse_address.setText(downOrderLogic.getChoiceServReObj().isExistNurse() ? address : "");
            ed_service_address.setText(downOrderLogic.getChoiceServReObj().isExistNurse() ? address : "");
            AppLog.D("addressChange_choiceServReObj.isExistM():" + downOrderLogic.getChoiceServReObj().isExistM());
            AppLog.D("addressChange_choiceServReObj.isExistN():" + downOrderLogic.getChoiceServReObj().isExistN());
            AppLog.D("addressChange_choiceServReObj.isExistNurse():" + downOrderLogic.getChoiceServReObj().isExistNurse());
            AppLog.D("address:" + address);

            if (downOrderLogic.getChoiceServReObj().isExistM()) {

                UserInfoResp userInfoResp = UserContacts.getUserInfo(this);
                String       ud           = userInfoResp.getProvince_name() + userInfoResp.getCity_name() + userInfoResp.getDistrict_name() + userInfoResp.getUser_community() + userInfoResp.getUser_address();
                AppLog.D("ud:" + ud);
                et_js_address_start.setText(ud);
                et_js_address_end.setText("");
            } else {

                if (downOrderLogic.getChoiceServReObj().isExistN()) {
                    et_js_address_start.setText("");
                    et_js_address_end.setText(address);
                } else {
                    et_js_address_start.setText("");
                    et_js_address_end.setText("");
                }
            }
        }

        if (downOrderLogic.getAttacheDetailResp() != null && downOrderLogic.getChoiceServReObj() != null) {
//            ll_nurse.setVisibility(downOrderLogic.getChoiceServReObj().isExistNurse() ? View.VISIBLE : View.GONE);
            rela_service_address.setVisibility(downOrderLogic.getChoiceServReObj().isExistNurse() ? View.VISIBLE : View.GONE);

//            rl_js_address.setVisibility((downOrderLogic.getChoiceServReObj().isExistM() || downOrderLogic.getChoiceServReObj().isExistN()) ? View.VISIBLE : View.GONE);
        } else {
//            ll_nurse.setVisibility(View.GONE);
            rela_service_address.setVisibility(View.GONE);
//            rl_js_address.setVisibility(View.GONE);
        }
    }

    private void setAttacheInfo(AttacheDetailResp attacheDetailResp) {
        person_address.setVisibility(View.VISIBLE);
        display(person_headimg, APIConfig.BASE_IMG_URL + attacheDetailResp.getUser_avatar(), R.mipmap.default_img);

        person_address.setText(attacheDetailResp.getProvince_name() + attacheDetailResp.getCity_name() + attacheDetailResp.getDistrict_name() + attacheDetailResp.getUser_community());
        changeName(downOrderLogic.getOrderDetailResp() != null
                   && downOrderLogic.getOrderDetailResp().getOrder().getOrder_status() > 1);

        attache_sex.setSelected(attacheDetailResp.getUser_gender() == 2);

        attache_age.setText(attacheDetailResp.getUser_age() + "岁");
        attache_post.setText(attacheDetailResp.getIndustry());

        StringBuilder serType = new StringBuilder();
        for (String str : attacheDetailResp.getServant_type_array_string()) {
            serType.append(str + " ");
        }
        person_service_type.setText(serType);
        ll_dorder_person_info.setVisibility(View.VISIBLE);

        downOrderLogic.getPlusMeal().setSnack(attacheDetailResp.getSnack());

//        AppLog.D("totalMoney1:" + downOrderLogic.getPlusMeal().getTotalMoney());
    }

    private void upData(int type) {
        addressChange(true);
        refresh.setVisibility(View.VISIBLE);
        icon_right.setVisibility(View.VISIBLE);
        ll_order_valid_time.setVisibility(View.GONE);

//        confirmBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_btn_backgroud));
        if (type != 2) {
            if (countDownTime != null) countDownTime.onDestory();
        }


        //	'-2从服务介绍页过来创建订单-1从专员页面过来创建订单0 待确认，1已确认待接单，2已接单待支付，3已支付等待服务开始　4开始服务　5服务完成　6订单取消 7订单失效',
        switch (type) {
            case -2:
                title.setText(R.string.downorder_title);
                confirmBtn.setTag(1);
                confirmBtn.setVisibility(View.VISIBLE);
//                confirmBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_complete_service_backgroud));
                confirmBtn.setText(R.string.downorder_btn_create);
                refresh.setVisibility(View.INVISIBLE);
                editEnable(true, true, true);
                rela_bottom.setVisibility(View.VISIBLE);
                break;
            case -1:
                title.setText(R.string.downorder_title);
                refresh.setVisibility(View.INVISIBLE);
                confirmBtn.setTag(1);
                confirmBtn.setVisibility(View.VISIBLE);
//                confirmBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_complete_service_backgroud));
                confirmBtn.setText(R.string.downorder_title);
                editEnable(true, true, true);
                rela_bottom.setVisibility(View.VISIBLE);
                break;
            case 0:
                person_rela.setTag(1);
                confirmBtn.setTag(2);
                confirmBtn.setText(R.string.downorder_btn_confirm);
                confirmBtn.setVisibility(View.VISIBLE);
//                confirmBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_complete_service_backgroud));
                title.setText(SpannableUtils.setTextForeground(getString(R.string.downorder_status_wait_confirm), 3, 6, getResources().getColor(R.color.app_special_style_color_normal)));
                editEnable(true, true, true);
                rela_bottom.setVisibility(View.VISIBLE);

                break;
            case 1:
                person_rela.setTag(2);
//                confirmBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_complete_service_backgroud));
                confirmBtn.setVisibility(View.INVISIBLE);

                editEnable(false, downOrderLogic.getFormMapp() != null, downOrderLogic.getToMapp() != null);
                icon_right.setVisibility(View.INVISIBLE);
//                time_icon_right.setVisibility(View.INVISIBLE);
                rela_bottom.setVisibility(View.VISIBLE);
                choice_service_icon_right.setVisibility(View.INVISIBLE);
                service_icon_right.setVisibility(View.INVISIBLE);
                title.setText(SpannableUtils.setTextForeground(getString(R.string.downorder_status_wait_accept), 3, 6, getResources().getColor(R.color.app_special_style_color_normal)));

                break;
            case 2:
                person_rela.setTag(2);
                title.setText(SpannableUtils.setTextForeground(getString(R.string.downorder_status_wait_pay), 3, 6, getResources().getColor(R.color.app_special_style_color_normal)));
                icon_right.setVisibility(View.VISIBLE);
                confirmBtn.setTag(3);
                confirmBtn.setText(R.string.downorder_btn_im_pay);
                confirmBtn.setVisibility(View.VISIBLE);
//                time_icon_right.setVisibility(View.INVISIBLE);
                service_icon_right.setVisibility(View.INVISIBLE);
                choice_service_icon_right.setVisibility(View.INVISIBLE);
                rela_bottom.setVisibility(View.VISIBLE);

                if (downOrderLogic.getOrderDetailResp().getOrder().getCountdown_time() > 0) {
                    if (countDownTime == null) {
                        countDownTime = new CountDownTime(handler);
                    }
                    countDownTime.start(downOrderLogic.getOrderDetailResp().getOrder().getCountdown_time());
                } else {
                    ll_order_valid_time.setVisibility(View.GONE);
                }
                editEnable(false, downOrderLogic.getFormMapp() != null, downOrderLogic.getToMapp() != null);
                break;
            case 3:
                person_rela.setTag(2);
                title.setText(SpannableUtils.setTextForeground(getString(R.string.downorder_status_payed), 3, 6, getResources().getColor(R.color.app_special_style_color_normal)));
                confirmBtn.setTag(4);
                confirmBtn.setText(R.string.downorder_btn_cancer);
                confirmBtn.setVisibility(View.VISIBLE);
//                time_icon_right.setVisibility(View.INVISIBLE);
                service_icon_right.setVisibility(View.INVISIBLE);
                choice_service_icon_right.setVisibility(View.INVISIBLE);

                editEnable(false, downOrderLogic.getFormMapp() != null, downOrderLogic.getToMapp() != null);
                rela_bottom.setVisibility(View.VISIBLE);
                break;
            case 4:
                person_rela.setTag(2);
                title.setText(SpannableUtils.setTextForeground(getString(R.string.downorder_status_doing), 3, 6, getResources().getColor(R.color.app_special_style_color_normal)));
                confirmBtn.setTag(6);
                confirmBtn.setText(R.string.downorder_btn_kook_service);
                confirmBtn.setVisibility(View.VISIBLE);
//                time_icon_right.setVisibility(View.INVISIBLE);
                service_icon_right.setVisibility(View.INVISIBLE);
                choice_service_icon_right.setVisibility(View.INVISIBLE);
                editEnable(false, downOrderLogic.getFormMapp() != null, downOrderLogic.getToMapp() != null);
                rela_bottom.setVisibility(View.VISIBLE);
                break;
            case 5:
                person_rela.setTag(2);
                title.setText(SpannableUtils.setTextForeground(getString(R.string.downorder_status_finish), 3, 5, getResources().getColor(R.color.app_special_style_color_normal)));
                confirmBtn.setTag(5);
                confirmBtn.setVisibility(downOrderLogic.getOrderDetailResp().getOrder().getOrder_star() > 0 ? View.INVISIBLE : View.VISIBLE);
                confirmBtn.setText(R.string.downorder_btn_score_comm);
//                time_icon_right.setVisibility(View.INVISIBLE);
                service_icon_right.setVisibility(View.INVISIBLE);
                choice_service_icon_right.setVisibility(View.INVISIBLE);
                editEnable(false, downOrderLogic.getFormMapp() != null, downOrderLogic.getToMapp() != null);
                rela_bottom.setVisibility(View.VISIBLE);
                break;
            case 6:
                person_rela.setTag(2);
                confirmBtn.setTag(4);
                title.setText(SpannableUtils.setTextForeground(getString(R.string.downorder_status_cancer), 3, 6, getResources().getColor(R.color.app_special_style_color_normal)));
                confirmBtn.setVisibility(View.INVISIBLE);
                confirmMoney.setVisibility(View.VISIBLE);
//                time_icon_right.setVisibility(View.INVISIBLE);
                service_icon_right.setVisibility(View.INVISIBLE);
                choice_service_icon_right.setVisibility(View.INVISIBLE);
                editEnable(false, downOrderLogic.getFormMapp() != null, downOrderLogic.getToMapp() != null);
                rela_bottom.setVisibility(View.VISIBLE);
                break;
            case 7:
                person_rela.setTag(2);
                rela_bottom.setVisibility(View.GONE);
                title.setText(SpannableUtils.setTextForeground(getString(R.string.downorder_status_failure), 3, 5, getResources().getColor(R.color.app_special_style_color_normal)));
//                time_icon_right.setVisibility(View.INVISIBLE);
                service_icon_right.setVisibility(View.INVISIBLE);
                if (downOrderLogic.getOrderDetailResp().getOrder().getOrder_delete_servant()==0){
                    delete_info.setVisibility(View.VISIBLE);
                }else {
                    delete_info.setVisibility(View.GONE);
                }
                choice_service_icon_right.setVisibility(View.INVISIBLE);
                confirmBtn.setVisibility(View.INVISIBLE);
                editEnable(false, downOrderLogic.getFormMapp() != null, downOrderLogic.getToMapp() != null);
                break;
        }

    }

    //    是否显示全名
    private void changeName(boolean showName) {
        if (downOrderLogic.getAttacheDetailResp() == null) return;
        /**接单为true 其他false*/
        if (showName) {
            person_name.setText(downOrderLogic.getAttacheDetailResp().getUser_name());
        } else {
            if (!TextUtils.isEmpty(downOrderLogic.getAttacheDetailResp().getUser_name())
                && downOrderLogic.getAttacheDetailResp().getUser_name().length() > 1) {
                person_name.setText(nameModify(downOrderLogic.getAttacheDetailResp().getUser_name()));

//                if (downOrderLogic.getAttacheDetailResp().getUser_gender() == 1) {
//                    String name = downOrderLogic.getAttacheDetailResp().getUser_name().substring(0, 1) + "爸爸";
//                    person_name.setText(name);
//                } else {
//                    String name = downOrderLogic.getAttacheDetailResp().getUser_name().substring(0, 1) + "妈妈";
//                    person_name.setText(name);
//                }
            } else {
                person_name.setText(downOrderLogic.getAttacheDetailResp().getUser_name());
            }
        }

    }

    private void editEnable(boolean enable, boolean hasStart, boolean hasEnd) {

        rela_choose_service.setClickable(enable);
        rela_service_date.setClickable(enable);
//        rela_start_time.setClickable(enable);

        if (hasStart) {
            ll_js_address_start.setClickable(true);
            ll_js_address_start.setTag(enable ? 0 : 1);
        } else {
            ll_js_address_start.setClickable(enable);
        }

        if (hasEnd) {
            ll_js_address_end.setClickable(true);
            ll_js_address_end.setTag(enable ? 0 : 1);
        } else {
            ll_js_address_end.setClickable(enable);
        }

        pcater_subtract.setClickable(enable);
        pcater_plus.setClickable(enable);

        et_js_address_start.setEnabled(enable);
        et_js_address_end.setEnabled(enable);
        et_nurse_address.setEnabled(enable);
        ed_service_address.setEnabled(enable);
        tv_service_require.setEnabled(enable);
        extra_cast.setEnabled(enable);

        scrollView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onSure(int rating) {
        AppLog.D("rating:" + rating);
        String orderId = downOrderLogic.getUserOrderListResp() != null
                         ? downOrderLogic.getUserOrderListResp().getOrder_id() : downOrderLogic.getOrderDetailResp().getOrder().getOrder_id();
        new OrderScoreHttp(this, handler, new OrderScoreReq(orderId, String.valueOf(rating)), WHAT_ORDER_COMM);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishAc(isRefreshOrder);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    private void finishAc(boolean isRefresh) {
        setResult(isRefresh ? OrderFragment.RESULT_CODE_REFRESH : OrderFragment.RESULT_CODE_NOT_CHANGE);
        finish();
    }

    private void initAddMoneyDialog() {
        if (addMoneyDialog == null) {
            addMoneyDialog = new AddMoneyDialog();
            addMoneyDialog.setOnAddMoneyListener(this);
        }
        addMoneyDialog.setOrderDetailResp(downOrderLogic.getOrderDetailResp());

        if (!addMoneyDialog.isAdded())
            addMoneyDialog.show(getSupportFragmentManager(), "addMoneyDialog");
    }

    @Override
    public void onAddMoneySure() {
        Intent intent = new Intent(this, OrderPayActivity.class);
        intent.putExtra(IntentFinal.INTENT_ORDER_DETAIL_OBJ, downOrderLogic.getOrderDetailResp());
        intent.putExtra(IntentFinal.INTENT_PAY_TYPE, 2);
        startActivityForResult(intent, 1);
    }

    public void refreshData() {
        if (downOrderLogic.getOrderDetailResp() == null) return;

        if (animator != null) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (animator.isStarted()) animator.resume();
            else
                animator.start();
        }

        new OrderDetailHttp(this, handler, downOrderLogic.getOrderDetailResp().getOrder().getOrder_id(), WHAT_GET_ORDERDETAL);
    }

    private void initRefreshView() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(refresh, "rotation", 0f, 360.0f);
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


    private void initSubmitDialog() {
        if (downOrderSureDialog == null) {
            downOrderSureDialog = new DownOrderSureDialog();
            downOrderSureDialog.setDownOrderSureListener(this);
        }
        downOrderSureDialog.show(getSupportFragmentManager(), "downOrderSureDialog");
    }

    @Override
    public void onDownOrderSure() {

        switch ((int) confirmBtn.getTag()) {
            case 1:
                new OrderCreatHttp(this, handler, downOrderLogic.getOrderCreateReq(et_js_address_start.getText().toString().trim()
                        , et_js_address_end.getText().toString().trim()
                        , ed_service_address.getText().toString().trim()
                        , extra_cast.getText().toString().trim(),tv_service_require.getText().toString().trim()), WHAT_CREATE_ORDER);
                break;
            case 2:
                OrderCreateReq orderCreateReq = downOrderLogic.getOrderCreateReq(et_js_address_start.getText().toString().trim()
                        , et_js_address_end.getText().toString().trim()
                        , ed_service_address.getText().toString().trim()
                        , extra_cast.getText().toString().trim(),tv_service_require.getText().toString().trim());
                orderCreateReq.setOrder_id(downOrderLogic.getOrderDetailResp().getOrder().getOrder_id());
                new OrderSureHttp(this, handler, orderCreateReq, WHAT_SURE_ORDER);
                break;
        }
    }

    private CancerOrderDialog cancerOrderDialog;

    private void initCancerOrderDialog() {
        if (cancerOrderDialog == null) {
            cancerOrderDialog = new CancerOrderDialog();
            cancerOrderDialog.setOnSureListener(this);
        }
        cancerOrderDialog.show(getSupportFragmentManager(), "cancerOrderDialog");
    }

    @Override
    public void onCancerSure() {
        String orId = downOrderLogic.getUserOrderListResp() == null ? downOrderLogic.getOrderDetailResp().getOrder().getOrder_id() : downOrderLogic.getUserOrderListResp().getOrder_id();
        new OrderCancerHttp(this, handler, new AttacheOrderIdReq(orId), WHAT_ORDER_CANCER);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (countDownTime != null) countDownTime.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (countDownTime != null) countDownTime.onReStart();
    }

    public class AttacheAddressWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(address) && !TextUtils.isEmpty(s.toString())) {
                AppLog.D("address:" + address + "s.toString():" + s.toString());
                if (address.equals(s.toString().trim())) {
                    tv_js_address_end_prompt.setVisibility(View.VISIBLE);
                } else {
                    tv_js_address_end_prompt.setVisibility(View.GONE);
                }
            } else {
                tv_js_address_end_prompt.setVisibility(View.GONE);
            }
        }
    }


    OrderChildInfoDialog orderChildInfoDialog;

    private void showEditChildDialog() {
        if (orderChildInfoDialog == null) {
            orderChildInfoDialog = new OrderChildInfoDialog();

        }
        orderChildInfoDialog.show(getSupportFragmentManager(), "OrderChildInfoDialog");
        orderChildInfoDialog.setOnSureListener(new OrderChildInfoDialog.OrderChildInfoListener() {
            @Override
            public void onSure() {
                Intent intent= new Intent(DownOrderActivity.this, ChildrenInfoEditActivity.class);
                intent.putExtra(IntentFinal.INTENT_DOWNORDER_TO_CHILD_EDIT,true);
                startActivityForResult(intent, 1);
                orderChildInfoDialog.dismiss();
            }
        });
    }


    private String DataTime;
    private Calendar calendar = Calendar.getInstance();
    public void onYearMonthDayTimePicker(View view) {
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_OF_DAY);
        picker.setRange(2000, 2030);
        picker.setSelectedItem(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                AppLog.D("calendar.get(Calendar.HOUR):"+calendar.get(Calendar.HOUR));
                AppLog.D("calendar.get(Calendar.HOUR_OF_DAY):"+calendar.get(Calendar.HOUR_OF_DAY));
                String hour1=hour;
                String minute1=minute;
                if(hour.length()<=1){hour1="0"+hour;}
                if(minute.length()<=1){minute1="0"+minute;}
                DataTime=year + "-" + month + "-" + day + " " + hour1 + ":" + minute1;
                tv_service_date.setText(DataTime);
                downOrderLogic.setChoiceData(DataTime+":00");
//                ToastUtils.toastLong(DownOrderActivity.this,DataTime);
            }
        });
        picker.show();
    }
}
