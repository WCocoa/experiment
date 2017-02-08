package com.qiantang.neighbourmother.ui.home;

import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.data.JoinTeacherApplyHttp;
import com.qiantang.neighbourmother.business.data.SignTeacherJoinHttp;
import com.qiantang.neighbourmother.business.request.JoinTeacherApplyReq;
import com.qiantang.neighbourmother.business.response.SignTeacherJoinResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.TeacherJoinDialog;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * ClassName:教师加盟
 * author: Cocoa
 * date: 2017/1/9.
 */

public class TeacherToJoinActivity extends BaseActivity implements View.OnClickListener, TeacherJoinDialog.TeacherJoinListener {
    private ImageView   back;//
    private TextView    btn_sign_join;//
    private ProgressBar pb_progress;//
    private WebView     teacher_content;//

    @Override
    public int getContentView() {
        return R.layout.activity_teacher_to_join;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        btn_sign_join = (TextView) findViewById(R.id.btn_sign_join);
        pb_progress = (ProgressBar) findViewById(R.id.pb_progress);
        teacher_content = (WebView) findViewById(R.id.teacher_content);
    }

    @Override
    public void initData() {
        teacher_content.getSettings().setJavaScriptEnabled(true);
        teacher_content.setHorizontalScrollBarEnabled(false);
        teacher_content.setVerticalScrollBarEnabled(false);
        teacher_content.clearCache(true);
        new SignTeacherJoinHttp(this, handler, true, 1);
//        teacher_content.loadUrl("file:///android_asset/company/company.html");
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        btn_sign_join.setOnClickListener(this);

        teacher_content.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100)
                    pb_progress.setVisibility(View.INVISIBLE);
                else {
                    if (View.INVISIBLE == pb_progress.getVisibility())
                        pb_progress.setVisibility(View.VISIBLE);
                    pb_progress.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                SignTeacherJoinResp signTeacherJoinResp = (SignTeacherJoinResp) msg.obj;
                teacher_content.loadDataWithBaseURL(null, signTeacherJoinResp.getContent(), "text/html", "utf-8", null);
                closeProgressDialog();
                break;

            case 2:
                ToastUtils.toastLong(this, R.string.teacherjoin_d_apply_already_submit);
                closeProgressDialog();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_sign_join:
//                ToastUtils.toastshort(this, "申请加盟");
                showJoinDialog();
                break;
        }
    }

    private TeacherJoinDialog teacherJoinDialog;

    private void showJoinDialog() {
        if (teacherJoinDialog == null) {
            teacherJoinDialog = new TeacherJoinDialog();
            teacherJoinDialog.setOnAccountPayListener(this);
        }
        teacherJoinDialog.show(getSupportFragmentManager(), "teacherJoinDialog");
    }

    @Override
    public void onTeacherJoin(String name, String tel) {

        AppLog.D("onTeacherJoin1:" + name);
        AppLog.D("onTeacherJoin2:" + tel);

        new JoinTeacherApplyHttp(this, handler, new JoinTeacherApplyReq(name, tel), 2);

    }
}
