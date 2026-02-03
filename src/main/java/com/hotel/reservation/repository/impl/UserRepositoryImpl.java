package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    /* =========================
       REGISTER USER
       ========================= */
    @Override
    public void save(User user) {

        String sql = "INSERT INTO users " +
                "(username, password, full_name, contact_no, address, role, status, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getContactNo());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getStatus());
            ps.setBoolean(8, user.isActive());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    /* =========================
       CHECK USERNAME EXISTS
       ========================= */
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

    /* =========================
       LOGIN USER
       ========================= */
    @Override
    public User findByUsernameAndPassword(String username, String password) {

        String sql = "SELECT user_id, username, password, role, status, is_active " +
                "FROM users " +
                "WHERE username = ? AND password = ? AND is_active = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return User.builder()
                        .userId(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .role(rs.getString("role"))
                        .status(rs.getString("status"))
                        .isActive(rs.getBoolean("is_active"))
                        .build();
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Login failed", e);
        }
    }
}
