package com.hotel.reservation.repository;

import java.time.LocalDate;

public interface RoomRepository {

    boolean roomExists(int roomId);

    boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut);
}
