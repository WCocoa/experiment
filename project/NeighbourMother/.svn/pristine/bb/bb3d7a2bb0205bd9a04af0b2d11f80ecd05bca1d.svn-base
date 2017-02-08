package com.qiantang.neighbourmother.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiantang.neighbourmother.business.data.BaseHttp;
import com.qiantang.neighbourmother.util.AppLog;


/**
 * @author quliang
 * @version 2015-8-20 上午11:28:11
 *          desc
 */
@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment {

    protected Handler handler;
    private View rootView;
//    protected Activity mActivity;

    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   ViewGroup container, Bundle savedInstanceState) {
        AppLog.D("BaseFragment:onCreateView");
        if (rootView == null || getSaveView()) {
            rootView = inflater.inflate(getContentView(), null);
            initHandler();
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

    protected abstract void updateUI(Message msg);

    private String getFraqmentName() {
        String className = getClass().getName().substring(getClass().getName().lastIndexOf(".") + 1);
        AppLog.D("getActivityNamegetActivityNamegetActivityNamegetActivityName:" + className);
        return className;
    }


    protected void initHandler() {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case BaseHttp.START:
                        showProgressDialog();
                        break;

                    case BaseHttp.TOKEN_INVALID:
                        closeProgressDialog();
                        ((BaseActivity) getActivity()).onTokenInvalid();
                        break;

                    case BaseHttp.FAILURE:
                        failureOperation(msg.obj);
                        closeProgressDialog();
                        break;
                    case BaseHttp.END:
                        closeProgressDialog();
                        break;
                    case BaseHttp.CHILD_INFO_INVLID:
//                        ((BaseActivity) getActivity()).editChildInfo();
                        closeProgressDialog();
                        break;
                    default:
                        updateUI(msg);
                        break;
                }
            }
        };


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case BaseHttp.START:
                        showProgressDialog();
                        break;

                    case BaseHttp.TOKEN_INVALID:
                        closeProgressDialog();
                        ((BaseActivity) getActivity()).onTokenInvalid();
//					showProgressDialog();
//					Intent intent=new Intent(getActivity(),LoginActivity.class);
//					startActivity(intent);
                        break;

                    case BaseHttp.FAILURE:
                        failureOperation(msg.obj);
                        break;
                    default:
                        updateUI(msg);
                        break;
                }
            }
        };
    }


    public void failureOperation(Object obj) {
        ((BaseActivity) getActivity()).failureToast(obj);
        closeProgressDialog();
        clossRefresh();
    }

    protected void clossRefresh() {
    }

    public void showProgressDialog() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showProgressDialog();
    }

    public void closeProgressDialog() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).closeProgressDialog();
    }

    protected void sendMsg(Handler handler, int what, Object object) {
        ((BaseActivity) getActivity()).sendMsg(handler, what, object);
    }


}
