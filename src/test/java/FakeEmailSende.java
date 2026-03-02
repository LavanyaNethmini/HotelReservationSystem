import com.hotel.reservation.infrastructure.EmailSender;

class FakeEmailSender extends EmailSender {

    boolean emailSent = false;
    String subjectCaptured;

    @Override
    public void sendEmail(String to, String subject, String message) {
        emailSent = true;
        subjectCaptured = subject;
    }
}