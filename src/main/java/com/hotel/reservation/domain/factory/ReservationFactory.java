package com.hotel.reservation.domain.factory;

import com.hotel.reservation.domain.model.Reservation;

import java.time.LocalDate;

public class ReservationFactory {

    public static Reservation createStandardReservation(
            String customerName,
            String roomType,
            LocalDate checkIn,
            LocalDate checkOut,
            String createdBy
    ) {
        return new Reservation.Builder()
                .customerName(customerName)
                .roomType(roomType)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .createdBy(createdBy)
                .build();
    }
}
