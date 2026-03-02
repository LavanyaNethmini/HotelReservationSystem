import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.repository.BillRepository;

import java.math.BigDecimal;

class FakeBillRepository implements BillRepository {

    @Override
    public void save(Bill bill) {
        // do nothing (fake)
    }


}