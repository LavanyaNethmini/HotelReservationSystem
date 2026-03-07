package com.hotel.reservation.domain;

import com.hotel.reservation.domain.model.Guest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuestDomainTest {

    @Test
    void guest_shouldStoreName() {

        Guest guest = new Guest(
                "John",
                "Colombo",
                "john@email.com",
                "0771234567"
        );

        assertEquals("John",guest.getName());
    }
}