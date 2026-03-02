package com.hotel.reservation.service.impl;

import com.hotel.reservation.service.DashboardService;
import com.hotel.reservation.dao.DashboardDAO;

import java.sql.SQLException;

public class DashboardServiceImpl implements DashboardService {

    private final DashboardDAO dashboardDAO = new DashboardDAO();

    @Override
    public int getTodayReservations() throws SQLException {
        return dashboardDAO.getTodayReservations();
    }

    @Override
    public int getTotalGuests() throws SQLException {
        return dashboardDAO.getTotalGuests();
    }

    @Override
    public int getAvailableRoomsToday() throws SQLException {
        return dashboardDAO.getAvailableRoomsToday();
    }
}