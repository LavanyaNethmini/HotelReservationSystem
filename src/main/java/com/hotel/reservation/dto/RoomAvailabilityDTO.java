package com.hotel.reservation.dto;

import com.hotel.reservation.domain.model.Room;

public class RoomAvailabilityDTO {

    private Room room;
    private boolean available;

    public RoomAvailabilityDTO(Room room, boolean available) {
        this.room = room;
        this.available = available;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isAvailable() {
        return available;
    }
}
