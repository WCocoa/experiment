package com.cocoa.cocoautils.ui.folder;

import android.view.View;

import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.ui.base.BaseFragment;
import com.cocoa.cocoautils.ui.folder.presenter.FolderFragmentPresenter;
import com.cocoa.cocoautils.ui.folder.view.FolderFragmentView;

/**
 * ClassName:设置
 * author: Cocoa
 * date: 2016/11/14.
 */

public class FolderFragment extends BaseFragment<FolderFragmentView, FolderFragmentPresenter> implements View.OnClickListener {
    @Override
    public void onClick(View view) {

    }

    @Override
    public FolderFragmentPresenter initPresenter() {
        return new FolderFragmentPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_folder;
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
