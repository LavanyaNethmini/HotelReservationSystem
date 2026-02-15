package com.hotel.reservation.presentation.servlet;

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
import java.time.LocalDate;

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

        String yearStr = req.getParameter("year");
        String startStr = req.getParameter("start");
        String endStr = req.getParameter("end");

        // ===== Monthly Revenue by Year =====
        if (yearStr != null && !yearStr.isEmpty()) {

            int year = Integer.parseInt(yearStr);

            req.setAttribute("monthlyRevenue",
                    reportService.getMonthlyRevenue(year));

            req.setAttribute("selectedYear", year);
        }

        // ===== Total Revenue by Date Range =====
        if (startStr != null && endStr != null
                && !startStr.isEmpty()
                && !endStr.isEmpty()) {

            req.setAttribute("totalRevenue",
                    reportService.getTotalRevenue(
                            LocalDate.parse(startStr),
                            LocalDate.parse(endStr)
                    ));

            req.setAttribute("startDate", startStr);
            req.setAttribute("endDate", endStr);
        }
    }



    private void handleReservation(HttpServletRequest req) {
        String start = req.getParameter("start");
        String end = req.getParameter("end");

        if (start != null && end != null) {
            req.setAttribute("reservationReport",
                    reportService.getReservationReport(
                            LocalDate.parse(start),
                            LocalDate.parse(end)
                    ));
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
