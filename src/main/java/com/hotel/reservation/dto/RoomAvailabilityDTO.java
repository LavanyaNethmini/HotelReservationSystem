package com.hotel.reservation.dto;

import java.math.BigDecimal;

public class RoomAvailabilityDTO {

    private int roomId;
    private String roomNumber;
    private String roomType;
    private BigDecimal price;
    private boolean available;

    public RoomAvailabilityDTO(
            int roomId,
            String roomNumber,
            String roomType,
            BigDecimal price,
            boolean available
    ) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.available = available;
    }

    public int getRoomId() { return roomId; }
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public BigDecimal getPrice() { return price; }
    public boolean isAvailable() { return available; }
}
