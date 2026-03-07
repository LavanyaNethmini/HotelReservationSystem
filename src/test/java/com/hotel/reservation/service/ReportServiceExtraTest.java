package com.hotel.reservation.service;

import com.hotel.reservation.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReportServiceExtraTest {

    @Test
    void shouldCreateReportService() {

        ReportServiceImpl service = new ReportServiceImpl();

        assertNotNull(service);
    }


}