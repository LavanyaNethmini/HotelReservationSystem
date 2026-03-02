package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.GuestRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<Guest> findAll() {
        List<Guest> list = new ArrayList<>();
        String sql = "SELECT * FROM guests ORDER BY name";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapGuest(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Guest> search(String keyword) {
        List<Guest> list = new ArrayList<>();
        String sql =
                "SELECT * FROM guests " +
                        "WHERE name LIKE ? OR phone LIKE ? " +
                        "ORDER BY name";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapGuest(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Guest findById(int id) {
        String sql = "SELECT * FROM guests WHERE guest_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapGuest(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(Guest guest) {
        String sql = "UPDATE guests SET " +
                "name=?, phone=?, email=?, address=? " +
                "WHERE guest_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getPhone());
            ps.setString(3, guest.getEmail());
            ps.setString(4, guest.getAddress());
            ps.setInt(5, guest.getGuestId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Guest mapGuest(ResultSet rs) throws SQLException {

        return new Guest(
                rs.getInt("guest_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("phone")
        );

    }


}
