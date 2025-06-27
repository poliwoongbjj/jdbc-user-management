package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/database_name";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "<PASSWORD>";

    // JDBC driver
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Private constructor
    private Util() {

    }

    // Method to get database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the driver
            Class.forName(DRIVER);

            // Establish connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection to database failed!");
            e.printStackTrace();
        }
        return connection;
    }
}
