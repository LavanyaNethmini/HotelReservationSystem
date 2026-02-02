package com.hotel.reservation.domain.model;

import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private String customerName;
    private String roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;
    private String createdBy;

    private Reservation(Builder builder) {
        this.customerName = builder.customerName;
        this.roomType = builder.roomType;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.status = builder.status;
        this.createdBy = builder.createdBy;
    }

    public static class Builder {
        private String customerName;
        private String roomType;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private String status = "CONFIRMED";
        private String createdBy;

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder roomType(String roomType) {
            this.roomType = roomType;
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

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }

    // Getters
    public String getCustomerName() { return customerName; }
    public String getRoomType() { return roomType; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public String getStatus() { return status; }
    public String getCreatedBy() { return createdBy; }
}
