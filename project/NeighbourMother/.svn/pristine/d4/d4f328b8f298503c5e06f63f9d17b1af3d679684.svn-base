package com.qiantang.neighbourmother.ui.center;

import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.ExitLoginDialog;

import java.util.HashMap;

/**
 * ClassName:设置
 * author: Cocoa
 * date: 2016/10/8.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private TextView  btn_exit;//退出
    private ImageView back;//返回

    @Override
    public int getContentView() {
        return R.layout.activity_settings;
    }

    @Override
    public void initView() {
        btn_exit = (TextView) findViewById(R.id.btn_exit);
        back = (ImageView) findViewById(R.id.back);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        btn_exit.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {

    }

    private ExitLoginDialog dialog;

    private void showDialog() {
        if (dialog == null) {
            dialog = new ExitLoginDialog();
        }
        dialog.show(getSupportFragmentManager(), "SettingsActivity");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit:
                showDialog();
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
