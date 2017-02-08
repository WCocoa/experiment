package com.qiantang.neighbourmother.ui.order.attache;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.OrderAdapter;
import com.qiantang.neighbourmother.business.data.AttacheOrderListHttp;
import com.qiantang.neighbourmother.business.data.UserDeleteOrderHttp;
import com.qiantang.neighbourmother.business.response.AttacheOrderListResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.BaseFragment;
import com.qiantang.neighbourmother.ui.dialog.DeleteOrderDialog;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

import java.util.List;

/**
 * ClassName:专员订单，待支付
 * author: Cocoa
 * date: 2016/9/26.
 */
public class WaitPaymentFragment extends BaseFragment implements View.OnClickListener {
    private XListView                  listview;//
    private OrderAdapter               orderAdapter;
    private List<AttacheOrderListResp> attacheOrderLists;
    private RelativeLayout             rl_default_page;//没有数据默认页面

    @Override
    public int getContentView() {
        return R.layout.fragment_wait_payment;
    }

    @Override
    public void initView(View view) {
        listview = (XListView) view.findViewById(R.id.listview);
        rl_default_page = (RelativeLayout) view.findViewById(R.id.rl_default_page);
    }

    @Override
    public void initData() {
        orderAdapter = new OrderAdapter(getParentFragment().getActivity(), 1, handler);
        listview.setAdapter(orderAdapter);
        listview.setPullLoadEnable(false);
        getHttpData(true, true, 1);

    }

    private void getHttpData(boolean isFirst, boolean isShowProgress, int what) {
        int count = isFirst == true ? 0 : orderAdapter.getDataList().size();
        new AttacheOrderListHttp(getParentFragment().getActivity(), handler, count, 1, what, isShowProgress);
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
    public void initEvent() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i <= 0) {
                    return;
                }
//                if (isDelete) {
//
////                    isDelete = false;
//
////                    orderAdapter.setDelete(isDelete);
//                    orderAdapter.notifyDataSetChanged();
//                } else {

                Intent intent = new Intent(getParentFragment().getActivity(), AttacheOrderDetailsActivity.class);
                intent.putExtra(IntentFinal.INTENT_ATTACHE_ORDER_LIST, orderAdapter.getDataList().get(i - 1).getOrder_id());
                AppLog.D("Order_id:" + orderAdapter.getDataList().get(i - 1).getOrder_id());
                startActivityForResult(intent, 2);
//                }
//                OrderFragment.flag = true;
            }
        });
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
//                isDelete = false;
//                orderAdapter.setDelete(isDelete);
//                OrderFragment.flag = true;
                getHttpData(true, false, 1);
            }

            @Override
            public void onLoadMore() {
//                isDelete = false;
//                orderAdapter.setDelete(isDelete);
//                OrderFragment.flag = true;
                getHttpData(false, false, 2);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    showDialog(orderAdapter.getDataList().get(position - 1));
                    return true;
                } else {

                    return false;
                }
            }
        });
    }

    DeleteOrderDialog    deleteOrderDialog;
    AttacheOrderListResp order;

    private void showDialog(final AttacheOrderListResp attacheOrderListResp) {
        order = new AttacheOrderListResp();
        if (deleteOrderDialog == null) {
            deleteOrderDialog = new DeleteOrderDialog();
        }
        deleteOrderDialog.show(((BaseActivity) getParentFragment().getActivity()).getSupportFragmentManager(), "DeleteOrderDialog");
        deleteOrderDialog.setOnSureListener(new DeleteOrderDialog.DeleteOrderListener() {
            @Override
            public void onSure() {
                if (!TextUtils.isEmpty(attacheOrderListResp.getOrder_id())) {
                    order.setOrder_id(attacheOrderListResp.getOrder_id());
                    new UserDeleteOrderHttp(getActivity(), handler, false,attacheOrderListResp.getOrder_id(), 3, true);
                }
                deleteOrderDialog.dismiss();
            }

            @Override
            public void onCancel() {
                deleteOrderDialog.dismiss();
//                ToastUtils.toastshort(getActivity(), R.string.cancel_delete_order);
//                OrderFragment.flag = true;
//                isDelete = false;
//                notifyDataSetChanged();
            }
        });
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
            case 3:
                int pos = orderAdapter.getDataList().size();
                for (int i = 0; i < pos; i++) {
                    AttacheOrderListResp attacheOrderLists = orderAdapter.getDataList().get(i);
                    if (order.getOrder_id().equals(attacheOrderLists.getOrder_id())) {
                        orderAdapter.getDataList().remove(i);
                        break;
                    }
                }
//                isDelete = false;
//                orderAdapter.setDelete(isDelete);
//                OrderFragment.flag = true;
                orderAdapter.notifyDataSetChanged();
                ToastUtils.toastshort(getParentFragment().getActivity(), R.string.delete_order_success);
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

    @Override
    protected void clossRefresh() {
        super.clossRefresh();
//        isDelete = false;
        listview.aotuRefreshComplete();
//        orderAdapter.setDelete(isDelete);
//        OrderFragment.flag = true;
        orderAdapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        AppLog.D("requestCode:" + requestCode);
        getHttpData(true, false, 1);
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

//    private boolean isDelete = false;
//
//    public void deleteItem(boolean isShow) {
//        if (isShow) {
//            isDelete = false;
//            orderAdapter.setDelete(isDelete);
//            orderAdapter.notifyDataSetChanged();
//        } else {
//            isDelete = true;
//            orderAdapter.setDelete(isDelete);
//            orderAdapter.notifyDataSetChanged();
//        }
//    }
}
