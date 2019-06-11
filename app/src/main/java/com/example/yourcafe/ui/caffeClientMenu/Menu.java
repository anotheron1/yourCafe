package com.example.yourcafe.ui.caffeClientMenu;

public class Menu {
    private String id;
    private String client_id;
    private String client_qr;
    private String caffe_id;
    private String all_cup;
    private String fill_cup;

    public String getId() {
        return id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    String getClient_qr() {
        return client_qr;
    }

    public void setClient_qr(String client_qr) {
        this.client_qr = client_qr;
    }

    public String getCaffe_id() {
        return caffe_id;
    }

    public void setCaffe_id(String caffe_id) {
        this.caffe_id = caffe_id;
    }

    String getAll_cup() {
        return all_cup;
    }

    public void setAll_cup(String all_cup) {
        this.all_cup = all_cup;
    }

    String getFill_cup() {
        return fill_cup;
    }

    public void setFill_cup(String fill_cup) {
        this.fill_cup = fill_cup;
    }
}
