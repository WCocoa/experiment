package com.qiantang.neighbourmother.ui.startpage;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.MainTabActivity;
import com.qiantang.neighbourmother.util.PropertyConfig;

/**
 * ClassName:身份选择
 * author: Cocoa
 * date: 2016/9/19.
 */
public class IdentityChoiceActivity extends BaseActivity implements View.OnClickListener {
    private ImageView commissioner;//专员
    private ImageView user;//用户

    @Override
    public int getContentView() {
        return R.layout.activity_identity_choice;
    }

    @Override
    public void initView() {
        commissioner = (ImageView) findViewById(R.id.commissioner);
        user = (ImageView) findViewById(R.id.user);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        commissioner.setOnClickListener(this);
        user.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user:
                PropertyConfig.getInstance(this).save(UserContacts.USER_ROLE, 1);
                startActivity(new Intent(this, MainTabActivity.class));
                finish();
                break;
            case R.id.commissioner:
                PropertyConfig.getInstance(this).save(UserContacts.USER_ROLE, 2);
                startActivity(new Intent(this, MainTabActivity.class));
                finish();
                break;
        }
    }
}
