import com.hotel.reservation.repository.NotificationRepository;

class FakeNotificationRepository implements NotificationRepository {

    boolean saved = false;

    @Override
    public void save(int userId,
                     String type,
                     String eventType,
                     String message,
                     String status) {

        saved = true;
    }
}