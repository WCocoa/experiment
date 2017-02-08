package com.qiantang.neighbourmother.ui.order.attache;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.OrderAdapter;
import com.qiantang.neighbourmother.business.data.AttacheOrderListHttp;
import com.qiantang.neighbourmother.business.response.AttacheOrderListResp;
import com.qiantang.neighbourmother.ui.BaseFragment;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

import java.util.List;

/**
 * ClassName:订单，进行中
 * author: Cocoa
 * date: 2016/9/26.
 */
public class UnderwayPaymentFragment extends BaseFragment implements View.OnClickListener {
    private XListView listview;//
    private OrderAdapter orderAdapter;
    private List<AttacheOrderListResp> attacheOrderLists;
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
        orderAdapter = new OrderAdapter(getParentFragment().getActivity(), 2, handler);
        listview.setAdapter(orderAdapter);
        listview.setPullLoadEnable(false);
        getHttpData(true, false, 1);
    }

    private void getHttpData(boolean isFirst, boolean isShowProgress, int what) {
        int count = isFirst == true ? 0 : orderAdapter.getDataList().size();
        new AttacheOrderListHttp(getParentFragment().getActivity(), handler,count, 2, what, isShowProgress);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((OrderFragment) getParentFragment()).refreshFlag) {
            ((OrderFragment) getParentFragment()).refreshFlag = false;
            getHttpData(true, false, 1);
        }
    }

    @Override
    protected void clossRefresh() {
        super.clossRefresh();
        listview.aotuRefreshComplete();
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void initEvent() {

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i <= 0) {
                    return;
                }
                Intent intent = new Intent(getParentFragment().getActivity(), AttacheOrderDetailsActivity.class);
                intent.putExtra(IntentFinal.INTENT_ATTACHE_ORDER_LIST, orderAdapter.getDataList().get(i - 1).getOrder_id());
                AppLog.D("Order_id:" + orderAdapter.getDataList().get(i - 1).getOrder_id());
                startActivityForResult(intent,1);
            }
        });
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getHttpData(true, false, 1);
            }

            @Override
            public void onLoadMore() {
                getHttpData(true, false, 2);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        AppLog.D("requestCode:"+requestCode);
        getHttpData(true, false, 1);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                attacheOrderLists = (List<AttacheOrderListResp>) msg.obj;
                setData(attacheOrderLists, true);
                break;
            case 2:
                attacheOrderLists = (List<AttacheOrderListResp>) msg.obj;
                setData(attacheOrderLists, false);
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

    private void setData(List<AttacheOrderListResp> attacheOrderLists, boolean isFirst) {
        if (isFirst) {
            orderAdapter.getDataList().clear();
        }
        if (attacheOrderLists != null && attacheOrderLists.size() > 0) {
            orderAdapter.getDataList().addAll(attacheOrderLists);
            if (attacheOrderLists.size() < 10) {
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
//
//    private boolean isDelete = false;
//
//    public void deleteItem(boolean isShow) {
//        OrderFragment.flag = true;
////        isDelete = true;
////        orderAdapter.setDelete(isDelete);
////        orderAdapter.notifyDataSetChanged();
//        ToastUtils.toastLong(getParentFragment().getActivity(), R.string.order_underway_not_del);
//    }
}
