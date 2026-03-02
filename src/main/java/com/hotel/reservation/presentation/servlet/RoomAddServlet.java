package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.service.RoomService;
import com.hotel.reservation.service.impl.RoomServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/room-add")
public class RoomAddServlet extends HttpServlet {

    private final RoomService roomService = new RoomServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String role = (String) req.getSession().getAttribute("role");

        if (role == null || !role.equals("ADMIN")) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        req.getRequestDispatcher("/room-add.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        String role = (String) req.getSession().getAttribute("role");

        if (role == null || !role.equals("ADMIN")) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        String roomNumber = req.getParameter("roomNumber");
        String roomType = req.getParameter("roomType");
        double price = Double.parseDouble(req.getParameter("price_per_night"));

        roomService.saveRoom(roomNumber, roomType, price);

        resp.sendRedirect(req.getContextPath() + "/rooms");
    }
}
