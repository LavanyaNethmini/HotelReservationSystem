import com.hotel.reservation.domain.model.Room;
import com.hotel.reservation.repository.RoomRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class FakeRoomRepository implements RoomRepository {

    private final List<Room> rooms = new ArrayList<>();
    private final List<Integer> bookedRoomIds = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setBookedRoomIds(List<Integer> ids) {
        bookedRoomIds.clear();
        bookedRoomIds.addAll(ids);
    }

    @Override
    public List<Room> findAll() {
        return rooms;
    }

    @Override
    public List<Integer> findBookedRoomIds(LocalDate checkIn, LocalDate checkOut) {
        return bookedRoomIds;
    }

    // Unused methods (minimal implementation)

    @Override public boolean roomExists(int roomId) { return true; }
    @Override public boolean isRoomAvailable(int roomId, LocalDate c1, LocalDate c2) { return true; }
    @Override public BigDecimal getRoomRate(int roomId) { return BigDecimal.ZERO; }
    @Override public void save(String number, String type, double price) {}
}