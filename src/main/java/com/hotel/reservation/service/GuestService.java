package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.Guest;
import java.util.List;

public interface GuestService {

    List<Guest> getAll();

    List<Guest> search(String keyword);

    Guest getById(int id);

    void update(Guest guest);
}
