package com.cocoa.cocoautils.ui.setting;

import android.view.View;

import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.ui.base.BaseFragment;
import com.cocoa.cocoautils.ui.setting.presenter.SettingsPresenter;
import com.cocoa.cocoautils.ui.setting.view.SettingsView;

/**
 * ClassName:设置
 * author: Cocoa
 * date: 2016/11/14.
 */

public class SettingsFragment extends BaseFragment<SettingsView, SettingsPresenter> implements View.OnClickListener {
    @Override
    public void onClick(View view) {

    }

    @Override
    public SettingsPresenter initPresenter() {
        return new SettingsPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_settings;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
