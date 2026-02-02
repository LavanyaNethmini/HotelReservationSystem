package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.factory.ReservationFactory;
import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.repository.ReservationRepository;
import com.hotel.reservation.repository.impl.ReservationRepositoryImpl;
import com.hotel.reservation.service.ReservationService;

import java.time.LocalDate;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl() {
        this.reservationRepository = new ReservationRepositoryImpl();
    }

    @Override
    public void makeReservation(
            String customerName,
            String roomType,
            LocalDate checkIn,
            LocalDate checkOut,
            String createdBy
    ) {
        Reservation reservation = ReservationFactory.createStandardReservation(
                customerName, roomType, checkIn, checkOut, createdBy
        );

        reservationRepository.save(reservation);
    }
}
