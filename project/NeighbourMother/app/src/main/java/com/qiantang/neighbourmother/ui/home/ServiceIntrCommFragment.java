package com.qiantang.neighbourmother.ui.home;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.ServiceIntroduceResp;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.ui.BaseFragment;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;

/**
 * ClassName:初中
 * author: Cocoa
 * date: 2016/9/18.
 */
public class ServiceIntrCommFragment extends BaseFragment implements View.OnClickListener {
    private WebView web;
    private RelativeLayout rl_bottom;
    private TextView btn_downOrder;
    private ServiceIntroduceResp serviceIntroduceResp;
    private int index;

    @Override
    public int getContentView() {
        return R.layout.fragment_service_comm;
    }

    @Override
    public void initView(View view) {
        web = (WebView) view.findViewById(R.id.web);
        btn_downOrder = (TextView) view.findViewById(R.id.btn_downOrder);
        rl_bottom = (RelativeLayout) view.findViewById(R.id.rl_bottom);
    }

    @Override
    public void initData() {
        serviceIntroduceResp = ((ServiceIntroduceActivity) getActivity()).getServiceIntroduceResps().get(index);
        AppLog.D("serviceIntroduceResp:" + serviceIntroduceResp.getService_phase_name());

        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        web.loadDataWithBaseURL(null, serviceIntroduceResp.getContent(), "text/html", "utf-8", null);
        if (PropertyConfig.getInstance(getActivity()).getInt(UserContacts.USER_ROLE) == 1) {
            rl_bottom.setVisibility(View.VISIBLE);
        } else {
            rl_bottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void initEvent() {
        btn_downOrder.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_downOrder:
                Intent intent = new Intent(getActivity(), DownOrderActivity.class);
                intent.putExtra(IntentFinal.INTENT_TO_DOWN_ORDER, 0);
                intent.putExtra(IntentFinal.INTENT_TO_DOWN_ORDER_SERVICE, index);
                AppLog.D("index:" + index);
                startActivity(intent);
                break;
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
