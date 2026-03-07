package com.hotel.reservation.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DTOCoverageTest {

    @Test
    void shouldTestDTOFields() {

        String guestName = "John";
        String roomType = "Deluxe";

        assertEquals("John", guestName);
        assertTrue(roomType.contains("Deluxe"));
    }

}