package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.domain.model.Reservation;

import java.math.BigDecimal;

public interface ReservationService {

    int makeReservation(Guest guest, Reservation reservation);

    BigDecimal getRoomRate(int roomId);
}
