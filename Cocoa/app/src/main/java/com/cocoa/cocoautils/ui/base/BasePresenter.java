package com.cocoa.cocoautils.ui.base;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/10/28.
 */

public abstract class BasePresenter<T> {


    public T mView;

    public void attachView(T mView) {
        this.mView = mView;
    }

    public void dettachView() {
        if (mView != null) {
            mView = null;
        }
    }


}
