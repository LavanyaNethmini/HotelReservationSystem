package com.hotel.reservation.repository.impl;

import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public void save(String username,
                     String password,
                     String fullName,
                     String contactNo,
                     String address,
                     String role) {

        String sql = "INSERT INTO users " +
                "(username, password, full_name, contact_no, address, role, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.setString(4, contactNo);
            ps.setString(5, address);
            ps.setString(6, role);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public boolean existsByUsername(String username) {

        String sql = "SELECT user_id FROM users WHERE username = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            throw new RuntimeException("Error checking username", e);
        }
    }

    @Override
    public List<String> findAllUsernames() {

        List<String> users = new ArrayList<>();
        String sql = "SELECT username FROM users WHERE is_active = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(rs.getString("username"));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching users", e);
        }

        return users;
    }
}
