package com.hotel.reservation.notification;

import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.infrastructure.EmailSender;
import com.hotel.reservation.repository.GuestRepository;
import com.hotel.reservation.repository.NotificationRepository;
import com.hotel.reservation.repository.impl.GuestRepositoryImpl;
import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.repository.impl.NotificationRepositoryImpl;

public class EmailNotificationObserver implements EventObserver {

    private final NotificationRepository notificationRepo =
            new NotificationRepositoryImpl();

    @Override
    public void update(String eventType,
                       Reservation reservation) {

        GuestRepository guestRepo = new GuestRepositoryImpl();
        Guest guest = guestRepo.findById(reservation.getGuestId());

        EmailSender emailSender = new EmailSender();

        switch (eventType) {

            case "RESERVATION_CREATED":

                String message =
                        "Your reservation has been successfully created.";

                emailSender.sendEmail(
                        guest.getEmail(),
                        "Reservation Confirmed",
                        message
                );

                // ✅ Save logged user ID
                notificationRepo.save(
                        reservation.getCreatedBy(),                     // ✅ logged user
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
                        reservation.getCreatedBy(),                     // ✅ logged user
                        "EMAIL",
                        "RESERVATION_CANCELLED",
                        cancelMessage,
                        "SENT"
                );

                break;
        }
    }
}
