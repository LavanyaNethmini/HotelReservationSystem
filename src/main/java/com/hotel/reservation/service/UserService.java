package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.User;

import java.util.List;

public interface UserService {

    void registerUser(User user);

    boolean isUsernameTaken(String username);

    boolean login(String username, String password);

    List<String> getAllActiveUsers();
}
