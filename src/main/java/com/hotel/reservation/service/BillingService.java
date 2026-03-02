package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.Bill;

public interface BillingService {

    void generateBill(Bill bill);


    double calculateTotal(double pricePerNight, int nights, double taxPercentage);
}
