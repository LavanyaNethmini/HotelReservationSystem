package com.hotel.reservation.repository.impl;

import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.infrastructure.DBConnection;
import com.hotel.reservation.repository.BillRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BillRepositoryImpl implements BillRepository {

    private final Connection connection =
            DBConnection.getInstance().getConnection();

    @Override
    public void save(Bill bill) {

        String sql = "INSERT INTO bills " +
                "(reservation_id, nights, room_rate, total_amount, payment_method, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, bill.getReservationId());
            ps.setInt(2, bill.getNights());
            ps.setBigDecimal(3, bill.getRoomRate());
            ps.setBigDecimal(4, bill.getTotalAmount());
            ps.setString(5, bill.getPaymentMethod());
            ps.setInt(6, bill.getCreatedBy());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving bill", e);
        }
    }
}
