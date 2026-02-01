package com.hotel.reservation.repository;

import java.util.List;

public interface UserRepository {

    void save(String username,
              String password,
              String fullName,
              String contactNo,
              String address,
              String role);

    boolean existsByUsername(String username);

    boolean validateLogin(String username, String password);

    List<String> findAllUsernames();
}
