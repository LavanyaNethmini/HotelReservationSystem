package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.service.DashboardService;
import com.hotel.reservation.service.impl.DashboardServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DashboardService service = new DashboardServiceImpl();

        try {
            int totalReservations = service.getTodayReservations();
            int totalGuests = service.getTotalGuests();
            int availableRooms = service.getAvailableRoomsToday();

            request.setAttribute("totalReservations", totalReservations);
            request.setAttribute("totalGuests", totalGuests);
            request.setAttribute("availableRooms", availableRooms);

        } catch (SQLException e) {
            e.printStackTrace();
        }



        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
