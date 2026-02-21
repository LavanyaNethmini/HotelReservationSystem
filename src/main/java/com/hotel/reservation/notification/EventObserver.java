package com.hotel.reservation.notification;

public interface EventObserver {

    void update(String eventType, Object data);
}
