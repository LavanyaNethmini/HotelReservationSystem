package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<User> findAll() {

        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY username";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapUser(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void delete(int userId) {

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }


    @Override
    public void resetPassword(int userId, String newPassword) {

        String sql = "UPDATE users SET password=? WHERE user_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error resetting password", e);
        }
    }


    private User mapUser(ResultSet rs) throws SQLException {

        return User.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .fullName(rs.getString("full_name"))
                .contactNo(rs.getString("contact_no"))
                .address(rs.getString("address"))
                .role(rs.getString("role"))
                .status(rs.getString("status"))
                .isActive(rs.getBoolean("is_active"))
                .build();
    }


    @Override
    public User findById(int id) {

        String sql = "SELECT * FROM users WHERE user_id=?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return User.builder()
                        .userId(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .fullName(rs.getString("full_name"))
                        .contactNo(rs.getString("contact_no"))
                        .address(rs.getString("address"))
                        .role(rs.getString("role"))
                        .status(rs.getString("status"))
                        .isActive(rs.getBoolean("is_active"))
                        .build();
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void update(User user) {

        String sql = "UPDATE users SET " +
                "username=?, password=?, full_name=?, contact_no=?, " +
                "address=?, role=?, status=?, is_active=? " +
                "WHERE user_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getContactNo());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getStatus());
            ps.setBoolean(8, user.isActive());
            ps.setInt(9, user.getUserId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
