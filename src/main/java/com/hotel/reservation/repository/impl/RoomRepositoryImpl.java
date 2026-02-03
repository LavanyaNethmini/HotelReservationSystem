package com.hotel.reservation.repository.impl;

import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.RoomRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class RoomRepositoryImpl implements RoomRepository {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    @Override
    public boolean roomExists(int roomId) {

        String sql = "SELECT room_id FROM rooms WHERE room_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            return ps.executeQuery().next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) {

        String sql =
                "SELECT reservation_id FROM reservations " +
                        "WHERE room_id = ? " +
                        "AND NOT (check_out <= ? OR check_in >= ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.setDate(2, java.sql.Date.valueOf(checkIn));
            ps.setDate(3, java.sql.Date.valueOf(checkOut));

            return !ps.executeQuery().next();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal getRoomRate(int roomId) {

        String sql = "SELECT price_per_night FROM rooms WHERE room_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("price_per_night");
            }

            throw new RuntimeException("Room rate not found");

        } catch (Exception e) {
            throw new RuntimeException("Error fetching room rate", e);
        }
    }

}
