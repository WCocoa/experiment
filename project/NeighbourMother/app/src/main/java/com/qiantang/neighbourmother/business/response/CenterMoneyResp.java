package com.qiantang.neighbourmother.business.response;

import java.io.Serializable;

/**
 * ClassName:中心金额
 * author: Cocoa
 * date: 2016/11/18.
 */

public class CenterMoneyResp extends BaseResp implements Serializable {

    /**
     * money : 800
     * card_no : 11111
     * open_bank : 22222
     * account_name : 333333
     */

    private double money;
    private String card_no;
    private String open_bank;
    private String account_name;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getOpen_bank() {
        return open_bank;
    }

    public void setOpen_bank(String open_bank) {
        this.open_bank = open_bank;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }
}
