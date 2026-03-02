package com.hotel.reservation.repository;

public interface NotificationRepository {

    void save(
            int userId,
            String channel,
            String eventType,
            String message,
            String status
    );

}

