import com.hotel.reservation.domain.model.Room;
import com.hotel.reservation.dto.RoomAvailabilityDTO;
import com.hotel.reservation.service.RoomService;
import com.hotel.reservation.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {

    @Test
    void shouldMarkRoomAsAvailableWhenNotBooked() {

        FakeRoomRepository fakeRepo = new FakeRoomRepository();

        fakeRepo.addRoom(
                new Room(1, "101", "DELUXE", BigDecimal.valueOf(5000), true)
        );

        fakeRepo.setBookedRoomIds(new ArrayList<>()); // no bookings

        RoomService service = new RoomServiceImpl(fakeRepo);

        List<RoomAvailabilityDTO> result =
                service.getRoomsWithAvailability(
                        LocalDate.now(),
                        LocalDate.now().plusDays(1)
                );

        assertTrue(result.get(0).isAvailable());
    }

    @Test
    void shouldMarkRoomAsUnavailableWhenBooked() {

        FakeRoomRepository fakeRepo = new FakeRoomRepository();

        fakeRepo.addRoom(
                new Room(1, "101", "DELUXE", BigDecimal.valueOf(5000), true)
        );

        fakeRepo.setBookedRoomIds(Collections.singletonList(1)); // room 1 booked

        RoomService service = new RoomServiceImpl(fakeRepo);

        List<RoomAvailabilityDTO> result =
                service.getRoomsWithAvailability(
                        LocalDate.now(),
                        LocalDate.now().plusDays(1)
                );

        assertFalse(result.get(0).isAvailable());
    }

    @Test
    void shouldThrowExceptionWhenDateRangeInvalid() {

        FakeRoomRepository fakeRepo = new FakeRoomRepository();
        RoomService service = new RoomServiceImpl(fakeRepo);

        assertThrows(IllegalArgumentException.class, () ->
                service.getRoomsWithAvailability(
                        LocalDate.now(),
                        LocalDate.now().minusDays(1)
                )
        );
    }
}