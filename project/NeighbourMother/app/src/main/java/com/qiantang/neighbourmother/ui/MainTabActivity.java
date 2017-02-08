package com.qiantang.neighbourmother.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.qiantang.neighbourmother.NeighbourMotherApplication;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.data.CkeckVersionHttp;
import com.qiantang.neighbourmother.business.response.VersionResp;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.ui.center.user.CenterFragment;
import com.qiantang.neighbourmother.ui.community.ConmmunityFragment;
import com.qiantang.neighbourmother.ui.dialog.VersionUpdateDialog;
import com.qiantang.neighbourmother.ui.home.HomeFragment;
import com.qiantang.neighbourmother.ui.order.attache.OrderFragment;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;
import com.qiantang.neighbourmother.util.ToastUtils;


/**
 * 主菜单
 *
 * @date 创建时间：2015年9月16日 上午9:21:36
 */
public class MainTabActivity extends BaseActivity {

    private long exitTime = 0;
    private FragmentTabHost mTabHost;

    private LayoutInflater layoutInflater;

    private int mImageViewArray[] = {R.drawable.hometab_home,R.drawable.hometab_conmmunity,
            R.drawable.hometab_order,  R.drawable.hometab_center};

    private int mTextviewArray[] = {R.string.homeTab_home,R.string.homeTab_conmmunity, R.string.homeTab_order,  R.string.homeTab_center};
//    private int mTextviewArray[] = {R.string.homeTab_home, R.string.homeTab_order, R.string.homeTab_center};

    private Class fragmentArray[] = {HomeFragment.class, ConmmunityFragment.class,OrderFragment.class,  CenterFragment.class};
//    private Class fragmentArray[] = {HomeFragment.class, OrderFragment.class,  CenterFragment.class};

    private String mTabTagArray[] = {"tab_home","tab_conmmunity", "tab_preview",  "tab_center"};
//    private String mTabTagArray[] = {"tab_home", "tab_preview", "tab_center"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        // TODO Auto-generated method stub
        return R.layout.activity_maintab;
    }

    @Override
    public void initView() {
        typeChoose();
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.tab_background));
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTagArray[i])
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }
    }

    private void typeChoose() {
        switch (PropertyConfig.getInstance(this).getInt(UserContacts.USER_ROLE)) {
            case 1:
                fragmentArray[3] = CenterFragment.class;
                fragmentArray[2] = com.qiantang.neighbourmother.ui.order.user.OrderFragment.class;
                break;
            case 2:
                fragmentArray[3] = com.qiantang.neighbourmother.ui.center.attache.CenterFragment.class;
                fragmentArray[2] = com.qiantang.neighbourmother.ui.order.attache.OrderFragment.class;
                break;
        }
    }

    @Override
    public void initData() {

        new CkeckVersionHttp(MainTabActivity.this, handler, false, 1);
    }


    @Override
    public void initEvent() {
        // TODO Auto-generated method stub
    }


    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                VersionResp ver = (VersionResp) msg.obj;
                if (ver.getVerCode() > NeighbourMotherApplication.versionCode) {
                    showDialog(ver);
                }
                break;
        }
        closeProgressDialog();
    }


    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {

        View      view      = layoutInflater.inflate(R.layout.item_tab_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        //            ImageView img_point = (ImageView) view.findViewById(R.id.img_point);
        //            img_point.setVisibility(View.GONE);

        return view;

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
            && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.toastLong(this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private VersionUpdateDialog dialog;

    private void showDialog(VersionResp versionInfo) {
        if (dialog == null) {
            dialog = new VersionUpdateDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable(IntentFinal.INTENT_VERSION_OBJ_KEY, versionInfo);
            dialog.setArguments(bundle);
            dialog.setCancelable(false);
        }
        dialog.show(getSupportFragmentManager(), "versionDialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String   tag      = mTabHost.getCurrentTabTag();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
