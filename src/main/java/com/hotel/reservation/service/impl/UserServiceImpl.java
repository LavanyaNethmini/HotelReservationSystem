package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.repository.UserRepository;
import com.hotel.reservation.repository.impl.UserRepositoryImpl;
import com.hotel.reservation.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    /* =========================
       REGISTER USER
       ========================= */
    @Override
    public void registerUser(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        userRepository.save(user);
    }

    /* =========================
       LOGIN USER
       ========================= */
    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    /* =========================
       CHECK USERNAME AVAILABILITY
       ========================= */
    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }
}
