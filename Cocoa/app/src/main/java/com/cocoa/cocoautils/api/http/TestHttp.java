package com.cocoa.cocoautils.api.http;

import android.content.Context;
import android.os.Handler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;

/**
 * ClassNmae:
 * Author Cocoa
 * Date 2017/7/24 0024.
 */

public class TestHttp   {
    private Context context;
    private Handler handler;
    private int     what;

    public TestHttp(Context context, Handler handler, int what) {

        this.context = context;
        this.handler = handler;
        this.what = what;
    }


    void getHttp() {
       RetorfitManager.createClientApi().getinfo().subscribe(new Subject() {
           @Override
           public boolean hasObservers() {
               return false;
           }

           @Override
           public boolean hasThrowable() {
               return false;
           }

           @Override
           public boolean hasComplete() {
               return false;
           }

           @Override
           public Throwable getThrowable() {
               return null;
           }

           @Override
           protected void subscribeActual(Observer observer) {

           }

           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(Object value) {

           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onComplete() {

           }
       });

    }
}
