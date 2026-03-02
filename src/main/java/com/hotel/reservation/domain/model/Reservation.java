package com.hotel.reservation.domain.model;

import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private int guestId;
    private int roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;
    private int createdBy;
    private String guestName;
    private String guestPhone;




    private Reservation(Builder b) {
        this.reservationId = b.reservationId;
        this.guestId = b.guestId;
        this.roomId = b.roomId;
        this.checkIn = b.checkIn;
        this.checkOut = b.checkOut;
        this.status = b.status;
        this.createdBy = b.createdBy;

    }

    public Reservation(
            int reservationId,
            int guestId,
            int roomId,
            LocalDate checkIn,
            LocalDate checkOut,
            String status,
            int createdBy,
            String guestName,
            String guestPhone
    ) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.createdBy = createdBy;
        this.guestName = guestName;
        this.guestPhone = guestPhone;
    }


    public int getReservationId() { return reservationId; }
    public int getGuestId() { return guestId; }
    public int getRoomId() { return roomId; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public String getStatus() { return status; }
    public int getCreatedBy() { return createdBy; }
    public String getGuestName() { return guestName; }
    public String getGuestPhone() { return guestPhone; }

    public static class Builder {
        private int reservationId;
        private int guestId;
        private int roomId;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private String status = "CONFIRMED";
        private int createdBy;

        public Builder reservationId(int reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public Builder guestId(int guestId) {
            this.guestId = guestId;
            return this;
        }

        public Builder roomId(int roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder checkIn(LocalDate checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public Builder checkOut(LocalDate checkOut) {
            this.checkOut = checkOut;
            return this;
        }

        public Builder createdBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Reservation build() {
            if (checkIn.isAfter(checkOut)) {
                throw new IllegalStateException("Invalid date range");
            }
            return new Reservation(this);
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

    }
}
