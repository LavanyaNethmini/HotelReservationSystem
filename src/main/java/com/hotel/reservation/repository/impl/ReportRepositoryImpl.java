package com.hotel.reservation.repository.impl;

import com.hotel.reservation.dto.RevenueReportDTO;
import com.hotel.reservation.dto.ReservationReportDTO;
import com.hotel.reservation.dto.RoomOccupancyDTO;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.ReportRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    /* =========================
       REVENUE
       ========================= */

    @Override
    public BigDecimal getTotalRevenue(LocalDate start, LocalDate end) {

        String sql =
                "SELECT SUM(total_amount) " +
                        "FROM bills b " +
                        "JOIN reservations r ON b.reservation_id = r.reservation_id " +
                        "WHERE r.status = 'CONFIRMED' " +
                        "AND r.check_in BETWEEN ? AND ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BigDecimal total = rs.getBigDecimal(1);
                return total != null ? total : BigDecimal.ZERO;
            }

            return BigDecimal.ZERO;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RevenueReportDTO> getMonthlyRevenue(int year) {

        List<RevenueReportDTO> list = new ArrayList<>();

        String sql =
                "SELECT MONTH(r.check_in) AS month, " +
                        "       SUM(b.total_amount) AS total_revenue " +
                        "FROM bills b " +
                        "JOIN reservations r ON b.reservation_id = r.reservation_id " +
                        "WHERE r.status = 'CONFIRMED' " +
                        "AND YEAR(r.check_in) = ? " +
                        "GROUP BY MONTH(r.check_in) " +
                        "ORDER BY month";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int month = rs.getInt("month");
                BigDecimal total = rs.getBigDecimal("total_revenue");

                list.add(new RevenueReportDTO(month, total));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching monthly revenue", e);
        }

        return list;
    }



    /* =========================
       STUB METHODS (you can complete later)
       ========================= */

    @Override
    public List<ReservationReportDTO> getReservationsByDateRange(
            LocalDate start,
            LocalDate end
    ) {
        return new ArrayList<>();
    }

    @Override
    public int getTotalReservations(LocalDate start, LocalDate end) {
        return 0;
    }

    @Override
    public List<RoomOccupancyDTO> getRoomOccupancyByDateRange(
            LocalDate start,
            LocalDate end
    ) {
        return new ArrayList<>();
    }

    @Override
    public double getOverallOccupancyRate(
            LocalDate start,
            LocalDate end
    ) {
        return 0;
    }

    @Override
    public List<ReservationReportDTO> getReservationReport(
            LocalDate start,
            LocalDate end
    ) {

        List<ReservationReportDTO> list = new ArrayList<>();

        String sql =
                "SELECT r.reservation_id, g.name, rm.room_number, " +
                        "r.check_in, r.check_out, r.status " +
                        "FROM reservations r " +
                        "JOIN guests g ON r.guest_id = g.guest_id " +
                        "JOIN rooms rm ON r.room_id = rm.room_id " +
                        "WHERE r.check_in BETWEEN ? AND ? " +
                        "ORDER BY r.check_in";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(start));
            ps.setDate(2, java.sql.Date.valueOf(end));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ReservationReportDTO(
                        rs.getInt("reservation_id"),
                        rs.getString("name"),
                        rs.getString("room_number"),
                        rs.getDate("check_in").toLocalDate(),
                        rs.getDate("check_out").toLocalDate(),
                        rs.getString("status")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<RoomOccupancyDTO> getRoomOccupancyReport(
            LocalDate start,
            LocalDate end
    ) {

        List<RoomOccupancyDTO> list = new ArrayList<>();

        String sql =
                "SELECT r.room_id, r.room_number, r.room_type, " +
                        "SUM(DATEDIFF(LEAST(res.check_out, ?), GREATEST(res.check_in, ?))) AS booked_days " +
                        "FROM rooms r " +
                        "LEFT JOIN reservations res ON r.room_id = res.room_id " +
                        "AND res.status = 'CONFIRMED' " +
                        "AND res.check_in <= ? " +
                        "AND res.check_out >= ? " +
                        "GROUP BY r.room_id, r.room_number, r.room_type";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(end));
            ps.setDate(2, java.sql.Date.valueOf(start));
            ps.setDate(3, java.sql.Date.valueOf(end));
            ps.setDate(4, java.sql.Date.valueOf(start));

            ResultSet rs = ps.executeQuery();

            int totalDays = (int) ChronoUnit.DAYS.between(start, end);

            while (rs.next()) {

                int roomId = rs.getInt("room_id");
                String roomNumber = rs.getString("room_number");
                String roomType = rs.getString("room_type");

                int bookedDays = rs.getInt("booked_days");

                double occupancyRate = totalDays == 0
                        ? 0
                        : ((double) bookedDays / totalDays) * 100;

                list.add(new RoomOccupancyDTO(
                        roomId,
                        roomNumber,
                        roomType,
                        totalDays,
                        bookedDays,
                        occupancyRate
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }



}
