import com.hotel.reservation.service.ReportService;
import com.hotel.reservation.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReportServiceTest {


    @Test
    void shouldThrowExceptionWhenStartDateNull() {

        ReportService service =
                new ReportServiceImpl(new FakeReportRepository());

        assertThrows(IllegalArgumentException.class, () ->
                service.getTotalRevenue(null, LocalDate.now()));
    }

    @Test
    void shouldThrowExceptionWhenEndDateBeforeStart() {

        ReportService service =
                new ReportServiceImpl(new FakeReportRepository());

        LocalDate start = LocalDate.of(2025, 5, 10);
        LocalDate end = LocalDate.of(2025, 5, 1);

        assertThrows(IllegalArgumentException.class, () ->
                service.getTotalRevenue(start, end));
    }

    @Test
    void shouldReturnCorrectRevenue() {

        ReportService service =
                new ReportServiceImpl(new FakeReportRepository());

        BigDecimal revenue =
                service.getTotalRevenue(
                        LocalDate.now().minusDays(5),
                        LocalDate.now()
                );

        assertEquals(BigDecimal.valueOf(100000), revenue);
    }

    @Test
    void shouldThrowExceptionForInvalidDateRangeInOccupancyRate() {

        ReportService service =
                new ReportServiceImpl(new FakeReportRepository());

        assertThrows(IllegalArgumentException.class, () ->
                service.getOverallOccupancyRate(
                        LocalDate.now(),
                        LocalDate.now().minusDays(1)
                ));
    }
}
