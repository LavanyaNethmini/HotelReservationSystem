package com.hotel.reservation.notification;

import com.hotel.reservation.domain.model.Reservation;

public interface EventObserver {

    void update(String eventType,  Reservation reservation);
}
