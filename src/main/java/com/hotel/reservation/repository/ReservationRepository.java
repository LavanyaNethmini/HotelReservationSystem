package com.hotel.reservation.repository;

import com.hotel.reservation.domain.model.Reservation;

import java.util.List;

public interface ReservationRepository {
    int save(Reservation reservation);

    List<Reservation> findAll();

    List<Reservation> search(String keyword);

    List<Reservation> findByMonth(int year, int month);

    Reservation findById(int reservationId);

    void cancel(int reservationId);

    void update(Reservation reservation);

    void updateStatus(int reservationId, String status);

}

