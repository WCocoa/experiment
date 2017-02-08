package com.qiantang.neighbourmother.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by next on 2016/3/24.
 */
public class ChoiceServReObj implements Parcelable {

    private String idArray;
    private String nameArray;
    private int money;//单位分
    private int phase;
    /*是否存在看护*/
    private boolean existNurse;
    /*有晚上送没早上接*/
    private boolean existN;
    /*只有早上接*/
    private boolean existM;

    private ArrayList<DowOrSerChildObj> dowOrSerChildObjs;

    public ChoiceServReObj() {
    }

    public ChoiceServReObj(String idArray, String nameArray, int money, int phase,ArrayList<DowOrSerChildObj> dowOrSerChildObjs,boolean existNurse) {
        this.idArray = idArray;
        this.nameArray = nameArray;
        this.money = money;
        this.phase = phase;
        this.dowOrSerChildObjs = dowOrSerChildObjs;
        this.existNurse = existNurse;
    }

    public ArrayList<DowOrSerChildObj> getDowOrSerChildObjs() {
        return dowOrSerChildObjs;
    }

    public void setDowOrSerChildObjs(ArrayList<DowOrSerChildObj> dowOrSerChildObjs) {
        this.dowOrSerChildObjs = dowOrSerChildObjs;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getIdArray() {
        return idArray;
    }

    public void setIdArray(String idArray) {
        this.idArray = idArray;
    }

    public String getNameArray() {
        return nameArray;
    }

    public void setNameArray(String nameArray) {
        this.nameArray = nameArray;
    }

    public boolean isExistNurse() {
        return existNurse;
    }

    public void setExistNurse(boolean existNurse) {
        this.existNurse = existNurse;
    }

    public boolean isExistN() {
        return existN;
    }

    public void setExistN(boolean existN) {
        this.existN = existN;
    }

    public boolean isExistM() {
        return existM;
    }

    public void setExistM(boolean existM) {
        this.existM = existM;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idArray);
        dest.writeString(this.nameArray);
        dest.writeInt(this.money);
        dest.writeInt(this.phase);
        dest.writeByte(this.existNurse ? (byte) 1 : (byte) 0);
        dest.writeByte(this.existN ? (byte) 1 : (byte) 0);
        dest.writeByte(this.existM ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.dowOrSerChildObjs);
    }

    protected ChoiceServReObj(Parcel in) {
        this.idArray = in.readString();
        this.nameArray = in.readString();
        this.money = in.readInt();
        this.phase = in.readInt();
        this.existNurse = in.readByte() != 0;
        this.existN = in.readByte() != 0;
        this.existM = in.readByte() != 0;
        this.dowOrSerChildObjs = in.createTypedArrayList(DowOrSerChildObj.CREATOR);
    }

    public static final Parcelable.Creator<ChoiceServReObj> CREATOR = new Parcelable.Creator<ChoiceServReObj>() {
        @Override
        public ChoiceServReObj createFromParcel(Parcel source) {
            return new ChoiceServReObj(source);
        }

        @Override
        public ChoiceServReObj[] newArray(int size) {
            return new ChoiceServReObj[size];
        }
    };
}
