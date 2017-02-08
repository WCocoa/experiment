package com.qiantang.neighbourmother.ui.center;

import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.ui.BaseActivity;

/**
 * ClassName:产品介绍
 * author: Cocoa
 * date: 2016/10/12.
 */

public class ProductIntroActivity extends BaseActivity implements View.OnClickListener {
    private ImageView   back;//返回
    private ProgressBar myProgressBar;//
    private WebView     company_web;

    @Override
    public int getContentView() {
        return R.layout.activity_product_intro;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        myProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        company_web = (WebView) findViewById(R.id.company_web);

    }

    @Override
    public void initData() {
        company_web.getSettings().setJavaScriptEnabled(true);
        company_web.setHorizontalScrollBarEnabled(false);
        company_web.setVerticalScrollBarEnabled(false);
        company_web.clearCache(true);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        company_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    myProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == myProgressBar.getVisibility()) {
                        myProgressBar.setVisibility(View.VISIBLE);
                    }
                    myProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
