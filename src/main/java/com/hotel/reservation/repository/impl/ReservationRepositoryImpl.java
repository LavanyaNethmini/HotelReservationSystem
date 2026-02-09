package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.ReservationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    @Override
    public int save(Reservation reservation) {

        String sql = "INSERT INTO reservations " +
                "(guest_id, room_id, check_in, check_out, status, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, reservation.getGuestId());
            ps.setInt(2, reservation.getRoomId());
            ps.setDate(3, java.sql.Date.valueOf(reservation.getCheckIn()));
            ps.setDate(4, java.sql.Date.valueOf(reservation.getCheckOut()));
            ps.setString(5, reservation.getStatus());
            ps.setInt(6, reservation.getCreatedBy());

            ps.executeUpdate();

            // ===== GET GENERATED RESERVATION ID =====
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

            throw new RuntimeException("Failed to create reservation");

        } catch (Exception e) {
            throw new RuntimeException("Error saving reservation", e);
        }
    }

    @Override
    public List<Reservation> findAll() {

        List<Reservation> list = new ArrayList<>();

        String sql =
                "SELECT \n" +
                        "    r.reservation_id,\n" +
                        "    r.guest_id,\n" +
                        "    r.room_id,\n" +
                        "    r.check_in,\n" +
                        "    r.check_out,\n" +
                        "    r.status,\n" +
                        "    r.created_by,\n" +
                        "    g.name AS name,\n" +
                        "    g.phone AS phone\n" +
                        "FROM reservations r\n" +
                        "JOIN guests g ON r.guest_id = g.guest_id\n" +
                        "ORDER BY r.check_in DESC\n";


        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapReservation(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<Reservation> search(String keyword) {

        List<Reservation> list = new ArrayList<>();

        String sql =
                "SELECT r.*, g.name, g.phone " +
                        "FROM reservations r " +
                        "JOIN guests g ON r.guest_id = g.guest_id " +
                        "WHERE g.name LIKE ? OR g.phone LIKE ?";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapReservation(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<Reservation> findByMonth(int year, int month) {

        List<Reservation> list = new ArrayList<>();

        String sql =
                "SELECT r.*, g.name, g.phone " +
                        "FROM reservations r " +
                        "JOIN guests g ON r.guest_id = g.guest_id " +
                        "WHERE YEAR(r.check_in) = ? AND MONTH(r.check_in) = ?";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, year);
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapReservation(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void cancel(int reservationId) {

        String sql = "UPDATE reservations SET status='CANCELLED' WHERE reservation_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reservationId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Reservation r) {

        String sql =
                "UPDATE reservations SET room_id=?, check_in=?, check_out=? " +
                        "WHERE reservation_id=?";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, r.getRoomId());
            ps.setDate(2, Date.valueOf(r.getCheckIn()));
            ps.setDate(3, Date.valueOf(r.getCheckOut()));
            ps.setInt(4, r.getReservationId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reservation findById(int reservationId) {

        String sql =
                "SELECT r.*, g.name, g.phone " +
                        "FROM reservations r " +
                        "JOIN guests g ON r.guest_id = g.guest_id " +
                        "WHERE r.reservation_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapReservation(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error fetching reservation", e);
        }
    }


    private Reservation mapReservation(ResultSet rs) throws SQLException {
        return new Reservation(
                rs.getInt("reservation_id"),
                rs.getInt("guest_id"),
                rs.getInt("room_id"),
                rs.getDate("check_in").toLocalDate(),
                rs.getDate("check_out").toLocalDate(),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("name"),
                rs.getString("phone")
        );
    }

    @Override
    public void updateStatus(int reservationId, String status) {

        String sql =
                "UPDATE reservations SET status = ? WHERE reservation_id = ?";

        try (PreparedStatement ps =
                     connection.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, reservationId);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to cancel reservation", e);
        }
    }


}
