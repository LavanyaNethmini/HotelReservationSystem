import com.hotel.reservation.repository.BillRepository;
import com.hotel.reservation.service.BillingService;
import com.hotel.reservation.service.impl.BillingServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillingServiceTest {

    private final BillingService service =
            new BillingServiceImpl(new FakeBillRepository());

    @Test
    void shouldCalculateTotalWithTaxCorrectly() {
        double result = service.calculateTotal(1000, 2, 10);
        assertEquals(2200, result);
    }

    @Test
    void shouldReturnZeroWhenNightsZero() {
        double result = service.calculateTotal(1000, 0, 10);
        assertEquals(0, result);
    }

    @Test
    void shouldHandleZeroTax() {
        double result = service.calculateTotal(1000, 2, 0);
        assertEquals(2000, result);
    }
}