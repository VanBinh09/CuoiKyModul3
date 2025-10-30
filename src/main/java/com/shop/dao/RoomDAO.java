package com.shop.dao;

import com.shop.model.Room;
import com.shop.model.PaymentType;
import com.shop.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {


    public List<Room> getAllRooms() throws SQLException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT p.id, p.ten_nguoi_thue, p.so_dien_thoai, "
                + "DATE_FORMAT(p.ngay_bat_dau, '%d-%m-%Y') AS ngay_bat_dau, "
                + "p.ghi_chu, h.ten_hinh_thuc "
                + "FROM phongtro p "
                + "JOIN hinhthucthanhtoan h ON p.hinh_thuc_id = h.id "
                + "ORDER BY p.id DESC";


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setTenNguoiThue(rs.getString("ten_nguoi_thue"));
                r.setSoDienThoai(rs.getString("so_dien_thoai"));
                r.setNgayBatDau(rs.getString("ngay_bat_dau"));
                r.setHinhThucThanhToan(rs.getString("ten_hinh_thuc"));
                r.setGhiChu(rs.getString("ghi_chu"));
                list.add(r);
            }
        }
        return list;
    }


    public List<PaymentType> getPaymentTypes() throws SQLException {
        List<PaymentType> list = new ArrayList<>();
        String sql = "SELECT * FROM hinhthucthanhtoan";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PaymentType pt = new PaymentType();
                pt.setId(rs.getInt("id"));
                pt.setTenHinhThuc(rs.getString("ten_hinh_thuc"));
                list.add(pt);
            }
        }
        return list;
    }


    public void insertRoom(Room room) throws SQLException {
        String sql  = "INSERT INTO phongtro (ten_nguoi_thue, so_dien_thoai, ngay_bat_dau, hinh_thuc_id, ghi_chu) "
                + "VALUES (?, ?, STR_TO_DATE(?, '%d-%m-%Y'), ?, ?)";


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, room.getTenNguoiThue());
            ps.setString(2, room.getSoDienThoai());
            ps.setString(3, room.getNgayBatDau());
            ps.setInt(4, room.getHinhThucId());
            ps.setString(5, room.getGhiChu());
            ps.executeUpdate();
        }
    }


    public void deleteRoom(int id) throws SQLException {
        String sql = "DELETE FROM phongtro WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Tìm kiếm phòng trọ
    public List<Room> searchRooms(String keyword) throws SQLException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT p.id, p.ten_nguoi_thue, p.so_dien_thoai, "
                + "DATE_FORMAT(p.ngay_bat_dau, '%d-%m-%Y') AS ngay_bat_dau, "
                + "p.ghi_chu, h.ten_hinh_thuc "
                + "FROM phongtro p "
                + "JOIN hinhthucthanhtoan h ON p.hinh_thuc_id = h.id "
                + "WHERE p.id LIKE ? OR p.ten_nguoi_thue LIKE ? OR p.so_dien_thoai LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Room(
                        rs.getInt("id"),
                        rs.getString("ten_nguoi_thue"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("ngay_bat_dau"),
                        rs.getString("ten_hinh_thuc"),
                        rs.getString("ghi_chu")
                ));
            }
        }
        return list;
    }
}
