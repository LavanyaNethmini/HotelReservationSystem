package com.hotel.reservation.dto;

public class RoomOccupancyDTO {

    private int roomId;
    private String roomNumber;
    private String roomType;
    private int totalDays;
    private int bookedDays;
    private double occupancyRate;

    public RoomOccupancyDTO(
            int roomId,
            String roomNumber,
            String roomType,
            int totalDays,
            int bookedDays,
            double occupancyRate
    ) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.totalDays = totalDays;
        this.bookedDays = bookedDays;
        this.occupancyRate = occupancyRate;
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

    public int getTotalDays() {
        return totalDays;
    }

    public int getBookedDays() {
        return bookedDays;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }
}
