package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.response.UserOrderListResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.QLTimeUtil;
import com.qiantang.neighbourmother.widget.CircleImageView;

import java.util.ArrayList;


/**
 * ClassName:订单列表适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class UserOrderAdapter extends BaseAdapter {
    private ArrayList<UserOrderListResp> list = new ArrayList<UserOrderListResp>();
    private LayoutInflater mLayoutInflater;
    private Activity       context;
    private int            type;
    private Handler        handler;

    public UserOrderAdapter(Activity context, int type, Handler handler) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.type = type;
        this.handler = handler;
    }

    public ArrayList<UserOrderListResp> getDataList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (getCount() <= 0 || position >= getCount())
            return null;
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_order_list, null);
            holder.delete_order_item = (RelativeLayout) convertView.findViewById(R.id.delete_order_item);
            holder.order_no = (TextView) convertView.findViewById(R.id.order_no);
            holder.order_title = (TextView) convertView.findViewById(R.id.order_title);
            holder.payment_status = (TextView) convertView.findViewById(R.id.payment_status);
            holder.attache_image = (CircleImageView) convertView.findViewById(R.id.attache_image);
            holder.attache_name = (TextView) convertView.findViewById(R.id.attache_name);
            holder.order_time = (TextView) convertView.findViewById(R.id.order_time);
            holder.payment_money_status = (TextView) convertView.findViewById(R.id.payment_money_status);
            holder.payment_money = (TextView) convertView.findViewById(R.id.payment_money);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final UserOrderListResp item = list.get(position);
//        if (isDelete) {
//            holder.delete_order_item.setVisibility(View.VISIBLE);
//            holder.delete_order_item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    ToastUtils.toastLong(context, "delete item  position:" + position);
//                    showDialog(item);
//                }
//            });
//        } else {
        holder.delete_order_item.setVisibility(View.GONE);
//        }

        holder.order_no.setText("订单编号：" + item.getOrder_no());
        holder.order_title.setText(item.getOrder_title());
        //	'0 待确认，1已确认待接单，2已接单待支付，3已支付等待服务开始　4开始服务　5服务完成　6订单取消 7订单失效',
        holder.payment_money_status.setVisibility(View.VISIBLE);
        holder.payment_money.setVisibility(View.VISIBLE);
        boolean flag = false;
        switch (item.getOrder_status()) {
            case 0:
                flag = false;
                holder.payment_status.setText(R.string.wait_affirm);
                holder.payment_money_status.setText(R.string.wait_payment);
                break;
            case 1:
                flag = false;
                holder.payment_status.setText(R.string.wait_order);
                holder.payment_money_status.setText(R.string.wait_payment);
                break;
            case 2:
                flag = true;
                holder.payment_status.setText(R.string.wait_to_pay);
                holder.payment_money_status.setText(R.string.wait_payment);
                break;
            case 3:
                flag = true;
                holder.payment_status.setText(R.string.already_pay);
                holder.payment_money_status.setText(R.string.real_payment);
                break;
            case 4:
                flag = true;
                holder.payment_status.setText(R.string.already_underway);
                holder.payment_money_status.setText(R.string.real_payment);
                break;
            case 5:
                flag = true;
                holder.payment_status.setText(R.string.already_complete);
                holder.payment_money_status.setText(R.string.real_payment);
                break;
            case 6:
                flag = true;
                holder.payment_status.setText(R.string.already_cancel);
                if (item.getOrder_state() == 1) {
                    holder.payment_money_status.setText(R.string.real_payment);
                } else {
                    holder.payment_money_status.setText(R.string.wait_payment);
                }
                break;
            case 7:
                flag = true;
                holder.payment_status.setText(R.string.already_be_defeated);
                holder.payment_money_status.setVisibility(View.INVISIBLE);
                holder.payment_money.setVisibility(View.INVISIBLE);
                break;
        }
        ((BaseActivity) context).display(holder.attache_image, APIConfig.BASE_IMG_URL + item.getAvatar(), R.mipmap.default_img);
        if (flag) {
            holder.attache_name.setText(item.getName());
        } else {
            if (!TextUtils.isEmpty(item.getName()) && item.getName().length() > 1) {
                holder.attache_name.setText(((BaseActivity) context).nameModify(item.getName()));

//                if (item.getGender() == 1) {
//                    String name = item.getName().substring(0, 1) + "爸爸";
//                    holder.attache_name.setText(name);
//                } else {
//                    String name = item.getName().substring(0, 1) + "妈妈";
//                    holder.attache_name.setText(name);
//                }
            } else {
                holder.attache_name.setText(item.getName());
            }
        }
//        double money;
//        if (item.getAdditional_money() > 0 && item.getAdditional_state() == 1) {
//            money = item.getMoney() + item.getAdditional_money();
//        } else {
//            money = item.getMoney();
//        }
        holder.payment_money.setText("￥" + String.format("%1.2f", item.getMoney() / 100.0));
        if (TextUtils.isEmpty(item.getCtime()) || item.getCtime().equals("0")) {
            holder.order_time.setText("");
        } else {
            holder.order_time.setText(QLTimeUtil.getStringTime(Long.parseLong(item.getCtime()) * 1000, "yyyy-MM-dd HH:mm"));
        }
        return convertView;
    }

//    private String order_id;
//
//    public String getOrder_id() {
//        return order_id;
//    }
//
//    public void setOrder_id(String order_id) {
//        this.order_id = order_id;
//    }
//
//    DeleteOrderDialog deleteOrderDialog;
//
//    private void showDialog(final UserOrderListResp userOrderListResp) {
//        if (deleteOrderDialog == null) {
//            deleteOrderDialog = new DeleteOrderDialog();
//        }
//        deleteOrderDialog.show(((BaseActivity) context).getSupportFragmentManager(), "DeleteOrderDialog");
//        deleteOrderDialog.setOnSureListener(new DeleteOrderDialog.DeleteOrderListener() {
//            @Override
//            public void onSure() {
//                if (!TextUtils.isEmpty(userOrderListResp.getOrder_id())) {
//                    setOrder_id(userOrderListResp.getOrder_id());
//                    new UserDeleteOrderHttp(context, handler, API.USER_ORDER_DELETE, userOrderListResp.getOrder_id(), 3, true);
//                } else {
//                    isDelete = false;
//                }
//                deleteOrderDialog.dismiss();
//                OrderFragment.flag = true;
//            }
//
//            @Override
//            public void onCancel() {
//                deleteOrderDialog.dismiss();
//                ToastUtils.toastLong(context, R.string.cancel_delete_order);
//                isDelete = false;
//                OrderFragment.flag = true;
//                notifyDataSetChanged();
//            }
//        });
//    }

    class ViewHolder {
        TextView        order_no;//订单号
        TextView        payment_status;//订单状态
        TextView        order_title;//订单标题
        CircleImageView attache_image;//图像
        TextView        attache_name;//姓名
        TextView        order_time;//提交订单时间
        TextView        payment_money_status;//支付金额状态
        TextView        payment_money;//支付金额
        RelativeLayout  delete_order_item;//删除订单

    }

//    private boolean isDelete = false;
//
//    public void setDelete(boolean isDelete) {
//        this.isDelete = isDelete;
//    }
}
