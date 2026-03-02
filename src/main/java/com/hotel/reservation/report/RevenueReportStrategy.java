package com.hotel.reservation.report;

import com.hotel.reservation.service.ReportService;
import com.hotel.reservation.service.impl.ReportServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class RevenueReportStrategy implements ReportStrategy {

    private final ReportService reportService =
            new ReportServiceImpl();

    @Override
    public void generateReport(HttpServletRequest request) {

        String yearStr = request.getParameter("year");
        String startStr = request.getParameter("start");
        String endStr = request.getParameter("end");

        if (startStr != null && endStr != null) {
            request.setAttribute("totalRevenue",
                    reportService.getTotalRevenue(
                            LocalDate.parse(startStr),
                            LocalDate.parse(endStr)
                    ));
        }

        if (yearStr != null) {
            request.setAttribute("monthlyRevenue",
                    reportService.getMonthlyRevenue(
                            Integer.parseInt(yearStr)
                    ));
        }
    }
}
