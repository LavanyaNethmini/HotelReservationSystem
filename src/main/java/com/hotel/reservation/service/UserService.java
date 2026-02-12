package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.User;

import java.util.List;

public interface UserService {

    void registerUser(User user);

    User login(String username, String password);

    boolean isUsernameTaken(String username);

    List<User> getAllUsers();
    void deleteUser(int id);
    void resetPassword(int id, String password);
    void saveUser(User user);
    void updateUser(User user);
}
