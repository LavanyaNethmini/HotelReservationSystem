package com.hotel.reservation.repository;

import com.hotel.reservation.domain.model.Guest;

import java.util.List;

public interface GuestRepository {

    Guest findByPhone(String phone);

    int save(Guest guest);

    List<Guest> findAll();

    List<Guest> search(String keyword);

    Guest findById(int id);

    void update(Guest guest);
}
