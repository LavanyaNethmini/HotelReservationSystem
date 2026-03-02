package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.dto.RevenueReportDTO;
import com.hotel.reservation.dto.ReservationReportDTO;
import com.hotel.reservation.dto.RoomOccupancyDTO;
import com.hotel.reservation.repository.ReportRepository;
import com.hotel.reservation.repository.impl.ReportRepositoryImpl;
import com.hotel.reservation.service.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl() {
        this.reportRepository = new ReportRepositoryImpl();
    }

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }



    /* =========================
       REVENUE REPORT
       ========================= */

    @Override
    public BigDecimal getTotalRevenue(LocalDate start, LocalDate end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("Invalid date range");
        }

        if (end.isBefore(start)) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date"
            );
        }

        return reportRepository.getTotalRevenue(start, end);
    }

    @Override
    public List<RevenueReportDTO> getMonthlyRevenue(int year) {
        return reportRepository.getMonthlyRevenue(year);
    }



    /* =========================
       RESERVATION REPORT
       ========================= */

    @Override
    public List<ReservationReportDTO> getReservationReport(
            LocalDate start,
            LocalDate end
    ) {
        return reportRepository.getReservationReport(start, end);
    }


    @Override
    public int getTotalReservations(LocalDate start, LocalDate end) {

        validateDateRange(start, end);

        return reportRepository.getTotalReservations(start, end);
    }





    /* =========================
       ROOM OCCUPANCY REPORT
       ========================= */

    @Override
    public List<RoomOccupancyDTO> getRoomOccupancyByDateRange(
            LocalDate start,
            LocalDate end
    ) {

        validateDateRange(start, end);

        return reportRepository.getRoomOccupancyByDateRange(start, end);
    }

    @Override
    public List<RoomOccupancyDTO> getRoomOccupancyReport(
            LocalDate start,
            LocalDate end
    ) {
        return reportRepository.getRoomOccupancyReport(start, end);
    }


    @Override
    public double getOverallOccupancyRate(
            LocalDate start,
            LocalDate end
    ) {

        validateDateRange(start, end);

        return reportRepository.getOverallOccupancyRate(start, end);
    }

    @Override
    public int getReservationCount(LocalDate start, LocalDate end) {
        return reportRepository.getReservationCount(start, end);
    }

    @Override
    public List<Bill> getBillsByDateRange(LocalDate start, LocalDate end) {
        return reportRepository.getBillsByDateRange(start, end);
    }


    /* =========================
       COMMON VALIDATION METHOD
       ========================= */

    private void validateDateRange(LocalDate start, LocalDate end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("Date range required");
        }

        if (end.isBefore(start)) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date"
            );
        }
    }




}
