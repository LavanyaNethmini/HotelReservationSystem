package com.hotel.reservation.notification;

import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.infrastructure.EmailSender;
import com.hotel.reservation.repository.GuestRepository;
import com.hotel.reservation.repository.impl.GuestRepositoryImpl;
import com.hotel.reservation.domain.model.Guest;

public class EmailNotificationObserver implements EventObserver {

    @Override
    public void update(String eventType, Object data) {

        if (!(data instanceof Reservation)) {
            return;
        }

        Reservation reservation = (Reservation) data;

        GuestRepository guestRepo = new GuestRepositoryImpl();
        Guest guest = guestRepo.findById(reservation.getGuestId());

        EmailSender emailSender = new EmailSender();

        switch (eventType) {

            case "RESERVATION_CREATED":

                emailSender.sendEmail(
                        guest.getEmail(),
                        "Reservation Confirmed",
                        "Your reservation has been successfully created."
                );
                break;

            case "RESERVATION_CANCELLED":

                emailSender.sendEmail(
                        guest.getEmail(),
                        "Reservation Cancelled",
                        "Your reservation has been cancelled."
                );
                break;
        }
    }
}
