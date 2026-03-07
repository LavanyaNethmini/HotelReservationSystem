package com.hotel.reservation.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DTOTest {

    @Test
    void dto_shouldStoreData() {

        String data="test";
        assertEquals("test",data);
    }
}