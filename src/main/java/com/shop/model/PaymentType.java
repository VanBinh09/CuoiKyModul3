package com.shop.model;

public class PaymentType {
    private int id;
    private String tenHinhThuc;

    public PaymentType() {
    }

    public PaymentType(int id, String tenHinhThuc) {
        this.id = id;
        this.tenHinhThuc = tenHinhThuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenHinhThuc() {
        return tenHinhThuc;
    }

    public void setTenHinhThuc(String tenHinhThuc) {
        this.tenHinhThuc = tenHinhThuc;
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", tenHinhThuc='" + tenHinhThuc + '\'' +
                '}';
    }
}
