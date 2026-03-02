package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.notification.EmailNotificationObserver;
import com.hotel.reservation.notification.EventObserver;
import com.hotel.reservation.notification.EventPublisher;
import com.hotel.reservation.repository.*;
import com.hotel.reservation.repository.impl.*;
import com.hotel.reservation.service.ReservationService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService, EventPublisher {

    private final GuestRepository guestRepo =
            new GuestRepositoryImpl();

    private final RoomRepository roomRepo =
            new RoomRepositoryImpl();

    private final ReservationRepository reservationRepo =
            new ReservationRepositoryImpl();

    private final List<EventObserver> observers =
            new ArrayList<>();

    public ReservationServiceImpl() {
        registerObserver(new EmailNotificationObserver());
    }



    @Override
    public int makeReservation(Guest guest, Reservation reservation) {

        // ===== ROOM VALIDATION =====
        if (!roomRepo.roomExists(reservation.getRoomId())) {
            throw new RuntimeException("Invalid room");
        }

        if (!roomRepo.isRoomAvailable(
                reservation.getRoomId(),
                reservation.getCheckIn(),
                reservation.getCheckOut())) {

            throw new RuntimeException("Room not available");
        }

        // ===== GUEST HANDLING =====
        Guest existingGuest =
                guestRepo.findByPhone(guest.getPhone());

        int guestId = (existingGuest != null)
                ? existingGuest.getGuestId()
                : guestRepo.save(guest);

        // ===== FINAL RESERVATION =====
        Reservation finalReservation =
                new Reservation.Builder()
                        .guestId(guestId)
                        .roomId(reservation.getRoomId())
                        .checkIn(reservation.getCheckIn())
                        .checkOut(reservation.getCheckOut())
                        .createdBy(reservation.getCreatedBy())
                        .build();

        // ===== SAVE & NOTIFY =====
        int reservationId = reservationRepo.save(finalReservation);

        notifyObservers(
                "RESERVATION_CREATED",
                finalReservation
        );

        return reservationId;
    }


    // =========================
    // ROOM RATE FOR BILLING
    // =========================
    @Override
    public BigDecimal getRoomRate(int roomId) {
        return roomRepo.getRoomRate(roomId);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepo.findAll();
    }

    @Override
    public List<Reservation> search(String keyword) {
        return reservationRepo.search(keyword);
    }

    @Override
    public List<Reservation> getMonthly(int year, int month) {
        return reservationRepo.findByMonth(year, month);
    }

    @Override
    public void cancelReservation(int reservationId) {
        reservationRepo.updateStatus(reservationId, "CANCELLED");
        Reservation reservation = reservationRepo.findById(reservationId);

        reservationRepo.cancel(reservationId);

        notifyObservers("RESERVATION_CANCELLED", reservation);
    }

    @Override
    public void updateReservation(Reservation r) {
        reservationRepo.update(r);
    }

    @Override
    public void registerObserver(EventObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(EventObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(
            String eventType,
            Reservation reservation) {

        for (EventObserver observer : observers) {
            observer.update(eventType, reservation);
        }
    }


}





