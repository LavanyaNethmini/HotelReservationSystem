package com.hotel.reservation.domain.model;

import java.math.BigDecimal;

public class Room {

    private int roomId;
    private String roomNumber;
    private String roomType;
    private BigDecimal price;
    private boolean active;

    public Room(int roomId, String roomNumber, String roomType, BigDecimal price, boolean active) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.active = active;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }
}
