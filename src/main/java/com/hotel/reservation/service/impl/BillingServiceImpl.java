package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.repository.BillRepository;
import com.hotel.reservation.repository.impl.BillRepositoryImpl;
import com.hotel.reservation.service.BillingService;

public class BillingServiceImpl implements BillingService {

    private final BillRepository billRepository =
            new BillRepositoryImpl();

    @Override
    public void generateBill(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public double calculateTotal(double pricePerNight, int nights, double taxPercentage) {
        double subtotal = pricePerNight * nights;
        double tax = subtotal * taxPercentage / 100;
        return subtotal + tax;
    }
}
