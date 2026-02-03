package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.ReservationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    @Override
    public int save(Reservation reservation) {

        String sql = "INSERT INTO reservations " +
                "(guest_id, room_id, check_in, check_out, status, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, reservation.getGuestId());
            ps.setInt(2, reservation.getRoomId());
            ps.setDate(3, java.sql.Date.valueOf(reservation.getCheckIn()));
            ps.setDate(4, java.sql.Date.valueOf(reservation.getCheckOut()));
            ps.setString(5, reservation.getStatus());
            ps.setInt(6, reservation.getCreatedBy());

            ps.executeUpdate();

            // ===== GET GENERATED RESERVATION ID =====
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

            throw new RuntimeException("Failed to create reservation");

        } catch (Exception e) {
            throw new RuntimeException("Error saving reservation", e);
        }
    }
}
