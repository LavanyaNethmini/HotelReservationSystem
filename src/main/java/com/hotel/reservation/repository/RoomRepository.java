package com.hotel.reservation.repository;

import com.hotel.reservation.domain.model.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RoomRepository {

    List<Room> findAll();

    List<Integer> findBookedRoomIds(LocalDate checkIn, LocalDate checkOut);

    boolean roomExists(int roomId);

    boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut);

    BigDecimal getRoomRate(int roomId);

    void save(String number, String type, double price);

}
