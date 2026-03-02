package com.hotel.reservation.service;

import com.hotel.reservation.domain.model.User;

import java.util.List;

public interface UserService {

    void registerUser(User user);

    User login(String username, String password);

    boolean isUsernameTaken(String username);

    List<User> getAllUsers();
    void delete(int userId);

    void resetPassword(int userId, String newPassword);

    void saveUser(User user);
    void updateUser(User user);
    void save(User user);
    User findById(int id);

}
