package com.hotel.reservation.dto;

import java.math.BigDecimal;

public class RevenueReportDTO {

    private int month;
    private BigDecimal totalRevenue;

    public RevenueReportDTO(int month, BigDecimal totalRevenue) {
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    public int getMonth() {
        return month;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}

