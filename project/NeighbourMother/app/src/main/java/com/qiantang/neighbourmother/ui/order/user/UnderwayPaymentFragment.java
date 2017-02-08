package com.qiantang.neighbourmother.ui.order.user;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.UserOrderAdapter;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.UserOrderListHttp;
import com.qiantang.neighbourmother.business.response.UserOrderListResp;
import com.qiantang.neighbourmother.ui.BaseFragment;
import com.qiantang.neighbourmother.ui.home.DownOrderActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

import java.util.List;

/**
 * ClassName:用户订单，进行中
 * author: Cocoa
 * date: 2016/9/26.
 */
public class UnderwayPaymentFragment extends BaseFragment implements View.OnClickListener {
    private XListView listview;//
    private UserOrderAdapter orderAdapter;
    private List<UserOrderListResp> userOrderListResps;
    private boolean isRefresh;
    private RelativeLayout rl_default_page;//没有数据默认页面

    @Override
    public int getContentView() {
        return R.layout.fragment_underway_payment;
    }

    @Override
    public void initView(View view) {
        listview = (XListView) view.findViewById(R.id.listview);
        rl_default_page = (RelativeLayout) view.findViewById(R.id.rl_default_page);
    }

    @Override
    public void initData() {
        orderAdapter = new UserOrderAdapter(getParentFragment().getActivity(), 3, handler);
        listview.setPullLoadEnable(false);
        listview.aotuRefreshComplete();
        listview.setAdapter(orderAdapter);
        getHttpData(true, true, 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRefresh) getHttpData(true, true, 1);
    }

    private void getHttpData(boolean isFirst, boolean isShowProgress, int what) {
        isRefresh = false;
        int count = isFirst == true ? 0 : orderAdapter.getDataList().size();
        new UserOrderListHttp(getParentFragment().getActivity(), handler, 2 ,count, what, isShowProgress);
    }

    @Override
    public void initEvent() {

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getParentFragment().getActivity(), DownOrderActivity.class);
                intent.putExtra(IntentFinal.INTENT_TO_DOWN_ORDER, 2);
                intent.putExtra(IntentFinal.INTENT_ORDER_ITEM_OBJ, orderAdapter.getDataList().get(i - 1));
                startActivityForResult(intent, 1);

            }
        });
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getHttpData(true, false, 1);
            }

            @Override
            public void onLoadMore() {
                getHttpData(false, false, 2);
            }
        });
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                userOrderListResps = (List<UserOrderListResp>) msg.obj;
                setData(userOrderListResps, true);
                break;
            case 2:
                userOrderListResps = (List<UserOrderListResp>) msg.obj;
                setData(userOrderListResps, false);
                break;

        }
        if (orderAdapter.getDataList() == null || orderAdapter.getDataList().size() <= 0) {
            rl_default_page.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        } else {
            rl_default_page.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }
        closeProgressDialog();
    }

    private void setData(List<UserOrderListResp> userOrderListResps, boolean isFirst) {
        if (isFirst) {
            orderAdapter.getDataList().clear();
        }
        if (userOrderListResps != null && userOrderListResps.size() > 0) {
            orderAdapter.getDataList().addAll(userOrderListResps);
            if (userOrderListResps.size() < 10) {
                listview.setPullLoadEnable(false);
            } else {
                listview.setPullLoadEnable(true);
            }
        } else {
            ToastUtils.toastshort(getParentFragment().getActivity(), isFirst ? getString(R.string.app_pull_not_data) : getString(R.string.app_pull_already_last_page));
        }
        orderAdapter.notifyDataSetChanged();
        listview.aotuRefreshComplete();

    }

    @Override
    public void onClick(View view) {

    }

//    private boolean isDelete = false;
//
//    public void deleteItem(boolean isShow) {
//        OrderFragment.flag = true;
////        isDelete = true;
////        orderAdapter.setDelete(isDelete);
////        orderAdapter.notifyDataSetChanged();
//        ToastUtils.toastLong(getParentFragment().getActivity(), R.string.order_underway_not_del);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        AppLog.D("UnderwayPaymentFragment.onActivityResult.resultCode:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == OrderFragment.RESULT_CODE_REFRESH) {
            getHttpData(true, true, 1);
        }
    }

    @Override
    protected void clossRefresh() {
        super.clossRefresh();
        listview.aotuRefreshComplete();
        orderAdapter.notifyDataSetChanged();
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }
}
