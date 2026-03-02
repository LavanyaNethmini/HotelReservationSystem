package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.report.OccupancyReportStrategy;
import com.hotel.reservation.report.ReportStrategy;
import com.hotel.reservation.report.ReservationReportStrategy;
import com.hotel.reservation.report.RevenueReportStrategy;

import com.hotel.reservation.service.ReportService;
import com.hotel.reservation.service.impl.ReportServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {

    private final ReportService reportService =
            new ReportServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String type = req.getParameter("type");

        if (type == null) {
            type = "revenue"; // default tab
        }

        req.setAttribute("type", type);

        switch (type) {

            case "revenue":
                handleRevenue(req);
                break;

            case "reservation":
                handleReservation(req);
                break;

            case "occupancy":
                handleOccupancy(req);
                break;
        }

        req.getRequestDispatcher("/report.jsp")
                .forward(req, resp);
    }

    private void handleRevenue(HttpServletRequest req) {

        String start = req.getParameter("start");
        String end = req.getParameter("end");

        if (start != null && end != null
                && !start.isEmpty()
                && !end.isEmpty()) {

            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            List<Bill> bills =
                    reportService.getBillsByDateRange(startDate, endDate);

            req.setAttribute("billList", bills);

            // Calculate total manually (simple way)
            BigDecimal total = BigDecimal.ZERO;
            for (Bill b : bills) {
                total = total.add(b.getTotalAmount());
            }

            req.setAttribute("totalRevenue", total);
        }
    }


    private void handleReservation(HttpServletRequest req) {

        String start = req.getParameter("start");
        String end = req.getParameter("end");

        if (start != null && end != null
                && !start.isEmpty()
                && !end.isEmpty()) {

            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            // 1️⃣ Set reservation list
            req.setAttribute("reservationReport",
                    reportService.getReservationReport(startDate, endDate));

            // 2️⃣ Set total reservation count
            req.setAttribute("totalReservations",
                    reportService.getReservationCount(startDate, endDate));
        }
    }

    private void handleOccupancy(HttpServletRequest req) {
        String start = req.getParameter("start");
        String end = req.getParameter("end");

        if (start != null && end != null) {
            req.setAttribute("occupancyReport",
                    reportService.getRoomOccupancyReport(
                            LocalDate.parse(start),
                            LocalDate.parse(end)
                    ));
        }
    }
}
