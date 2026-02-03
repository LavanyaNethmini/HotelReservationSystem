package com.hotel.reservation.repository;

import com.hotel.reservation.domain.model.Guest;

public interface GuestRepository {

    Guest findByPhone(String phone);

    int save(Guest guest);
}
