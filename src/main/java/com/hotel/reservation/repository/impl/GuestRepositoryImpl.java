package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.GuestRepository;

import java.sql.*;

public class GuestRepositoryImpl implements GuestRepository {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    @Override
    public Guest findByPhone(String phone) {

        String sql = "SELECT * FROM guests WHERE phone = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Guest(
                        rs.getInt("guest_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Guest guest) {

        String sql = "INSERT INTO guests (name, address, email, phone) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getAddress());
            ps.setString(3, guest.getEmail());
            ps.setString(4, guest.getPhone());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
