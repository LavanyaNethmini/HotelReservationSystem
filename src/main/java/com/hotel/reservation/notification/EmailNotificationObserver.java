package com.hotel.reservation.notification;

import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.infrastructure.EmailSender;
import com.hotel.reservation.repository.GuestRepository;
import com.hotel.reservation.repository.NotificationRepository;
import com.hotel.reservation.repository.impl.GuestRepositoryImpl;
import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.repository.impl.NotificationRepositoryImpl;

public class EmailNotificationObserver implements EventObserver {

    private final NotificationRepository notificationRepo;
    private final GuestRepository guestRepo;
    private final EmailSender emailSender;

    // Production constructor
    public EmailNotificationObserver() {
        this(
                new NotificationRepositoryImpl(),
                new GuestRepositoryImpl(),
                new EmailSender()
        );
    }

    // Test constructor
    public EmailNotificationObserver(
            NotificationRepository notificationRepo,
            GuestRepository guestRepo,
            EmailSender emailSender) {

        this.notificationRepo = notificationRepo;
        this.guestRepo = guestRepo;
        this.emailSender = emailSender;
    }

    @Override
    public void update(String eventType,
                       Reservation reservation) {

        Guest guest = guestRepo.findById(reservation.getGuestId());

        switch (eventType) {

            case "RESERVATION_CREATED":

                String message =
                        "Your reservation has been successfully created.";

                emailSender.sendEmail(
                        guest.getEmail(),
                        "Reservation Confirmed",
                        message
                );

                notificationRepo.save(
                        reservation.getCreatedBy(),
                        "EMAIL",
                        "RESERVATION_CREATED",
                        message,
                        "SENT"
                );

                break;

            case "RESERVATION_CANCELLED":

                String cancelMessage =
                        "Your reservation has been cancelled.";

                emailSender.sendEmail(
                        guest.getEmail(),
                        "Reservation Cancelled",
                        cancelMessage
                );

                notificationRepo.save(
                        reservation.getCreatedBy(),
                        "EMAIL",
                        "RESERVATION_CANCELLED",
                        cancelMessage,
                        "SENT"
                );

                break;
        }
    }
}
