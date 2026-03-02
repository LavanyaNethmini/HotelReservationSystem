package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.repository.UserRepository;
import com.hotel.reservation.repository.impl.UserRepositoryImpl;
import com.hotel.reservation.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    // Test constructor (Dependency Injection)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void delete(int userId) {
        userRepository.delete(userId);
    }


    @Override
    public void resetPassword(int userId, String newPassword) {
        userRepository.resetPassword(userId, newPassword);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User updatedUser) {

        // 1️⃣ Get existing user from DB
        User existingUser = userRepository.findById(updatedUser.getUserId());

        // 2️⃣ Rebuild complete user with old password
        User user = new User.UserBuilder()
                .userId(existingUser.getUserId())
                .username(existingUser.getUsername()) // keep same
                .password(existingUser.getPassword()) // KEEP PASSWORD
                .fullName(updatedUser.getFullName())
                .contactNo(updatedUser.getContactNo())
                .address(updatedUser.getAddress())
                .role(updatedUser.getRole())
                .build();

        // 3️⃣ Update DB
        userRepository.update(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

}
