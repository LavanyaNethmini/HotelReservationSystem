package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.Room;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.RoomRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    @Override
    public boolean roomExists(int roomId) {

        String sql = "SELECT room_id FROM rooms WHERE room_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            return ps.executeQuery().next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) {

        String sql =
                "SELECT reservation_id FROM reservations " +
                        "WHERE room_id = ? " +
                        "AND NOT (check_out <= ? OR check_in >= ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.setDate(2, java.sql.Date.valueOf(checkIn));
            ps.setDate(3, java.sql.Date.valueOf(checkOut));

            return !ps.executeQuery().next();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal getRoomRate(int roomId) {

        String sql = "SELECT price_per_night FROM rooms WHERE room_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("price_per_night");
            }

            throw new RuntimeException("Room rate not found");

        } catch (Exception e) {
            throw new RuntimeException("Error fetching room rate", e);
        }
    }

    @Override
    public List<Room> findAll() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Room(
                        rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getBigDecimal("price_per_night"),
                        rs.getBoolean("availability")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Integer> findBookedRoomIds(LocalDate checkIn, LocalDate checkOut) {

        List<Integer> list = new ArrayList<>();

        String sql =
                "SELECT DISTINCT room_id " +
                        "FROM reservations " +
                        "WHERE status <> 'CANCELLED' " +
                        "AND check_in < ? " +
                        "AND check_out > ?";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(checkOut));
            ps.setDate(2, Date.valueOf(checkIn));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("room_id"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void save(String number, String type, double price) {

        String sql = "INSERT INTO rooms (room_number, room_type, price_per_night) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, number);
            ps.setString(2, type);
            ps.setDouble(3, price);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
