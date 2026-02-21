package com.hotel.reservation.notification;

public interface EventPublisher {

    void registerObserver(EventObserver observer);

    void removeObserver(EventObserver observer);

    void notifyObservers(String eventType, Object data);
}
