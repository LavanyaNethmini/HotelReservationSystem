package com.hotel.reservation.service;

import java.sql.SQLException;

public interface DashboardService {

    int getTodayReservations()throws SQLException;

    int getTotalGuests() throws SQLException;

    int getAvailableRoomsToday() throws SQLException;
}