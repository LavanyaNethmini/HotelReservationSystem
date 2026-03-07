package com.hotel.reservation.domain;

import com.hotel.reservation.domain.model.Bill;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BillDomainTest {

    @Test
    void billBuilder_shouldCreateBill() {

        Bill bill =
                new Bill.Builder()
                        .reservationId(1)
                        .nights(2)
                        .roomRate(new BigDecimal("5000"))
                        .paymentMethod("CASH")
                        .createdBy(1)
                        .build();

        assertEquals(2,bill.getNights());
    }
}