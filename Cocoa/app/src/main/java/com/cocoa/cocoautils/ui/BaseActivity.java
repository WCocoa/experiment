package com.cocoa.cocoautils.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.cocoa.cocoautils.R;
import com.cocoa.cocoautils.broadcast.BaseBroadCast;
import com.cocoa.cocoautils.ui.common.IntentFinal;
import com.cocoa.cocoautils.ui.dialog.ProcessDialog;


/**
 * ClassName:Activity基类
 * author: Cocoa
 * date: 2016/11/14.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private   ProcessDialog mProcessDialog;
    protected Handler       handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initAppMsgBroad();
        initView();
        initEvent();
        initData();
    }

    public abstract int getContentView();

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();

    public void showProgressDialog() {
        if (mProcessDialog == null) {
            if (getParent() != null) {
                mProcessDialog = new ProcessDialog(getParent());
            } else {
                mProcessDialog = new ProcessDialog(this);
            }
            mProcessDialog.setCancelable(false);
            mProcessDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (!isFinishing()) {
            mProcessDialog.show();
        }
    }


    public void closeProgressDialog() {
        if (mProcessDialog != null)
            mProcessDialog.dismiss();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        if (myBroadCast != null)
            myBroadCast.onDestroy();
        if (mProcessDialog != null) {
            mProcessDialog.dismiss();
        }
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        } else {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
            return false;
        }
    }




    public void calcuAdersWidth(final View view, final float scale) {

        view.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams   = view.getLayoutParams();
                DisplayMetrics         displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                layoutParams.height = (int) (displayMetrics.widthPixels * scale);
                view.setLayoutParams(layoutParams);
                view.postInvalidate();
            }
        });

    }


    public void loginSuccess() {
    }


    private BaseBroadCast myBroadCast;

    private void initAppMsgBroad() {
        myBroadCast = new BaseBroadCast(this, new String[]{IntentFinal.BROADCAST_QUIT_ACTIVITY});
        myBroadCast.setOnMyBroadCastListener(new BaseBroadCast.MyBroadCastListener() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(IntentFinal.BROADCAST_QUIT_ACTIVITY) && quitOfParent()) {
                    finish();
                }
            }
        });
    }
    public void display(final ImageView imageView, final Context context, String url, int failureImage, int type) {

                Glide.with(getApplicationContext())
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(failureImage)
                        .animate(R.anim.glide_load_image_anim)
                        .fitCenter()
//                        .listener(new CommonRequsetListener())
                        .crossFade()
                        .into(imageView).getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (imageView.isShown()) {
                            imageView.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    public boolean quitOfParent() {
        return true;
    }
}
