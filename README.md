# JDBC User Management Application

A Java application demonstrating database operations using JDBC with MySQL.

## Project Description

This project implements a simple user management system that performs CRUD (Create, Read, Update, Delete) operations on a MySQL database using JDBC. The application follows a layered architecture pattern with Model, DAO, and Service layers.

## Features

- Create and drop database tables
- Add users to the database
- Retrieve all users
- Remove users by ID
- Clear table contents
- Unit tests for all operations

## Technologies Used

- Java 8+
- JDBC
- MySQL 8.0
- JUnit 4
- Maven (optional)

## Project Structure

```
src/
├── main/
│   └── java/
│       └── jm/task/core/jdbc/
│           ├── Main.java
│           ├── dao/
│           │   ├── UserDao.java
│           │   ├── UserDaoJDBCImpl.java
│           │   └── UserDaoHibernateImpl.java
│           ├── model/
│           │   └── User.java
│           ├── service/
│           │   ├── UserService.java
│           │   └── UserServiceImpl.java
│           └── util/
│               └── Util.java
└── test/
    └── java/
        └── UserServiceTest.java
```

## Prerequisites

1. Java Development Kit (JDK) 8 or higher
2. MySQL Server 8.0 or higher
3. MySQL Workbench (recommended)
4. Maven (optional, for dependency management)

## Setup Instructions

### 1. Database Setup

1. Install MySQL Server and MySQL Workbench
2. Open MySQL Workbench and create a new connection
3. Create a new schema/database:
   ```sql
   CREATE DATABASE jdbc_task;
   ```

### 2. Project Configuration

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/jdbc-user-management.git
   cd jdbc-user-management
   ```

2. Create a `Util.java` file in `src/main/java/jm/task/core/jdbc/util/` with your database credentials:
   ```java
   package jm.task.core.jdbc.util;

   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;

   public class Util {
       private static final String URL = "jdbc:mysql://localhost:3306/jdbc_task";
       private static final String USERNAME = "your_username";
       private static final String PASSWORD = "your_password";
       private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
       
       public static Connection getConnection() {
           Connection connection = null;
           try {
               Class.forName(DRIVER);
               connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           } catch (ClassNotFoundException | SQLException e) {
               e.printStackTrace();
           }
           return connection;
       }
   }
   ```

3. Add MySQL Connector dependency:

   **Using Maven** - Add to `pom.xml`:
   ```xml
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.33</version>
   </dependency>
   ```

   **Without Maven** - Download [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) and add the JAR to your project classpath.

### 3. Running the Application

1. Run the unit tests to verify everything works:
    - Right-click on `UserServiceTest.java`
    - Select "Run UserServiceTest"

2. Run the main application:
    - Right-click on `Main.java`
    - Select "Run Main"

## Application Algorithm

The main method performs the following operations:

1. Creates a table for Users
2. Adds 4 Users to the database (with console output after each addition)
3. Retrieves and prints all Users from the database
4. Clears the Users table
5. Drops the Users table

## Security Note

The `Util.java` file containing database credentials is excluded from version control for security reasons. Always keep your database credentials private and never commit them to a public repository.

## Testing

The project includes JUnit tests that verify:
- Table creation and deletion
- User insertion and retrieval
- User deletion by ID
- Table clearing functionality

All tests should pass when the implementation is correct.

## Author

Solution to the project provided by Woong Kim

## License

This project is created by Habsida for educational purposes.