package com.hotel.reservation.dto;

import java.time.LocalDate;

public class ReservationReportDTO {

    private int reservationId;
    private String guestName;
    private String roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;

    public ReservationReportDTO(
            int reservationId,
            String guestName,
            String roomNumber,
            LocalDate checkIn,
            LocalDate checkOut,
            String status
    ) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public String getStatus() {
        return status;
    }
}
