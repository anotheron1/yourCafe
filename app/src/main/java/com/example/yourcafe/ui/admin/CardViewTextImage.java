package com.example.yourcafe.ui.admin;

public class CardViewTextImage implements CardViewType {
    String coupon;
    String descr;
    int photoId;

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getDescr() {
        return descr;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    CardViewTextImage(String coupon, int photoId, String descr) {
        this.coupon = coupon;
        this.photoId = photoId;
        this.descr = descr;
    }
}
