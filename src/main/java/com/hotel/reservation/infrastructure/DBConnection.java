package com.hotel.reservation.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private static final String URL =
            "jdbc:mysql://localhost:3308/ocen_view_resort_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    // Private constructor (Singleton)
    private DBConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    // Singleton instance access
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    // Expose connection
    public Connection getConnection() {
        return connection;
    }
}
