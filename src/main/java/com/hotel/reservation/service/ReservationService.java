package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.domain.model.Reservation;

public interface ReservationService {

    int makeReservation(Guest guest, Reservation reservation);

}
