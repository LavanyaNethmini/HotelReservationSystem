package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.dto.RevenueReportDTO;
import com.hotel.reservation.dto.ReservationReportDTO;
import com.hotel.reservation.dto.RoomOccupancyDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    /* =========================
       REVENUE REPORT
       ========================= */

    BigDecimal getTotalRevenue(LocalDate start, LocalDate end);

    List<RevenueReportDTO> getMonthlyRevenue(int year);


   /* =========================
   RESERVATION REPORT
   ========================= */

    List<ReservationReportDTO> getReservationReport(
            LocalDate start,
            LocalDate end
    );


    int getTotalReservations(LocalDate start, LocalDate end);


    /* =========================
       ROOM OCCUPANCY REPORT
       ========================= */

    List<RoomOccupancyDTO> getRoomOccupancyByDateRange(
            LocalDate start,
            LocalDate end
    );

    double getOverallOccupancyRate(
            LocalDate start,
            LocalDate end
    );

    /* ==========================
   ROOM OCCUPANCY REPORT
   ========================== */

    List<RoomOccupancyDTO> getRoomOccupancyReport(
            LocalDate start,
            LocalDate end
    );


    int getReservationCount(LocalDate start, LocalDate end);



    List<Bill> getBillsByDateRange(LocalDate start, LocalDate end);

}
