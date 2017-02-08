package com.qiantang.neighbourmother.logic;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.adapter.ChoiceServiceAdapter;
import com.qiantang.neighbourmother.model.DowOrSerChildObj;
import com.qiantang.neighbourmother.model.DowOrSerObj;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.widget.FixationHeightListView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by quliang on 16-10-9.
 */

public class ChoiceServLogic implements ChoiceServiceAdapter.CheckEnable {

    private Activity activity;

    private ChoiceServiceAdapter morningAdapter;//早上送孩子适配器
    private ChoiceServiceAdapter nightAdapter;//晚上接孩子适配器
    private ChoiceServiceAdapter nurseAdapter;//看护孩子适配器
    private ChoiceServiceAdapter counsellingGradeAdapter;//辅导作业 选择年级适配器
    private ChoiceServiceAdapter counsellingTimeAdapter;//辅导作业 选择辅导时长适配器
    private ChoiceServiceAdapter weekendAdapter;//周末看护适配器

    private FixationHeightListView list_morning;//早上送孩子
    private FixationHeightListView list_night;//晚上接孩子
    private FixationHeightListView list_nurse;//看护孩子
    private FixationHeightListView list_counselling_grade;//辅导作业 选择年级
    private FixationHeightListView list_counselling_time;//辅导作业 选择辅导时长
    private FixationHeightListView list_weekend;//周末看护适配器

    private TextView ser_label1;
    private TextView ser_label2;
    private TextView ser_label3;
    private TextView ser_label4;
    private TextView ser_label5;

    private LinearLayout ll_morning;
    private LinearLayout ll_night;
    private LinearLayout ll_nurse;
    private LinearLayout ll_counselling;
    private LinearLayout ll_weekend;
    private LinearLayout ll_service_content;

    public LinearLayout getLl_service_content() {
        return ll_service_content;
    }

    public void setLl_service_content(LinearLayout ll_service_content) {
        this.ll_service_content = ll_service_content;
    }

    public LinearLayout getLl_morning() {
        return ll_morning;
    }

    public void setLl_morning(LinearLayout ll_morning) {
        this.ll_morning = ll_morning;
    }

    public LinearLayout getLl_night() {
        return ll_night;
    }

    public void setLl_night(LinearLayout ll_night) {
        this.ll_night = ll_night;
    }

    public LinearLayout getLl_nurse() {
        return ll_nurse;
    }

    public void setLl_nurse(LinearLayout ll_nurse) {
        this.ll_nurse = ll_nurse;
    }

    public LinearLayout getLl_counselling() {
        return ll_counselling;
    }

    public void setLl_counselling(LinearLayout ll_counselling) {
        this.ll_counselling = ll_counselling;
    }

    public LinearLayout getLl_weekend() {
        return ll_weekend;
    }

    public void setLl_weekend(LinearLayout ll_weekend) {
        this.ll_weekend = ll_weekend;
    }

    private TextView total_money;//服务费用

    private int calcuMoney;

    /**
     * 被选中项中是否有看护服务
     */
    private boolean isNurse;

    private ArrayList<DowOrSerChildObj> listCacu = new ArrayList<DowOrSerChildObj>();

    public ChoiceServLogic(Activity activity) {
        this.activity = activity;
    }

    public void init() {
        initData();
        initEvent();
    }

    private void initData() {
        morningAdapter = new ChoiceServiceAdapter(activity);
        nightAdapter = new ChoiceServiceAdapter(activity);
        nurseAdapter = new ChoiceServiceAdapter(activity);
        counsellingGradeAdapter = new ChoiceServiceAdapter(activity);
        counsellingTimeAdapter = new ChoiceServiceAdapter(activity);
        counsellingTimeAdapter.setFd(true);
        counsellingTimeAdapter.setEnable(false);
        weekendAdapter = new ChoiceServiceAdapter(activity);

        list_morning.setAdapter(morningAdapter);
        list_night.setAdapter(nightAdapter);
        list_nurse.setAdapter(nurseAdapter);
        list_counselling_grade.setAdapter(counsellingGradeAdapter);
        list_counselling_time.setAdapter(counsellingTimeAdapter);
        list_weekend.setAdapter(weekendAdapter);
    }

    private void initEvent() {
        counsellingGradeAdapter.setCheckEnableListener(this);

        list_morning.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DowOrSerChildObj dowOrSerChildObj = morningAdapter.getDataList().get(position);
                if (morningAdapter.isEnable()) {
                    boolean bo = !dowOrSerChildObj.isChekced();
                    if (bo) {
                        for (DowOrSerChildObj dowOrSerChildObj1 : morningAdapter.getDataList()) {
                            dowOrSerChildObj1.setChekced(false);
                        }

                        morningAdapter.setCheckedIndex(position);
                    }
                    dowOrSerChildObj.setChekced(bo);
                    morningAdapter.setChecked(dowOrSerChildObj.isChekced());
                    controler(1, dowOrSerChildObj.isChekced());
                }
            }
        });

        list_night.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DowOrSerChildObj dowOrSerChildObj = nightAdapter.getDataList().get(position);
                if (nightAdapter.isEnable()) {
                    boolean bo = !dowOrSerChildObj.isChekced();
                    if (bo) {
                        for (DowOrSerChildObj dowOrSerChildObj1 : nightAdapter.getDataList()) {
                            dowOrSerChildObj1.setChekced(false);
                        }
                        nightAdapter.setCheckedIndex(position);
                    }
                    dowOrSerChildObj.setChekced(bo);
                    nightAdapter.setChecked(dowOrSerChildObj.isChekced());
                    controler(2, dowOrSerChildObj.isChekced());
                }
            }
        });

        list_nurse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DowOrSerChildObj dowOrSerChildObj = nurseAdapter.getDataList().get(position);
                if (nurseAdapter.isEnable()) {
                    boolean bo = !dowOrSerChildObj.isChekced();
                    if (bo) {
                        for (DowOrSerChildObj dowOrSerChildObj1 : nurseAdapter.getDataList()) {
                            dowOrSerChildObj1.setChekced(false);
                        }
                        nurseAdapter.setCheckedIndex(position);
                    }
                    dowOrSerChildObj.setChekced(bo);
                    nurseAdapter.setChecked(dowOrSerChildObj.isChekced());
                    controler(3, dowOrSerChildObj.isChekced());
                }
            }
        });

        list_counselling_grade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DowOrSerChildObj dowOrSerChildObj = counsellingGradeAdapter.getDataList().get(position);
                if (counsellingGradeAdapter.isEnable()) {
                    boolean bo = !dowOrSerChildObj.isChekced();
                    if (bo) {
                        for (DowOrSerChildObj dowOrSerChildObj1 : counsellingGradeAdapter.getDataList()) {
                            dowOrSerChildObj1.setChekced(false);
                        }
                        counsellingGradeAdapter.setCheckedIndex(position);
                    }
                    dowOrSerChildObj.setChekced(bo);
                    counsellingGradeAdapter.setChecked(dowOrSerChildObj.isChekced());
                    controler(4, dowOrSerChildObj.isChekced());
                }
            }
        });

        list_counselling_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DowOrSerChildObj dowOrSerChildObj = counsellingTimeAdapter.getDataList().get(position);
                if (counsellingTimeAdapter.isEnable()) {
                    boolean bo = !dowOrSerChildObj.isChekced();
                    if (bo) {
                        for (DowOrSerChildObj dowOrSerChildObj1 : counsellingTimeAdapter.getDataList()) {
                            dowOrSerChildObj1.setChekced(false);
                        }
                        counsellingTimeAdapter.setCheckedIndex(position);
                    }
                    dowOrSerChildObj.setChekced(bo);
                    counsellingTimeAdapter.setChecked(dowOrSerChildObj.isChekced());
                    controler(5, dowOrSerChildObj.isChekced());
                }
            }
        });

        list_weekend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DowOrSerChildObj dowOrSerChildObj = weekendAdapter.getDataList().get(position);
                if (weekendAdapter.isEnable()) {
                    boolean bo = !dowOrSerChildObj.isChekced();
                    if (bo) {
                        for (DowOrSerChildObj dowOrSerChildObj1 : weekendAdapter.getDataList()) {
                            dowOrSerChildObj1.setChekced(false);
                        }
                        weekendAdapter.setCheckedIndex(position);
                    }
                    dowOrSerChildObj.setChekced(bo);
                    weekendAdapter.setChecked(dowOrSerChildObj.isChekced());
                    controler(6, dowOrSerChildObj.isChekced());
                }
            }
        });
    }

    public void setData(ArrayList<DowOrSerObj> dowOrSerObjs) {

        if (dowOrSerObjs != null && dowOrSerObjs.size() > 0) {
            ll_service_content.setVisibility(View.VISIBLE);
        } else {
            ll_service_content.setVisibility(View.GONE);
        }
        for (DowOrSerObj dowOrSerObj : dowOrSerObjs) {
            setAdapterData(dowOrSerObj);
        }

    }

    private void setAdapterData(DowOrSerObj dowOrSerObj) {
        switch (dowOrSerObj.getType()) {
            case 1:
                ser_label1.setText(dowOrSerObj.getService_cate_name());
                morningAdapter.getDataList().clear();
                morningAdapter.getDataList().addAll(dowOrSerObj.getData());
                changeView(morningAdapter, ll_morning);
                morningAdapter.notifyDataSetChanged();
                break;
            case 2:
                ser_label2.setText(dowOrSerObj.getService_cate_name());
                nightAdapter.getDataList().clear();
                nightAdapter.getDataList().addAll(dowOrSerObj.getData());
                changeView(nightAdapter, ll_night);
                nightAdapter.notifyDataSetChanged();
                break;
            case 3:
                ser_label3.setText(dowOrSerObj.getService_cate_name());
                nurseAdapter.getDataList().clear();
                nurseAdapter.getDataList().addAll(dowOrSerObj.getData());
                changeView(nurseAdapter, ll_nurse);
                nurseAdapter.notifyDataSetChanged();
                break;
            case 4:
                ser_label4.setText(dowOrSerObj.getService_cate_name());
                counsellingGradeAdapter.getDataList().clear();
                counsellingGradeAdapter.getDataList().addAll(dowOrSerObj.getData());
                counsellingGradeAdapter.notifyDataSetChanged();
                break;
            case 5:
                counsellingTimeAdapter.getDataList().clear();
                counsellingTimeAdapter.getDataList().addAll(dowOrSerObj.getData());
                counsellingTimeAdapter.notifyDataSetChanged();
                break;
            case 6:
                ser_label5.setText(dowOrSerObj.getService_cate_name());
                weekendAdapter.getDataList().clear();
                weekendAdapter.getDataList().addAll(dowOrSerObj.getData());
                changeView(weekendAdapter, ll_weekend);
                weekendAdapter.notifyDataSetChanged();
                break;
        }
        if ((counsellingGradeAdapter.getDataList() != null && counsellingGradeAdapter.getDataList().size() > 0) && (counsellingTimeAdapter.getDataList() != null && counsellingTimeAdapter.getDataList().size() > 0)) {
            ll_counselling.setVisibility(View.VISIBLE);
        } else {
            ll_counselling.setVisibility(View.GONE);
        }
    }

    private void changeView(ChoiceServiceAdapter adapter, LinearLayout ll_liner) {
        if (adapter.getDataList() != null && adapter.getDataList().size() > 0) {
            ll_liner.setVisibility(View.VISIBLE);
        } else {
            ll_liner.setVisibility(View.GONE);
        }
    }

    private void controler(int type, boolean checked) {
        switch (type) {
//            早上送
            case 1:

                AppLog.D("nightAdapter.isChecked():" + nightAdapter.isChecked());
                AppLog.D("morningAdapter.isChecked():" + morningAdapter.isChecked());
                if (checked) {

                    if (!nightAdapter.isChecked()) {
                        nurseAdapter.setEnable(false);
                        counsellingGradeAdapter.setEnable(false);
                    }
                    weekendAdapter.setEnable(false);
                } else {
                    if (!nightAdapter.isChecked()) {
                        nurseAdapter.setEnable(true);
                        counsellingGradeAdapter.setEnable(true);
                        weekendAdapter.setEnable(true);
                    }
                }
                break;
//            晚上接
            case 2:
                AppLog.D("nurseAdapter.isChecked():" + nurseAdapter.isChecked());
                AppLog.D("morningAdapter.isChecked():" + morningAdapter.isChecked());
                if (checked) {

                    if (morningAdapter.isChecked()) {
                        nurseAdapter.setEnable(true);
                        counsellingGradeAdapter.setEnable(true);
                    }

                    morningAdapter.setEnable(true);
                    weekendAdapter.setEnable(false);
                } else {

                    if ((nurseAdapter.isChecked() || counsellingGradeAdapter.isChecked())) {
                        morningAdapter.setEnable(false);
                    }
                    if ((!nurseAdapter.isChecked() && !counsellingGradeAdapter.isChecked()) && !morningAdapter.isChecked()) {
                        weekendAdapter.setEnable(true);
                    }
                }

                break;
//            看护
            case 3:
                if (checked) {
                    counsellingGradeAdapter.setEnable(false);
//                    counsellingTimeAdapter.setEnable(false);
                    weekendAdapter.setEnable(false);
                    if (!nightAdapter.isChecked()) {
                        morningAdapter.setEnable(false);
                    }
                } else {

                    AppLog.D("morningAdapter.isChecked():" + morningAdapter.isChecked());
                    AppLog.D("nightAdapter.isChecked():" + nightAdapter.isChecked());

                    if (!morningAdapter.isChecked() && !nightAdapter.isChecked()) {
                        weekendAdapter.setEnable(true);
                    }
                    morningAdapter.setEnable(true);
                    counsellingGradeAdapter.setEnable(true);
                }

                break;
//            辅导作业左
            case 4:
                if (checked) {
                    nurseAdapter.setEnable(false);
                    weekendAdapter.setEnable(false);
                    if (!nightAdapter.isChecked()) {
                        morningAdapter.setEnable(false);
                    }
                    counsellingTimeAdapter.setEnable(true);

                    calcuRightData();
                } else {

                    if (!morningAdapter.isChecked() && !nightAdapter.isChecked()) {
                        morningAdapter.setEnable(true);
                        weekendAdapter.setEnable(true);
                    } else if (!morningAdapter.isChecked() && nightAdapter.isChecked()) {
                        morningAdapter.setEnable(true);
                    }
                    nurseAdapter.setEnable(true);

//                    清空辅导2选项并设置为不可用
                    counsellingTimeAdapter.setEnable(false);
                }

                break;
//            辅导作业右
            case 5:
                if (checked) {
                    calcuRightData();
                }

                break;
//            周末看护
            case 6:
                if (checked) {
                    morningAdapter.setEnable(false);
                    nightAdapter.setEnable(false);
                    nurseAdapter.setEnable(false);
                    counsellingGradeAdapter.setEnable(false);

                } else {
                    morningAdapter.setEnable(true);
                    nightAdapter.setEnable(true);
                    nurseAdapter.setEnable(true);
                    counsellingGradeAdapter.setEnable(true);

                }
                break;
        }

        morningAdapter.notifyDataSetChanged();
        nightAdapter.notifyDataSetChanged();
        nurseAdapter.notifyDataSetChanged();
        counsellingGradeAdapter.notifyDataSetChanged();
        counsellingTimeAdapter.notifyDataSetChanged();
        weekendAdapter.notifyDataSetChanged();

        calcu();
    }

    /**
     * 计算辅导右边列表money
     */
    private void calcuRightData() {
        if (counsellingTimeAdapter.getCheckedIndex() != -1) {
            DowOrSerChildObj param = counsellingGradeAdapter.getDataList().get(counsellingGradeAdapter.getCheckedIndex());
            DowOrSerChildObj dowOrSerChildObj = counsellingTimeAdapter.getDataList().get(counsellingTimeAdapter.getCheckedIndex());
            dowOrSerChildObj.setFdMoney(param.getService_money() * dowOrSerChildObj.getService_money());
        }
    }

    @Override
    public void onCheck(boolean enable) {
        counsellingTimeAdapter.setEnable(enable);
        AppLog.D("enable:" + enable);
    }

    private void calcu() {
        listCacu.clear();
        int tempMoney = 0;
        int jsToal = 0;//接送总价 留作免费接送优惠使用
        DowOrSerChildObj dowOrSerChildObj;
        int nurse = 0;

        if (morningAdapter.isEnable() && morningAdapter.isChecked() && morningAdapter.getCheckedIndex() >= 0) {
            dowOrSerChildObj = morningAdapter.getDataList().get(morningAdapter.getCheckedIndex());
            listCacu.add(dowOrSerChildObj);
            jsToal = dowOrSerChildObj.getService_money();
            tempMoney = tempMoney + dowOrSerChildObj.getService_money();
        }

        if (nightAdapter.isEnable() && nightAdapter.isChecked() && nightAdapter.getCheckedIndex() >= 0) {
            dowOrSerChildObj = nightAdapter.getDataList().get(nightAdapter.getCheckedIndex());
            listCacu.add(dowOrSerChildObj);
            jsToal = jsToal + dowOrSerChildObj.getService_money();
            tempMoney = tempMoney + dowOrSerChildObj.getService_money();
        }

        if (nurseAdapter.isEnable() && nurseAdapter.isChecked() && nurseAdapter.getCheckedIndex() >= 0) {
            dowOrSerChildObj = nurseAdapter.getDataList().get(nurseAdapter.getCheckedIndex());
            if (dowOrSerChildObj.isRule()) {
                tempMoney = tempMoney - jsToal;
            }
            listCacu.add(dowOrSerChildObj);
            tempMoney = tempMoney + dowOrSerChildObj.getService_money();
            nurse++;
        }

        if (weekendAdapter.isEnable() && weekendAdapter.isChecked() && weekendAdapter.getCheckedIndex() >= 0) {
            dowOrSerChildObj = weekendAdapter.getDataList().get(weekendAdapter.getCheckedIndex());
            if (dowOrSerChildObj.isRule()) {
                tempMoney = tempMoney - jsToal;
            }
            listCacu.add(dowOrSerChildObj);
            tempMoney = tempMoney + dowOrSerChildObj.getService_money();
            nurse++;
        }

        if (counsellingGradeAdapter.isEnable() && counsellingGradeAdapter.isChecked() && counsellingGradeAdapter.getCheckedIndex() >= 0) {
            dowOrSerChildObj = counsellingGradeAdapter.getDataList().get(counsellingGradeAdapter.getCheckedIndex());
            listCacu.add(dowOrSerChildObj);


        }

        if (counsellingTimeAdapter.isEnable() && counsellingTimeAdapter.isChecked() && counsellingTimeAdapter.getCheckedIndex() >= 0) {
            dowOrSerChildObj = counsellingTimeAdapter.getDataList().get(counsellingTimeAdapter.getCheckedIndex());
            listCacu.add(dowOrSerChildObj);
            if (dowOrSerChildObj.isRule()) {
                tempMoney = tempMoney - jsToal;
            }
            tempMoney = tempMoney + dowOrSerChildObj.getFdMoney();
            nurse++;
        }

        calcuMoney = tempMoney;

        total_money.setText("￥" + getFormatMoney());

        isNurse = nurse > 0 ? true : false;

    }

    private String getFormatMoney() {
        float fmoney = ((float) calcuMoney) / 100;
        return new DecimalFormat("0.00").format(fmoney);
    }


    public ChoiceServiceAdapter getMorningAdapter() {
        return morningAdapter;
    }

    public void setMorningAdapter(ChoiceServiceAdapter morningAdapter) {
        this.morningAdapter = morningAdapter;
    }

    public ChoiceServiceAdapter getNightAdapter() {
        return nightAdapter;
    }

    public void setNightAdapter(ChoiceServiceAdapter nightAdapter) {
        this.nightAdapter = nightAdapter;
    }

    public ChoiceServiceAdapter getNurseAdapter() {
        return nurseAdapter;
    }

    public void setNurseAdapter(ChoiceServiceAdapter nurseAdapter) {
        this.nurseAdapter = nurseAdapter;
    }

    public ChoiceServiceAdapter getCounsellingGradeAdapter() {
        return counsellingGradeAdapter;
    }

    public void setCounsellingGradeAdapter(ChoiceServiceAdapter counsellingGradeAdapter) {
        this.counsellingGradeAdapter = counsellingGradeAdapter;
    }

    public ChoiceServiceAdapter getCounsellingTimeAdapter() {
        return counsellingTimeAdapter;
    }

    public void setCounsellingTimeAdapter(ChoiceServiceAdapter counsellingTimeAdapter) {
        this.counsellingTimeAdapter = counsellingTimeAdapter;
    }

    public ChoiceServiceAdapter getWeekendAdapter() {
        return weekendAdapter;
    }

    public void setWeekendAdapter(ChoiceServiceAdapter weekendAdapter) {
        this.weekendAdapter = weekendAdapter;
    }

    public FixationHeightListView getList_morning() {
        return list_morning;
    }

    public void setList_morning(FixationHeightListView list_morning) {
        this.list_morning = list_morning;
    }

    public FixationHeightListView getList_night() {
        return list_night;
    }

    public void setList_night(FixationHeightListView list_night) {
        this.list_night = list_night;
    }

    public FixationHeightListView getList_nurse() {
        return list_nurse;
    }

    public void setList_nurse(FixationHeightListView list_nurse) {
        this.list_nurse = list_nurse;
    }

    public FixationHeightListView getList_counselling_grade() {
        return list_counselling_grade;
    }

    public void setList_counselling_grade(FixationHeightListView list_counselling_grade) {
        this.list_counselling_grade = list_counselling_grade;
    }

    public FixationHeightListView getList_counselling_time() {
        return list_counselling_time;
    }

    public void setList_counselling_time(FixationHeightListView list_counselling_time) {
        this.list_counselling_time = list_counselling_time;
    }

    public FixationHeightListView getList_weekend() {
        return list_weekend;
    }

    public void setList_weekend(FixationHeightListView list_weekend) {
        this.list_weekend = list_weekend;
    }

    public TextView getTotal_money() {
        return total_money;
    }

    public void setTotal_money(TextView total_money) {
        this.total_money = total_money;
    }

    public int getCalcuMoney() {
        return calcuMoney;
    }

    public ArrayList<DowOrSerChildObj> getList() {
        return listCacu;
    }

    public boolean isNurse() {
        return isNurse;
    }

    public TextView getSer_label1() {
        return ser_label1;
    }

    public void setSer_label1(TextView ser_label1) {
        this.ser_label1 = ser_label1;
    }

    public TextView getSer_label2() {
        return ser_label2;
    }

    public void setSer_label2(TextView ser_label2) {
        this.ser_label2 = ser_label2;
    }

    public TextView getSer_label3() {
        return ser_label3;
    }

    public void setSer_label3(TextView ser_label3) {
        this.ser_label3 = ser_label3;
    }

    public TextView getSer_label4() {
        return ser_label4;
    }

    public void setSer_label4(TextView ser_label4) {
        this.ser_label4 = ser_label4;
    }

    public TextView getSer_label5() {
        return ser_label5;
    }

    public void setSer_label5(TextView ser_label5) {
        this.ser_label5 = ser_label5;
    }
}