package com.example.yourcafe.ui.admin;

public class CardViewText implements CardViewType {
    String coupon;
    String descr;
    String condition;

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getDescr() {
        return descr;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    CardViewText(String coupon, String condition, String descr) {
        this.coupon = coupon;
        this.condition = condition;
        this.descr = descr;
    }
}
