package com.hotel.reservation.domain;

import com.hotel.reservation.domain.model.Bill;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DomainCoverageTest {

    @Test
    void shouldCreateBill() {

        Bill bill = new Bill.Builder()
                .reservationId(1)
                .nights(3)
                .roomRate(new BigDecimal("4000"))
                .paymentMethod("CARD")
                .createdBy(1)
                .build();

        assertEquals(3, bill.getNights());
    }

}