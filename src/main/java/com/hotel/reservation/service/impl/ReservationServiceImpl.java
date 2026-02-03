package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.repository.*;
import com.hotel.reservation.repository.impl.*;
import com.hotel.reservation.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {

    private final GuestRepository guestRepo =
            new GuestRepositoryImpl();

    private final RoomRepository roomRepo =
            new RoomRepositoryImpl();

    private final ReservationRepository reservationRepo =
            new ReservationRepositoryImpl();

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

        // ===== SAVE & RETURN ID =====
        return reservationRepo.save(finalReservation);
    }
}
