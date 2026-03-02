package com.hotel.reservation.infrastructure;

import java.sql.Connection;

public class DBTest {

    public static void main(String[] args) {

        try {
            DBConnection db = DBConnection.getInstance();
            Connection connection = db.getConnection();

            if (connection != null && !connection.isClosed()) {
                System.out.println("✅ DB Connected Successfully");
            } else {
                System.out.println("❌ DB Connection Failed");
            }

        } catch (Exception e) {
            System.out.println("❌ Exception while connecting to DB");
            e.printStackTrace();
        }
    }
}
