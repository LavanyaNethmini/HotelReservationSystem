package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.repository.GuestRepository;
import com.hotel.reservation.repository.impl.GuestRepositoryImpl;
import com.hotel.reservation.service.GuestService;

import java.util.List;

public class GuestServiceImpl implements GuestService {

    private final GuestRepository repo = new GuestRepositoryImpl();

    @Override
    public List<Guest> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Guest> search(String keyword) {
        return repo.search(keyword);
    }

    @Override
    public Guest getById(int id) {
        return repo.findById(id);
    }

    @Override
    public void update(Guest guest) {
        repo.update(guest);
    }
}

