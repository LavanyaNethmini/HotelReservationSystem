package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.repository.UserRepository;
import com.hotel.reservation.repository.impl.UserRepositoryImpl;
import com.hotel.reservation.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public void registerUser(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        userRepository.save(
                user.getUsername(),
                user.getPassword(),
                user.getFullName(),
                user.getContactNo(),
                user.getAddress(),
                user.getRole()
        );
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<String> getAllActiveUsers() {
        return userRepository.findAllUsernames();
    }
}
