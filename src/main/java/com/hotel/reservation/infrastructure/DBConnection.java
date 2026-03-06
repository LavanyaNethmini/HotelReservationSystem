package com.hotel.reservation.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private static final String URL =
            "jdbc:mysql://localhost:3308/ocen_view_resort_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123"; // or your password

    private DBConnection() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("DB Connected from Tomcat");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
