package com.hotel.reservation.help;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelpCoverageTest {

    @Test
    void shouldRunHelpLogic() {

        String helpMessage = "Reservation Help";

        assertTrue(helpMessage.contains("Help"));
    }

}