import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.notification.EmailNotificationObserver;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static junit.framework.Assert.assertTrue;

public class NotificationServiseTest {

    @Test
    void shouldSendEmailAndLogWhenReservationCreated() {

        FakeEmailSender fakeEmail = new FakeEmailSender();
        FakeNotificationRepository fakeNotification =
                new FakeNotificationRepository();
        FakeGuestRepository fakeGuest =
                new FakeGuestRepository();

        EmailNotificationObserver observer =
                new EmailNotificationObserver(
                        fakeNotification,
                        fakeGuest,
                        fakeEmail
                );

        Reservation reservation = new Reservation.Builder()
                .guestId(1)
                .roomId(1)
                .checkIn(LocalDate.now())
                .checkOut(LocalDate.now().plusDays(2))
                .createdBy(99)
                .build();

        observer.update("RESERVATION_CREATED", reservation);

        assertTrue(fakeEmail.emailSent);
        assertTrue(fakeNotification.saved);
    }
}
