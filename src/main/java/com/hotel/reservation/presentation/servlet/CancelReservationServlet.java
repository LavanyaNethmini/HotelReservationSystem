package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.service.ReservationService;
import com.hotel.reservation.service.impl.ReservationServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class CancelReservationServlet extends HttpServlet {

    private final ReservationService reservationService =
            new ReservationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int reservationId =
                Integer.parseInt(req.getParameter("id"));

        reservationService.cancelReservation(reservationId);

        // success message
        session.setAttribute(
                "success",
                "Reservation cancelled successfully"
        );

        resp.sendRedirect("reservation");
    }
}
