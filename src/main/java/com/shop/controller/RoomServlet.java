package com.shop.controller;

import com.shop.dao.RoomDAO;
import com.shop.model.Room;
import com.shop.model.PaymentType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RoomServlet", value = "/room")
public class RoomServlet extends HttpServlet {
    private RoomDAO roomDAO;

    @Override
    public void init() {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertRoom(request, response);
                    break;
                case "delete":
                    deleteRooms(request, response);
                    break;
                case "search":
                    searchRooms(request, response);
                    break;
                default:
                    listRooms(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // Hiển thị danh sách
    private void listRooms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Room> listRooms = roomDAO.getAllRooms();
        List<PaymentType> paymentTypes = roomDAO.getPaymentTypes();
        request.setAttribute("listRooms", listRooms);
        request.setAttribute("paymentTypes", paymentTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    // form tạo mới
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<PaymentType> paymentTypes = roomDAO.getPaymentTypes();
        request.setAttribute("paymentTypes", paymentTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
        dispatcher.forward(request, response);
    }

    // Thêm mới phòng trọ
    private void insertRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String tenNguoiThue = request.getParameter("tenNguoiThue");
        String soDienThoai = request.getParameter("soDienThoai");
        String ngayBatDau = request.getParameter("ngayBatDau");
        int hinhThucId = Integer.parseInt(request.getParameter("hinhThucThanhToan"));
        String ghiChu = request.getParameter("ghiChu");

        Room room = new Room();
        room.setTenNguoiThue(tenNguoiThue);
        room.setSoDienThoai(soDienThoai);
        room.setNgayBatDau(ngayBatDau);
        room.setHinhThucId(hinhThucId);
        room.setGhiChu(ghiChu);

        // Thực hiện thêm mới
        roomDAO.insertRoom(room);
        response.sendRedirect("rooms");
    }

    // Xóa một hoặc nhiều phòng trọ
    private void deleteRooms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String[] ids = request.getParameterValues("selectedIds");
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                roomDAO.deleteRoom(Integer.parseInt(id));
            }
        }
        response.sendRedirect("rooms");
    }

    // Tìm kiếm phòng trọ
    private void searchRooms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String keyword = request.getParameter("keyword");
        List<Room> searchResults = roomDAO.searchRooms(keyword);
        request.setAttribute("listRooms", searchResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
