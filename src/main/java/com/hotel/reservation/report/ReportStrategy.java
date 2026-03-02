package com.hotel.reservation.report;

import jakarta.servlet.http.HttpServletRequest;

public interface ReportStrategy {

    void generateReport(HttpServletRequest request);
}
