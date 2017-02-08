package com.qiantang.neighbourmother.logic;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.SpannableUtils;
import com.qiantang.neighbourmother.util.ToastUtils;

/**
 * Created by quliang on 16-10-9.
 */
public class PlusMeal implements View.OnClickListener {

    private Activity activity;
    private TextView tv_pcater_price;//加餐饮价格
    private TextView pcater_subtract;//加餐减
    private TextView pcater_plus;//加餐加
    private TextView pcater_num;//加餐数量
    private TextView confirmMoney;//总共需付金额
    private TextView extra_cast;//额外花费
    private TextView        money_symbol;//额外消费符号
    //    0待支付1已支付
    private int type;

    private int snack;//单价
    private int num;

    private int calcuResult;
    private int servMoney;
    private int extraMoney;
    private int addMoney;

    private int totalMoney;

    public PlusMeal(Activity activity) {
        this.activity = activity;
    }

    public void init() {
        initEvent();
    }

    private void initEvent() {
        pcater_subtract.setOnClickListener(this);
        pcater_plus.setOnClickListener(this);
        extra_cast.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String sm = s.toString();
                money_symbol.setVisibility(TextUtils.isEmpty(sm)?View.GONE:View.VISIBLE);
                sm = TextUtils.isEmpty(sm) ? "0" : sm;
                extraMoney = Integer.valueOf(sm) * 100;
                totalCalcu();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pcater_subtract:
                if (snack <= 0) {
                    ToastUtils.toastLong(activity, R.string.downorder_please_attache);
                    return;
                }
                num--;
                plusAndSubtract();
                break;

            case R.id.pcater_plus:
                if (snack <= 0) {
                    ToastUtils.toastLong(activity, R.string.downorder_please_attache);
                    return;
                }
                num++;
                plusAndSubtract();
                break;
        }
    }

    private void plusAndSubtract() {
        if (num <= 0) {
            num = 0;
            pcater_subtract.setTextColor(activity.getResources().getColor(R.color.downOrer_plus_subtract_ll_stroke));
        } else {
            pcater_subtract.setTextColor(activity.getResources().getColor(R.color.colorAccent));
        }
        pcater_num.setText(String.valueOf(num));
        calcu();

    }

    private void calcu() {
        calcuResult = 0;
        if (snack > 0) {
            calcuResult = num * snack;
        }
        tv_pcater_price.setText(activity.getResources().getString(R.string.app_money_symbol) + calcuResult / 100);

        totalCalcu();
    }


    private void totalCalcu() {
        totalMoney = calcuResult + servMoney + extraMoney + addMoney;
        float  temp = ((float) totalMoney) / 100;
        String moneyText;
        switch (type) {
            case 0:
                moneyText = activity.getResources().getString(R.string.downorder_oay_money_suffix) + activity.getResources().getString(R.string.app_money_symbol);
//                confirmMoney.setText(moneyText + new java.text.DecimalFormat("0.00").format(temp));
                String waitPayText = moneyText + new java.text.DecimalFormat("0.00").format(temp);
//                AppLog.D("waitPayText:" + waitPayText);
                confirmMoney.setText(SpannableUtils.setTextForeground(waitPayText, moneyText.length()-1, waitPayText.length(), activity.getResources().getColor(R.color.money_color)));

                break;
            case 1:
                moneyText = activity.getResources().getString(R.string.downorder_oay_pay_money_suffix) + activity.getResources().getString(R.string.app_money_symbol);
                String inPayText = moneyText + new java.text.DecimalFormat("0.00").format(temp);
//                AppLog.D("inPayText:" + inPayText);
                confirmMoney.setText(SpannableUtils.setTextForeground(inPayText, moneyText.length()-1, inPayText.length(), activity.getResources().getColor(R.color.money_color)));
                break;
        }
    }


    public int getSnack() {
        return snack;
    }

    public void setSnack(int snack) {
        this.snack = snack;
        calcu();
    }


    public TextView getTv_pcater_price() {
        return tv_pcater_price;
    }

    public void setTv_pcater_price(TextView tv_pcater_price) {
        this.tv_pcater_price = tv_pcater_price;
    }

    public TextView getPcater_subtract() {
        return pcater_subtract;
    }

    public void setPcater_subtract(TextView pcater_subtract) {
        this.pcater_subtract = pcater_subtract;
    }

    public TextView getPcater_plus() {
        return pcater_plus;
    }

    public void setPcater_plus(TextView pcater_plus) {
        this.pcater_plus = pcater_plus;
    }

    public TextView getPcater_num() {
        return pcater_num;
    }

    public TextView getConfirmMoney() {
        return confirmMoney;
    }

    public void setConfirmMoney(TextView confirmMoney) {
        this.confirmMoney = confirmMoney;
    }

    public void setPcater_num(TextView pcater_num) {
        this.pcater_num = pcater_num;
    }

    public void setServMoney(int servMoney) {
        this.servMoney = servMoney;
        totalCalcu();
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        plusAndSubtract();
    }

    public void setExtra_cast(TextView extra_cast) {
        this.extra_cast = extra_cast;
    }

    public int getExtraMoney() {
        return extraMoney;
    }

    public void setExtraMoney(int extraMoney) {
        this.extraMoney = extraMoney;
        totalCalcu();
    }

    public int getServMoney() {
        return servMoney;
    }

    public int getAddMoney() {
        return addMoney;
    }

    public void setAddMoney(int addMoney) {
        this.addMoney = addMoney;
        totalCalcu();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TextView getMoney_symbol() {
        return money_symbol;
    }

    public void setMoney_symbol(TextView money_symbol) {
        this.money_symbol = money_symbol;
    }
}