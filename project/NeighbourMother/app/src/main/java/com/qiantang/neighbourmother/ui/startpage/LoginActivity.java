package com.qiantang.neighbourmother.ui.startpage;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.LoginHttp;
import com.qiantang.neighbourmother.business.data.PhoneCodeHttp;
import com.qiantang.neighbourmother.business.request.LoginReq;
import com.qiantang.neighbourmother.business.request.PhoneCodeReq;
import com.qiantang.neighbourmother.business.response.ChildInfoResp;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.logic.ChildContacts;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.MainTabActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.MyUtil;
import com.qiantang.neighbourmother.util.PropertyConfig;
import com.qiantang.neighbourmother.util.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName:登录
 * author: Cocoa
 * date: 2016/9/17.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText  input_phone;//手机号码
    private EditText  input_code;//验证码
    private TextView  send_code;//发送验证码
    private TextView  btn_login;//登录
//    private ImageView login_top_icon;//顶部图片
    private String    strPhone;

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        input_phone = (EditText) findViewById(R.id.input_phone);
        input_code = (EditText) findViewById(R.id.input_code);
        send_code = (TextView) findViewById(R.id.send_code);
        btn_login = (TextView) findViewById(R.id.btn_login);
//        login_top_icon = (ImageView) findViewById(R.id.login_top_icon);
//        calcuAdersWidth(login_top_icon, 0.5f);
    }

    @Override
    public void initData() {
        int login_type = getIntent().getIntExtra(IntentFinal.INTENT_LOGIN_TOKEN_FAILE, 0);
        if (login_type == 1) {
            sendBroadcast(new Intent(IntentFinal.BROADCAST_QUIT_ACTIVITY));
        }
    }

    @Override
    public void initEvent() {
        send_code.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {

        switch (msg.what) {
            case 1:
                send_code.setEnabled(false);
                count = 60;
                timer = new Timer(true);
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        handler.post(runnableUi);
                    }
                }, 0L, 1000L);
                ToastUtils.toastshort(this,
                        R.string.code_time_send);
                closeProgressDialog();
                break;
            case 2:
                UserInfoResp userInfoResp = (UserInfoResp) msg.obj;
                AppLog.D("userInfoResp:" + userInfoResp.toString());
                UserContacts.saveUserInfo(this, userInfoResp, true);
                saveChildInfo(userInfoResp);
                PropertyConfig.getInstance(this).save(UserContacts.USER_ACOUNT, strPhone);
                if (userInfoResp.getUser_type() == 2) {

                    startActivity(new Intent(this, IdentityChoiceActivity.class));
                } else if (userInfoResp.getUser_type() == 1) {
                    PropertyConfig.getInstance(this).save(UserContacts.USER_ROLE, 1);
                    startActivity(new Intent(this, MainTabActivity.class));
                } else if (userInfoResp.getUser_type() == 3) {
                    startActivity(new Intent(this, IdentityChoiceActivity.class));
                }
                closeProgressDialog();
                finish();
                break;
        }
    }

    private void saveChildInfo(UserInfoResp userInfoResp) {
        ChildInfoResp child = new ChildInfoResp();
        child.setChild_nickname(userInfoResp.getChild_nickname());
        child.setChild_gender(userInfoResp.getChild_gender());
        child.setChild_grade(userInfoResp.getChild_grade());
        child.setChild_school(userInfoResp.getChild_school());
        child.setChild_avatar(userInfoResp.getChild_avatar());
        child.setChild_id(userInfoResp.getChild_id());
        child.setChild_name(userInfoResp.getChild_name());
        ChildContacts.saveChildInfo(this, child);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
//                startActivity(new Intent(this, IdentityChoiceActivity.class));
//                finish();
                strPhone = input_phone.getText().toString().trim();
                String code = input_code.getText().toString().trim();
                if (checkLoginInput(code)) {
                    //登录请求接口
                    new LoginHttp(this, handler, new LoginReq(strPhone, code), true, 2);
                }
                break;
            case R.id.send_code:
                strPhone = input_phone.getText().toString().trim();
                sendCode(strPhone);
                break;
        }
    }

    /**
     * 发送验证码
     *
     * @param phone
     */
    private void sendCode(String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.toastLong(this, R.string.phone_is_not_empty);
            return;
        }
        if (!MyUtil.isMobileNumber(phone)) {
            ToastUtils.toastLong(this, R.string.input_phone_not_error);
            return;
        }
//验证码请求接口
        new PhoneCodeHttp(LoginActivity.this, handler, new PhoneCodeReq(strPhone, "1"), 1);
    }

    /**
     * 登录校验
     *
     * @param code
     * @return
     */
    private boolean checkLoginInput(String code) {
//        if (TextUtils.isEmpty(phone)) {
//            ToastUtils.toastLong(this, R.string.phone_is_not_empty);
//            return false;
//        }
        //        if (!MyUtil.isMobileNumber(phone)) {
//            ToastUtils.toastshort(this, R.string.input_phone_not_error);
//            return false;
//        }

        if (TextUtils.isEmpty(code)) {
            ToastUtils.toastLong(this, R.string.code_is_not_empty);
            return false;
        }


        return true;
    }

    Timer timer = null;
    static Integer  count      = 60;
    /**
     * 更新发送验证码时间
     */
    final  Runnable runnableUi = new Runnable() {
        @Override
        public void run() {
            // 更新界面
            count--;
            send_code.setText("" + count + "");
            if (count < 0) {
                send_code.setEnabled(true);
                send_code.setText(R.string.send_code_text);
                timer.cancel();
            }
        }
    };

    //处理后退键的情况
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (loginType == 1) {
//                this.finish();  //finish当前activity
//                overridePendingTransition(R.anim.a_exit,
//                        R.anim.a_enter1);
//                return true;
//            } else {
//                return super.onKeyDown(keyCode, event);
//            }
//        }else{
//            return super.onKeyDown(keyCode, event);
//        }
//
//    }


    @Override
    public boolean quitOfParent() {
        return false;
    }

}
