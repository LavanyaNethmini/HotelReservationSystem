package com.hotel.reservation.repository;

import com.hotel.reservation.dto.RevenueReportDTO;
import com.hotel.reservation.dto.ReservationReportDTO;
import com.hotel.reservation.dto.RoomOccupancyDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {

    /* ===== Revenue ===== */

    BigDecimal getTotalRevenue(LocalDate start, LocalDate end);

    List<RevenueReportDTO> getMonthlyRevenue(int year);


    /* ===== Reservation ===== */

    List<ReservationReportDTO> getReservationsByDateRange(
            LocalDate start,
            LocalDate end
    );

    List<ReservationReportDTO> getReservationReport(
            LocalDate start,
            LocalDate end
    );


    int getTotalReservations(LocalDate start, LocalDate end);


    /* ===== Room Occupancy ===== */

    List<RoomOccupancyDTO> getRoomOccupancyByDateRange(
            LocalDate start,
            LocalDate end
    );

    double getOverallOccupancyRate(
            LocalDate start,
            LocalDate end
    );

    List<RoomOccupancyDTO> getRoomOccupancyReport(
            LocalDate start,
            LocalDate end
    );




}
