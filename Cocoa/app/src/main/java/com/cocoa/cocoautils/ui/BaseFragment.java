package com.cocoa.cocoautils.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cocoa.cocoautils.utils.AppLog;


/**
 * ClassName:Fragment基类
 * author: Cocoa
 * date: 2016/11/14.
 */
@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment {

    private   View     rootView;

    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   ViewGroup container, Bundle savedInstanceState) {
        AppLog.D("BaseFragment:onCreateView");
        if (rootView == null || getSaveView()) {
            rootView = inflater.inflate(getContentView(), null);
            initView(rootView);
            initEvent();
            initData();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();

        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    protected boolean getSaveView() {
        return false;
    }

    public abstract int getContentView();

    public abstract void initView(View view);

    public abstract void initData();

    public abstract void initEvent();


    public void showProgressDialog() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showProgressDialog();
    }

    public void closeProgressDialog() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).closeProgressDialog();
    }

}
