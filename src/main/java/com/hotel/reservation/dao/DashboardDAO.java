package com.hotel.reservation.dao;

import com.hotel.reservation.infrastructure.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    public int getAvailableRoomsToday() throws SQLException {

        String sql = "SELECT COUNT(*) FROM rooms WHERE availability = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }

    public int getTotalGuests() throws SQLException {

        String sql = "SELECT COUNT(*) FROM guests";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }

    public int getTodayReservations() throws SQLException {

        String sql = "SELECT COUNT(*) FROM reservations WHERE check_in = CURDATE()";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }
}