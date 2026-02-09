package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.domain.model.Reservation;

import java.math.BigDecimal;
import java.util.List;

public interface ReservationService {

    int makeReservation(Guest guest, Reservation reservation);

    BigDecimal getRoomRate(int roomId);

    List<Reservation> getAll();

    List<Reservation> search(String keyword);

    List<Reservation> getMonthly(int year, int month);

    void cancelReservation(int reservationId);

    void updateReservation(Reservation r);
}
