package com.qiantang.neighbourmother.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassName:服务
 * author: Cocoa
 * date: 2016/9/22.
 */
public class DowOrSerChildObj implements Parcelable {


    /**
     * service_id : 14
     * service_name : 辅导一小时
     * service_cate_id : 4
     * service_phase_id : 2
     * service_type_id : 6
     * service_money : 45
     * type : 2
     * checkable : true
     */

    private String service_id;
    private String service_name;
/**1早上送孩子2晚上接孩子3看护4辅导作业5周末看护*/
    private int service_cate_id;
    private String service_phase_id;
    /**1不行2抱行3看护4周末看护5上门看护6辅导7上门辅导8专车9拼车*/
    private String service_type_id;
    private int service_money;
    private int type;
    private boolean checkable;

    private boolean enable;
    private boolean chekced;
    private boolean showMoney=true;

    private int fdMoney;
    private boolean rule;//	是否符合优惠规则　true符合false不符合

    public DowOrSerChildObj() {
    }

    public DowOrSerChildObj(String service_type_id,String service_phase_id) {
        this.service_type_id = service_type_id;
        this.service_phase_id = service_phase_id;
    }

    public boolean isRule() {
        return rule;
    }

    public void setRule(boolean rule) {
        this.rule = rule;
    }

    public int getFdMoney() {
        return fdMoney;
    }

    public void setFdMoney(int fdMoney) {
        this.fdMoney = fdMoney;
    }

    public boolean isShowMoney() {
        return showMoney;
    }

    public void setShowMoney(boolean showMoney) {
        this.showMoney = showMoney;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isChekced() {
        return chekced;
    }

    public void setChekced(boolean chekced) {
        this.chekced = chekced;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getService_cate_id() {
        return service_cate_id;
    }

    public void setService_cate_id(int service_cate_id) {
        this.service_cate_id = service_cate_id;
    }

    public String getService_phase_id() {
        return service_phase_id;
    }

    public void setService_phase_id(String service_phase_id) {
        this.service_phase_id = service_phase_id;
    }

    public String getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(String service_type_id) {
        this.service_type_id = service_type_id;
    }

    public int getService_money() {
        return service_money;
    }

    public void setService_money(int service_money) {
        this.service_money = service_money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean getCheckable() {
        return checkable;
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.service_id);
        dest.writeString(this.service_name);
        dest.writeInt(this.service_cate_id);
        dest.writeString(this.service_phase_id);
        dest.writeString(this.service_type_id);
        dest.writeInt(this.service_money);
        dest.writeInt(this.type);
        dest.writeByte(this.checkable ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enable ? (byte) 1 : (byte) 0);
        dest.writeByte(this.chekced ? (byte) 1 : (byte) 0);
        dest.writeByte(this.showMoney ? (byte) 1 : (byte) 0);
        dest.writeInt(this.fdMoney);
        dest.writeByte(this.rule ? (byte) 1 : (byte) 0);
    }

    protected DowOrSerChildObj(Parcel in) {
        this.service_id = in.readString();
        this.service_name = in.readString();
        this.service_cate_id = in.readInt();
        this.service_phase_id = in.readString();
        this.service_type_id = in.readString();
        this.service_money = in.readInt();
        this.type = in.readInt();
        this.checkable = in.readByte() != 0;
        this.enable = in.readByte() != 0;
        this.chekced = in.readByte() != 0;
        this.showMoney = in.readByte() != 0;
        this.fdMoney = in.readInt();
        this.rule = in.readByte() != 0;
    }

    public static final Parcelable.Creator<DowOrSerChildObj> CREATOR = new Parcelable.Creator<DowOrSerChildObj>() {
        @Override
        public DowOrSerChildObj createFromParcel(Parcel source) {
            return new DowOrSerChildObj(source);
        }

        @Override
        public DowOrSerChildObj[] newArray(int size) {
            return new DowOrSerChildObj[size];
        }
    };
}
