package com.hotel.reservation.repository;

import com.hotel.reservation.domain.model.User;

import java.util.List;

public interface UserRepository {

    // LOGIN
    User findByUsernameAndPassword(String username, String password);

    // REGISTER
    void save(User user);

    boolean existsByUsername(String username);

    List<User> findAll();

    User findById(int id);

    void update(User user);

    void delete(int id);

    void resetPassword(int id, String newPassword);
}
