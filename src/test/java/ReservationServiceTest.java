import com.hotel.reservation.domain.model.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;

public class ReservationServiceTest {

    @Test
    void reservationBuilder_ShouldCreateReservation() {

        Reservation reservation = new Reservation.Builder()
                .roomId(101)
                .checkIn(LocalDate.now())
                .checkOut(LocalDate.now().plusDays(2))
                .createdBy(1)
                .build();

        assertEquals(101, reservation.getRoomId());
    }
}
