package com.hotel.reservation.service;

import com.hotel.reservation.dto.RoomAvailabilityDTO;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<RoomAvailabilityDTO> getRoomsWithAvailability(
            LocalDate checkIn, LocalDate checkOut);

    void saveRoom(String number, String type, double price_per_night);

}
