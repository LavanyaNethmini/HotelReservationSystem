package com.hotel.reservation.service;

import java.time.LocalDate;

public interface ReservationService {

    void makeReservation(
            String customerName,
            String roomType,
            LocalDate checkIn,
            LocalDate checkOut,
            String createdBy
    );
}
