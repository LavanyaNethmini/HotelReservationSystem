package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.ReservationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Connection connection;

    public ReservationRepositoryImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public void save(Reservation reservation) {

        String sql = "INSERT INTO reservations " +
                "(customer_name, room_type, check_in, check_out, status, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, reservation.getCustomerName());
            ps.setString(2, reservation.getRoomType());
            ps.setDate(3, java.sql.Date.valueOf(reservation.getCheckIn()));
            ps.setDate(4, java.sql.Date.valueOf(reservation.getCheckOut()));
            ps.setString(5, reservation.getStatus());
            ps.setString(6, reservation.getCreatedBy());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to save reservation", e);
        }
    }
}
