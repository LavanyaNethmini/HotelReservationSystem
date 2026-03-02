package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.service.BillingService;
import com.hotel.reservation.service.impl.BillingServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class BillingServlet extends HttpServlet {

    private final BillingService billingService =
            new BillingServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        int userId = (int) session.getAttribute("userId");

        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        BigDecimal roomRate = new BigDecimal(request.getParameter("roomRate"));
        String paymentMethod = request.getParameter("paymentMethod");

        LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"));
        LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"));

        long nights =
                ChronoUnit.DAYS.between(checkIn, checkOut);

        Bill bill =
                new Bill.Builder()
                        .reservationId(reservationId)
                        .nights((int) nights)
                        .roomRate(roomRate)
                        .paymentMethod(paymentMethod)
                        .createdBy(userId)
                        .build();

        billingService.generateBill(bill);

        request.setAttribute("bill", bill);
        request.getRequestDispatcher("bill-print.jsp")
                .forward(request, response);
    }
}
