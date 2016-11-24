package com.cocoa.cocoautils.ui.run;

import android.view.View;

import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.ui.base.BaseFragment;
import com.cocoa.cocoautils.ui.run.presenter.RunFragmentPresenter;
import com.cocoa.cocoautils.ui.run.view.RunFragmentView;

/**
 * ClassName:设置
 * author: Cocoa
 * date: 2016/11/14.
 */

public class RunFragment extends BaseFragment<RunFragmentView, RunFragmentPresenter> implements View.OnClickListener {
    @Override
    public void onClick(View view) {

    }

    @Override
    public RunFragmentPresenter initPresenter() {
        return new RunFragmentPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_run;
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
