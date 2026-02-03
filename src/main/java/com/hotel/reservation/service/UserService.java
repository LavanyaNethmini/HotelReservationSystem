package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.User;

public interface UserService {

    void registerUser(User user);

    User login(String username, String password);

    boolean isUsernameTaken(String username);
}
