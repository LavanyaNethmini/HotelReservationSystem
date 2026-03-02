package com.hotel.reservation.notification;

import com.hotel.reservation.domain.model.Reservation;

public interface EventPublisher {

    void registerObserver(EventObserver observer);

    void removeObserver(EventObserver observer);

    void notifyObservers(String eventType, Reservation reservation);

}
