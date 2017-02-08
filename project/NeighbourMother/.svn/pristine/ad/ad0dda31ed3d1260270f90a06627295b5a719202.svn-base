package com.qiantang.neighbourmother.ui.center;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.NeighbourMotherApplication;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.data.CkeckVersionHttp;
import com.qiantang.neighbourmother.business.response.VersionResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.dialog.VersionUpdateDialog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;

/**
 * ClassName:关于我们
 * author: Cocoa
 * date: 2016/10/10.
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    private ImageView      back;//返回
    private RelativeLayout rl_about_company;//公司介绍
    private RelativeLayout rl_about_product;//产品介绍
    private TextView       tv_version;//版本名称，版本号
    private RelativeLayout rl_about_update;//版本更新

    @Override
    public int getContentView() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        rl_about_company = (RelativeLayout) findViewById(R.id.rl_about_company);
//        rl_about_product = (RelativeLayout) findViewById(R.id.rl_about_product);
        rl_about_update = (RelativeLayout) findViewById(R.id.rl_about_update);
        tv_version = (TextView) findViewById(R.id.tv_version);
    }

    @Override
    public void initData() {
        tv_version.setText("临家妈妈 V " + getVersionName());
    }

    /**
     * 获取当前版本
     *
     * @return
     */
    private String getVersionName() {
        String         version = "";
        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        rl_about_company.setOnClickListener(this);
//        rl_about_product.setOnClickListener(this);
        rl_about_update.setOnClickListener(this);

    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:

                VersionResp ver = (VersionResp) msg.obj;
                if (ver.getVerCode() > NeighbourMotherApplication.versionCode) {
                    showDialog(ver);
                } else {
                    ToastUtils.toastLong(this,
                            getResources().getString(R.string.about_new_version));
                }
                closeProgressDialog();
                break;
        }
    }

    private VersionUpdateDialog dialog;

    private void showDialog(VersionResp versionInfo) {
        if (dialog == null) {
            dialog = new VersionUpdateDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable(IntentFinal.INTENT_VERSION_OBJ_KEY,
                    versionInfo);
            dialog.setArguments(bundle);
            dialog.setCancelable(false);
        }
        dialog.show(getSupportFragmentManager(), "versionDialog");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.rl_about_product:
//                startActivity(new Intent(this,ProductIntroActivity.class));
//                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_about_company:
                startActivity(new Intent(this, CompanyIntroActivity.class));
                break;
            case R.id.rl_about_update:
                new CkeckVersionHttp(AboutActivity.this, handler, true, 1);
                break;
        }
    }

}
