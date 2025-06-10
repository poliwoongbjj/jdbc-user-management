package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // 1. Create table for Users
        userService.createUsersTable();

        // 2. Add 4 Users to the table
        userService.saveUser("John", "Doe", (byte) 25);
        userService.saveUser("Jane", "Smith", (byte) 30);
        userService.saveUser("Bob", "Johnson", (byte) 35);
        userService.saveUser("Alice", "Williams", (byte) 28);

        // 3. Get all Users from database and print them
        System.out.println("\nAll users in database:");
        userService.getAllUsers().forEach(System.out::println);

        // 4. Clear the Users table
        userService.cleanUsersTable();
        System.out.println("\nTable cleared");

        // 5. Drop the Users table
        userService.dropUsersTable();
        System.out.println("Table dropped");
    }
}
