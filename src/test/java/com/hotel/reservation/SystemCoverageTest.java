package com.hotel.reservation;

import com.hotel.reservation.domain.model.*;
import com.hotel.reservation.service.impl.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Disabled to avoid DB connection during tests")
class SystemCoverageTest {

    @Test
    void shouldExecuteMajorDomainClasses() {

        // Bill model
        Bill bill = new Bill.Builder()
                .reservationId(1)
                .nights(2)
                .roomRate(new BigDecimal("5000"))
                .paymentMethod("CASH")
                .createdBy(1)
                .build();

        assertEquals(2, bill.getNights());
        assertEquals(new BigDecimal("5000"), bill.getRoomRate());

        // Reservation model
        Reservation reservation =
                new Reservation.Builder()
                        .roomId(101)
                        .checkIn(LocalDate.now())
                        .checkOut(LocalDate.now().plusDays(3))
                        .createdBy(1)
                        .build();

        assertEquals(101, reservation.getRoomId());

        // User model
        User user = User.builder()
                .username("admin")
                .password("123")
                .fullName("Admin")
                .role("ADMIN")
                .build();

        assertEquals("admin", user.getUsername());
        assertEquals("ADMIN", user.getRole());


        // ===== Service Layer =====
        BillingServiceImpl billingService = new BillingServiceImpl();
        double total = billingService.calculateTotal(5000,2,10);

        assertEquals(11000,total);

        RoomServiceImpl roomService = new RoomServiceImpl();
        assertNotNull(roomService);

        ReportServiceImpl reportService = new ReportServiceImpl();
        assertNotNull(reportService);



    }
}