import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.repository.GuestRepository;

import java.util.ArrayList;
import java.util.List;

class FakeGuestRepository implements GuestRepository {

    @Override
    public Guest findByPhone(String phone) {
        return null;
    }

    @Override
    public int save(Guest guest) {
        return 0;
    }

    @Override
    public List<Guest> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Guest> search(String keyword) {
        return new ArrayList<>();
    }

    @Override
    public Guest findById(int id) {
        return new Guest(
                id,
                "Test Guest",
                "Colombo",
                "test@email.com",
                "0771234567"
        );
    }

    @Override
    public void update(Guest guest) {

    }
}