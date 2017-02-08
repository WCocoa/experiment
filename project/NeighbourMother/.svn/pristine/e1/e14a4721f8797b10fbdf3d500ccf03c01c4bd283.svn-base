package com.qiantang.neighbourmother.ui.center;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.FdAttacheApplySubHttp;
import com.qiantang.neighbourmother.business.data.KhAttacheApplySubHttp;
import com.qiantang.neighbourmother.business.data.UploadImageHttp;
import com.qiantang.neighbourmother.business.qlhttp.bean.UpFileObj;
import com.qiantang.neighbourmother.business.response.ApplyAttacheQuResp;
import com.qiantang.neighbourmother.logic.AuthUtil;
import com.qiantang.neighbourmother.model.AuthObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.CommisInfoSubSuccessDialog;
import com.qiantang.neighbourmother.ui.dialog.CommisInfoSubSuccessDialog.SureListener;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLScreenUtil;
import com.qiantang.neighbourmother.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * ClassName:孩子信息
 * author: Cocoa
 * date: 2016/9/20.
 */
public class CommissionerApplyCoachActivity extends BaseActivity implements View.OnClickListener,SureListener {

    private TextView back;
    private ImageView iv_tc;
    private ImageView iv_tc_delete;
    private ImageView iv_dc;
    private ImageView iv_dc_delete;
    private TextView btn_previous;
    private TextView btn_finish;

    private AuthUtil authUtil;

    private ApplyAttacheQuResp resp;
    private ApplyAttacheQuResp req;

    @Override
    public int getContentView() {
        return R.layout.activity_commissioner_apply_coach;
    }

    @Override
    public void initView() {
        back=(TextView)findViewById(R.id.back);
        iv_tc=(ImageView)findViewById(R.id.iv_tc);
        iv_tc_delete=(ImageView)findViewById(R.id.iv_tc_delete);
        iv_dc=(ImageView)findViewById(R.id.iv_dc);
        iv_dc_delete=(ImageView)findViewById(R.id.iv_dc_delete);
        btn_previous=(TextView)findViewById(R.id.btn_previous);
        btn_finish=(TextView)findViewById(R.id.btn_finish);
        initCell();
    }

    @Override
    public void initData() {

        resp = (ApplyAttacheQuResp) getIntent().getSerializableExtra(IntentFinal.RECUIT_ATTACHE_INFO_RESP);
        req = (ApplyAttacheQuResp) getIntent().getSerializableExtra(IntentFinal.RECUIT_ATTACHE_INFO_REQ);

        authUtil=new AuthUtil(this,handler);
        authUtil.add(new AuthObj(iv_tc, iv_tc_delete, 0, null, null));
        authUtil.add(new AuthObj(iv_dc, iv_dc_delete, 0, null, null));
        setData(resp);
//        authUtil.initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        authUtil.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        btn_previous.setOnClickListener(this);
        btn_finish.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {

            switch (msg.what){
                case 1:
                    String[] imgs = (String[]) msg.obj;
                    if (imgs != null){
                        switch (imgs.length){
                            case 1:
                                if(!TextUtils.isEmpty(authUtil.get(0).getLocImgPath())){
                                    req.setPic_teacher(imgs[0]);
                                }
                                if(!TextUtils.isEmpty(authUtil.get(1).getLocImgPath())){
                                    req.setPic_education(imgs[0]);
                                }
                                break;
                            case 2:
                                req.setPic_teacher(imgs[0]);
                                req.setPic_education(imgs[1]);
                                break;
                        }
                    }
                    subHttp();
                    break;

                case 2:
                    closeProgressDialog();
                    showSuccessDialog();
                    break;
            }

    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){
    case R.id.back:
        finish();
        break;
        case R.id.btn_previous:
            break;
        case R.id.btn_finish:
            ArrayList<UpFileObj> upFileObjs=getUpFiles();
            if(upFileObjs!=null&&upFileObjs.size()==0){
                req.setPic_teacher(resp.getPic_teacher());
                req.setPic_education(resp.getPic_education());
                subHttp();
            }else  if(upFileObjs!=null&&upFileObjs.size()>0){
                new UploadImageHttp(this, handler, upFileObjs, true, 1);
            }
            break;
}
    }

    private void initCell() {
        float unit = 15f;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int red_width = (displayMetrics.widthPixels - (int) QLScreenUtil.dpToPx(this, unit * 2 + 60)) / 2;

        cacuCellWH(iv_tc, red_width, red_width, 0, 0, (int) QLScreenUtil.dpToPx(this, unit), 0);
        cacuCellWH(iv_dc, red_width, red_width, (int) QLScreenUtil.dpToPx(this, unit), 0, 0, 0);
    }

    private void cacuCellWH(View view, int widht, int height, int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(left, top, right, bottom);
        layoutParams.height = height;
        layoutParams.width = widht;
        view.setLayoutParams(layoutParams);
        view.postInvalidate();
    }

    CommisInfoSubSuccessDialog commisInfoSubSuccessDialog;
    private void showSuccessDialog(){
        if(commisInfoSubSuccessDialog==null){
            commisInfoSubSuccessDialog=new CommisInfoSubSuccessDialog();
        commisInfoSubSuccessDialog.setOnSureListener(this);
        }
commisInfoSubSuccessDialog.show(getSupportFragmentManager(),"commisInfoSubSuccessDialog");
    }


    @Override
    public void onSure() {
        setResult(2);
        finish();
    }

    private void setData(ApplyAttacheQuResp applyAttacheQuResp) {

        String pic_teacher = applyAttacheQuResp.getPic_teacher();
        String pic_education = applyAttacheQuResp.getPic_education();

        AuthObj authObj0 = authUtil.get(0);
        AuthObj authObj1 = authUtil.get(1);

        authObj0.setNetImgPath(pic_teacher);
        authObj1.setNetImgPath(pic_education);

        authObj0.setHaveImg(TextUtils.isEmpty(pic_teacher) ? 0 : 1);
        authObj1.setHaveImg(TextUtils.isEmpty(pic_education) ? 0 : 1);

        authUtil.initData();
    }

    private ArrayList<UpFileObj> getUpFiles() {
        AuthObj authObj0 = authUtil.get(0);
        AuthObj authObj1 = authUtil.get(1);

        if (TextUtils.isEmpty(authObj0.getNetImgPath()) && TextUtils.isEmpty(authObj0.getLocImgPath())) {
            ToastUtils.toastLong(this, R.string.placea_please_finish_info);
            return null;
        }

        if (TextUtils.isEmpty(authObj1.getNetImgPath()) && TextUtils.isEmpty(authObj1.getLocImgPath())) {
            ToastUtils.toastLong(this, R.string.placea_please_finish_info);
            return null;
        }

        ArrayList<UpFileObj> upFileObjs = new ArrayList<UpFileObj>();

        if (!TextUtils.isEmpty(authObj0.getLocImgPath())) {
            upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(authObj0.getLocImgPath())));
        }
        if (!TextUtils.isEmpty(authObj1.getLocImgPath())) {
            upFileObjs.add(new UpFileObj(API.FILE_KEY, new File(authObj1.getLocImgPath())));
        }
        return upFileObjs;
    }

    private void subHttp(){
        new FdAttacheApplySubHttp(this,handler,req,2);
    }
}
