package com.hotel.reservation.repository;

import com.hotel.reservation.domain.model.User;

public interface UserRepository {

    // LOGIN
    User findByUsernameAndPassword(String username, String password);

    // REGISTER
    void save(User user);

    boolean existsByUsername(String username);
}
