package com.hotel.reservation.repository.impl;

import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.NotificationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NotificationRepositoryImpl implements NotificationRepository {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    @Override
    public void save(int userId,
                     String channel,
                     String eventType,
                     String message,
                     String status) {

        String sql =
                "INSERT INTO notifications " +
                        "(user_id, channel, event_type, message, sent_at, status) " +
                        "VALUES (?, ?, ?, ?, NOW(), ?)";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, channel);
            ps.setString(3, eventType);
            ps.setString(4, message);
            ps.setString(5, status);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();   // 👈 ADD THIS
            throw new RuntimeException("Error saving notification", e);
        }

    }
}
