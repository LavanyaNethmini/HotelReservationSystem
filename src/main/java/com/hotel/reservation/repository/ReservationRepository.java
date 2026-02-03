package com.hotel.reservation.repository;

import com.hotel.reservation.domain.model.Reservation;

public interface ReservationRepository {
    int save(Reservation reservation);
}

