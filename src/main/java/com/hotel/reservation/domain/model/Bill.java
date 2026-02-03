package com.hotel.reservation.domain.model;

import java.math.BigDecimal;

public class Bill {

    private int reservationId;
    private int nights;
    private BigDecimal roomRate;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private int createdBy;

    private Bill(Builder builder) {
        this.reservationId = builder.reservationId;
        this.nights = builder.nights;
        this.roomRate = builder.roomRate;
        this.totalAmount = builder.totalAmount;
        this.paymentMethod = builder.paymentMethod;
        this.createdBy = builder.createdBy;
    }

    public int getReservationId() { return reservationId; }
    public int getNights() { return nights; }
    public BigDecimal getRoomRate() { return roomRate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public int getCreatedBy() { return createdBy; }

    public static class Builder {

        private int reservationId;
        private int nights;
        private BigDecimal roomRate;
        private BigDecimal totalAmount;
        private String paymentMethod;
        private int createdBy;

        public Builder reservationId(int reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public Builder nights(int nights) {
            this.nights = nights;
            return this;
        }

        public Builder roomRate(BigDecimal roomRate) {
            this.roomRate = roomRate;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder createdBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Bill build() {
            if (nights <= 0) {
                throw new IllegalStateException("Invalid stay duration");
            }
            this.totalAmount =
                    roomRate.multiply(BigDecimal.valueOf(nights));
            return new Bill(this);
        }
    }
}
