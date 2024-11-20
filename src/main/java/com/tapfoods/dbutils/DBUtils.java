package com.tapfoods.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final public class DBUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Thiru@07";
    private static Connection con = null;

    private DBUtils() {}

    public static Connection myConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Database connection established successfully.");
            } else {
                System.out.println("Using existing database connection.");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error while connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error while closing the database connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
