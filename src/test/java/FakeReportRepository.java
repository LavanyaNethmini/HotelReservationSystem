import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.dto.ReservationReportDTO;
import com.hotel.reservation.dto.RevenueReportDTO;
import com.hotel.reservation.dto.RoomOccupancyDTO;
import com.hotel.reservation.repository.ReportRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class FakeReportRepository implements ReportRepository {

    @Override
    public BigDecimal getTotalRevenue(LocalDate start, LocalDate end) {
        return BigDecimal.valueOf(100000);
    }

    @Override
    public List<RevenueReportDTO> getMonthlyRevenue(int year) {
        return new ArrayList<>();
    }

    @Override
    public List<ReservationReportDTO> getReservationsByDateRange(LocalDate start, LocalDate end) {
        return new ArrayList<>();
    }

    @Override
    public List<ReservationReportDTO> getReservationReport(LocalDate start, LocalDate end) {
        return new ArrayList<>();
    }

    @Override
    public int getTotalReservations(LocalDate start, LocalDate end) {
        return 0;
    }

    @Override
    public List<RoomOccupancyDTO> getRoomOccupancyByDateRange(LocalDate start, LocalDate end) {
        return new ArrayList<>();
    }

    @Override
    public double getOverallOccupancyRate(LocalDate start, LocalDate end) {
        return 0;
    }

    @Override
    public List<RoomOccupancyDTO> getRoomOccupancyReport(LocalDate start, LocalDate end) {
        return new ArrayList<>();
    }

    @Override
    public int getReservationCount(LocalDate start, LocalDate end) {
        return 0;
    }

    @Override
    public List<Bill> getBillsByDateRange(LocalDate start, LocalDate end) {
        return new ArrayList<>();
    }

    // other methods can return dummy values
}