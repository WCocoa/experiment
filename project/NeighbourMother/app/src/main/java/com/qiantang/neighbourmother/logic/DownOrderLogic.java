package com.qiantang.neighbourmother.logic;

import android.app.Activity;
import android.text.TextUtils;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.request.OrderCreateReq;
import com.qiantang.neighbourmother.business.response.AttacheDetailResp;
import com.qiantang.neighbourmother.business.response.OrderDetailResp;
import com.qiantang.neighbourmother.business.response.UserOrderListResp;
import com.qiantang.neighbourmother.model.ChoiceServReObj;
import com.qiantang.neighbourmother.model.DowOrSerChildObj;
import com.qiantang.neighbourmother.model.MapSetPointObj;
import com.qiantang.neighbourmother.model.ServTypeObj;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLTimeUtil;
import com.qiantang.neighbourmother.util.ToastUtils;

import java.util.ArrayList;

/**
 * Created by quliang on 16-11-3.
 */

public class DownOrderLogic {
private Activity activity;
    private AttacheDetailResp attacheDetailResp;//已选择的专员信息
    private ChoiceServReObj choiceServReObj;//已选择服务的结果对象
    private String choiceData;//已选择日期
    private String choiceTime;//已选择时间
    private MapSetPointObj formMapp;//从哪里接
    private MapSetPointObj toMapp;//送到哪里
    private PlusMeal plusMeal;
    private int toThisType;//0服务介绍页面进入1专员页面进入2订单列表页面进入
    private UserOrderListResp userOrderListResp;//订单列表传过来的itemObj
    private OrderDetailResp orderDetailResp;
    private OrderCreateReq orderCreateReq;
    private String address;
    private int serviceType;

    public DownOrderLogic(Activity activity) {
        this.activity = activity;
    }

    public AttacheDetailResp getAttacheDetailResp() {
        return attacheDetailResp;
    }

    public void setAttacheDetailResp(AttacheDetailResp attacheDetailResp) {
        this.attacheDetailResp = attacheDetailResp;
    }

    public ChoiceServReObj getChoiceServReObj() {
        return choiceServReObj;
    }

    public void setChoiceServReObj(ChoiceServReObj choiceServReObj) {
        this.choiceServReObj = choiceServReObj;
        plusMeal.setServMoney(choiceServReObj.getMoney());
    }

    public String getChoiceData() {
        return choiceData;
    }

    public void setChoiceData(String choiceData) {
        this.choiceData = choiceData;
    }

    public String getChoiceTime() {
        return choiceTime;
    }

    public void setChoiceTime(String choiceTime) {
        this.choiceTime = choiceTime;
    }

    public MapSetPointObj getFormMapp() {
        return formMapp;
    }

    public void setFormMapp(MapSetPointObj formMapp) {
        this.formMapp = formMapp;
    }

    public MapSetPointObj getToMapp() {
        return toMapp;
    }

    public void setToMapp(MapSetPointObj toMapp) {
        this.toMapp = toMapp;
    }

    public PlusMeal getPlusMeal() {
        return plusMeal;
    }

    public void setPlusMeal(PlusMeal plusMeal) {
        this.plusMeal = plusMeal;
        plusMeal.init();
    }

    public int getToThisType() {
        return toThisType;
    }

    public void setToThisType(int toThisType) {
        this.toThisType = toThisType;
    }

    public UserOrderListResp getUserOrderListResp() {
        return userOrderListResp;
    }

    public void setUserOrderListResp(UserOrderListResp userOrderListResp) {
        this.userOrderListResp = userOrderListResp;
    }

    public OrderDetailResp getOrderDetailResp() {
        return orderDetailResp;
    }

    /*是否给其他对象赋值**/
    public void setOrderDetailResp(OrderDetailResp orderDetailResp,boolean dispacher) {
        this.orderDetailResp = orderDetailResp;
        if(dispacher){

            attacheDetailResp=orderDetailResp.getServant();
            attacheDetailResp.setSnack(orderDetailResp.getOrder().getSnack_money());
            //选择服务
            choiceServReObj = new ChoiceServReObj();
            choiceServReObj.setIdArray(orderDetailResp.getOrder().getService_id());
            choiceServReObj.setNameArray(orderDetailResp.getOrder().getOrder_title());
            choiceServReObj.setMoney(orderDetailResp.getOrder().getService_price());
//            choiceServReObj.setMoney(orderDetailResp.getOrder().getMoney() - orderDetailResp.getOrder().getSnack()
//                    * orderDetailResp.getOrder().getSnack_money() - orderDetailResp.getOrder().getTips());

            choiceServReObj.setPhase(orderDetailResp.getOrder().getService_phase_id() - 1);

            long cd=Long.valueOf(orderDetailResp.getOrder().getStart_date());
            long ct=Long.valueOf(orderDetailResp.getOrder().getStart_time());
            choiceData=cd>0?QLTimeUtil.getStringTime(cd*1000,QLTimeUtil.TIME_MODEL_2):null;
            AppLog.D("choiceData:"+choiceData);
            choiceTime=ct>0?QLTimeUtil.getStringTime(ct*1000,QLTimeUtil.TIME_MODEL_TIME):null;

            ArrayList<DowOrSerChildObj> dowOrSerChildObjs=new ArrayList<DowOrSerChildObj>();
            for (String a:orderDetailResp.getOrder().getService_type_id()){
                dowOrSerChildObjs.add(new DowOrSerChildObj(a,String.valueOf(orderDetailResp.getOrder().getService_phase_id())));
            }
            choiceServReObj.setDowOrSerChildObjs(dowOrSerChildObjs);

            /**1早上送孩子2晚上接孩子3看护4辅导作业5周末看护*/

            if(orderDetailResp.getOrder().getService_cate_id()!=null){
                for (int id:orderDetailResp.getOrder().getService_cate_id()){
                    if (id == 1) {
                        choiceServReObj.setExistM(true);
                    }

                    if (id == 2) {
                        choiceServReObj.setExistN(true);
                    }

                    if (id == 4 ||id== 3 || id == 5) {
                        choiceServReObj.setExistNurse(true);
                    }
                }
            }

            plusMeal.setType(orderDetailResp.getOrder().getOrder_status()>2?1:0);
            plusMeal.setServMoney(choiceServReObj.getMoney());
            plusMeal.setNum(orderDetailResp.getOrder().getSnack());
            plusMeal.setExtraMoney(orderDetailResp.getOrder().getTips());

            if(orderDetailResp.getOrder().getAdditional_money() > 0
                    && orderDetailResp.getOrder().getAdditional_state() == 1){
                plusMeal.setAddMoney(orderDetailResp.getOrder().getAdditional_money());
            }

        }
    }

    public OrderCreateReq getOrderCreateReq(String js_address_start,String js_address_end,String nurse_address,String strExtra_cast,String service_require) {
        OrderCreateReq orderCreateReq = null;

        if (getAttacheDetailResp() == null || getChoiceServReObj() == null
                || getChoiceData() == null) {
            ToastUtils.toastLong(activity, R.string.service_please_finish_info);
            return orderCreateReq;
        }

        orderCreateReq = new OrderCreateReq();
        orderCreateReq.setOrder_note(service_require);
        orderCreateReq.setServant_id(attacheDetailResp.getUser_id());
        orderCreateReq.setService_id(choiceServReObj.getIdArray());

        int extraM = TextUtils.isEmpty(strExtra_cast) ? 0 : Integer.valueOf(strExtra_cast) * 100;
        orderCreateReq.setMoney(String.valueOf(plusMeal.getTotalMoney()));
        orderCreateReq.setStart_date(getChoiceData());
//        orderCreateReq.setStart_time(getChoiceTime());
        orderCreateReq.setPlace_1(js_address_start);
        orderCreateReq.setPlace_2(js_address_end);
        orderCreateReq.setPlace_3(nurse_address);
        if (getFormMapp() != null) {
            orderCreateReq.setPlace_1_lng(formMapp.getLongitude());
            orderCreateReq.setPlace_1_lat(formMapp.getLatutide());
        }
        if (getToMapp() != null) {
            orderCreateReq.setPlace_2_lng(toMapp.getLongitude());
            orderCreateReq.setPlace_2_lat(toMapp.getLatutide());
        }
        orderCreateReq.setSnack(plusMeal.getNum());

        orderCreateReq.setTips(extraM);

        orderCreateReq.setService_price(plusMeal.getServMoney());

        orderCreateReq.setOrder_title(choiceServReObj.getNameArray());
        orderCreateReq.setService_phase_id(String.valueOf(choiceServReObj.getPhase() + 1));

        return orderCreateReq;
    }

    public boolean judgeAttacheAndService() {
        int k = 0;
        if (getAttacheDetailResp() == null || getChoiceServReObj() == null ||getChoiceServReObj().getDowOrSerChildObjs()==null) return false;
AppLog.D("attacheDetailResp:"+attacheDetailResp);
        int cSize = choiceServReObj.getDowOrSerChildObjs().size();
        int aSize = attacheDetailResp.getService_tag_array().size();
        AppLog.D("cSize:" + cSize);
        AppLog.D("aSize:" + aSize);
        for (int i = 0; i < cSize; i++) {
            DowOrSerChildObj dowOrSerChildObj = getChoiceServReObj().getDowOrSerChildObjs().get(i);
            for (int j = 0; j < aSize; j++) {
                ServTypeObj servTypeObj = getAttacheDetailResp().getService_tag_array().get(j);

                if (servTypeObj.getCheckable() == 1 && servTypeObj.getService_phase_id().equals(dowOrSerChildObj.getService_phase_id())
                        && servTypeObj.getService_type_id().equals(dowOrSerChildObj.getService_type_id())) {
                    k++;
                }
            }
        }
        return k == cSize ? true : false;
    }

    public void setOrderCreateReq(OrderCreateReq orderCreateReq) {
        this.orderCreateReq = orderCreateReq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    private String getstrArray(int []array){
        StringBuilder sb=new StringBuilder("");

        if(array!=null&&array.length>0){
            int size=array.length;
            for (int i=0;i<array.length;i++)
            sb.append(array[i]+i==(size-1)?"":",");
        }
            return sb.toString();
    }
}
