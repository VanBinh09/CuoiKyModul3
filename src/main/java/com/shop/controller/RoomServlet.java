package com.shop.controller;

import com.shop.dao.RoomDAO;
import com.shop.model.Room;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RoomServlet",value = "/rooms")
public class RoomServlet extends HttpServlet {

    private RoomDAO roomDAO;

    @Override
    public void init() {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "search":
                    searchRooms(request, response);
                    break;
                case "delete":
                    deleteRooms(request, response);
                    break;
                default:
                    listRooms(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listRooms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Room> listRoom = roomDAO.getAllRooms();
        request.setAttribute("listRoom", listRoom);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void searchRooms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String keyword = request.getParameter("keyword");
        List<Room> listRoom = roomDAO.searchRooms(keyword);
        request.setAttribute("listRoom", listRoom);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteRooms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String[] ids = request.getParameterValues("deleteIds");
        if (ids != null) {
            for (String id : ids) {
                roomDAO.deleteRoom(Integer.parseInt(id));
            }
        }
        response.sendRedirect("rooms");
    }
}
