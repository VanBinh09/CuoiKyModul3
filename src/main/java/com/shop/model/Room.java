package com.shop.model;

public class Room {
    private int id;
    private String tenNguoiThue;
    private String soDienThoai;
    private String ngayBatDau;
    private int hinhThucId;
    private String hinhThucThanhToan;
    private String ghiChu;

    public Room() {
    }

    public Room(int id, String tenNguoiThue, String soDienThoai, String ngayBatDau,
                int hinhThucId, String hinhThucThanhToan, String ghiChu) {
        this.id = id;
        this.tenNguoiThue = tenNguoiThue;
        this.soDienThoai = soDienThoai;
        this.ngayBatDau = ngayBatDau;
        this.hinhThucId = hinhThucId;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNguoiThue() {
        return tenNguoiThue;
    }

    public void setTenNguoiThue(String tenNguoiThue) {
        this.tenNguoiThue = tenNguoiThue;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public int getHinhThucId() {
        return hinhThucId;
    }

    public void setHinhThucId(int hinhThucId) {
        this.hinhThucId = hinhThucId;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", tenNguoiThue='" + tenNguoiThue + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", ngayBatDau='" + ngayBatDau + '\'' +
                ", hinhThucId=" + hinhThucId +
                ", hinhThucThanhToan='" + hinhThucThanhToan + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}
