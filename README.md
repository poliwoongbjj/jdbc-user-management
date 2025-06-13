# JDBC & Hibernate User Management Application

A Java application demonstrating database operations using both JDBC and Hibernate implementations with MySQL.

## Project Description

This project implements a user management system that performs CRUD (Create, Read, Update, Delete) operations on a MySQL database. The application showcases two different approaches to data persistence:
- **JDBC** (Java Database Connectivity) - Direct SQL execution
- **Hibernate** - Object-Relational Mapping (ORM) framework

The application follows a layered architecture pattern with Model, DAO, and Service layers, demonstrating how to switch between different data access implementations seamlessly.

## Features

- Dual implementation (JDBC and Hibernate) with same interface
- Create and drop database tables
- Add users to the database
- Retrieve all users
- Remove users by ID
- Clear table contents
- Unit tests for all operations
- Easy switching between JDBC and Hibernate implementations

## Technologies Used

- Java 8+
- JDBC
- Hibernate 5.6.3
- MySQL 8.0
- JUnit 4
- Maven

## Project Structure

```
src/
├── main/
│   └── java/
│       └── jm/task/core/jdbc/
│           ├── Main.java
│           ├── dao/
│           │   ├── UserDao.java (Interface)
│           │   ├── UserDaoJDBCImpl.java
│           │   └── UserDaoHibernateImpl.java
│           ├── model/
│           │   └── User.java
│           ├── service/
│           │   ├── UserService.java (Interface)
│           │   └── UserServiceImpl.java
│           └── util/
│               └── Util.java
└── test/
    └── java/
        └── UserServiceTest.java
```

## Architecture Overview

### Layered Architecture

1. **Model Layer** (`User.java`)
   - Entity class with JPA annotations
   - Represents the database table structure

2. **DAO Layer** (Data Access Object)
   - `UserDao` interface - Defines the contract
   - `UserDaoJDBCImpl` - JDBC implementation using PreparedStatement
   - `UserDaoHibernateImpl` - Hibernate implementation using Sessions

3. **Service Layer**
   - `UserService` interface - Business logic contract
   - `UserServiceImpl` - Delegates to DAO and handles business logic

4. **Utility Layer** (`Util.java`)
   - Database connection for JDBC
   - SessionFactory configuration for Hibernate

### Design Patterns Used

- **Interface-based programming**: Easy swapping of implementations
- **Singleton Pattern**: SessionFactory instance
- **Dependency Injection**: Manual injection in Service layer
- **Layered Architecture**: Clear separation of concerns

## Prerequisites

1. Java Development Kit (JDK) 8 or higher
2. MySQL Server 8.0 or higher
3. MySQL Workbench (recommended)
4. Maven 3.6+ (for dependency management)
5. IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Setup Instructions

### 1. Database Setup

1. Install MySQL Server and MySQL Workbench
2. Open MySQL Workbench and create a new connection
3. Create a new schema/database:
   ```sql
   CREATE DATABASE jdbc_task;
   USE jdbc_task;
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

   import jm.task.core.jdbc.model.User;
   import org.hibernate.SessionFactory;
   import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
   import org.hibernate.cfg.Configuration;
   import org.hibernate.cfg.Environment;
   import org.hibernate.service.ServiceRegistry;

   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;
   import java.util.Properties;

   public class Util {
       // Update these with your actual database credentials
       private static final String URL = "jdbc:mysql://localhost:3306/jdbc_hibernate_task";
       private static final String USERNAME = "your_username";
       private static final String PASSWORD = "your_password";
       private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
       
       private static SessionFactory sessionFactory;
       
       // JDBC connection method
       public static Connection getConnection() {
           // Implementation here
       }
       
       // Hibernate SessionFactory method
       public static SessionFactory getSessionFactory() {
           // Implementation here
       }
       
       public static void closeSessionFactory() {
           // Implementation here
       }
   }
   ```

3. Update Maven dependencies in `pom.xml`:
   ```xml
   <dependencies>
       <!-- JUnit -->
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.13.2</version>
           <scope>test</scope>
       </dependency>
       
       <!-- MySQL Connector -->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>8.0.25</version>
       </dependency>
       
       <!-- Hibernate -->
       <dependency>
           <groupId>org.hibernate</groupId>
           <artifactId>hibernate-core</artifactId>
           <version>5.6.3.Final</version>
       </dependency>
       
       <!-- JPA API -->
       <dependency>
           <groupId>org.hibernate.javax.persistence</groupId>
           <artifactId>hibernate-jpa-2.1-api</artifactId>
           <version>1.0.2.Final</version>
       </dependency>
   </dependencies>
   ```

### 3. Switching Between Implementations

To switch between JDBC and Hibernate, modify the `UserServiceImpl.java`:

**For JDBC:**
```java
private final UserDao userDao = new UserDaoJDBCImpl();
```

**For Hibernate (current):**
```java
private final UserDao userDao = new UserDaoHibernateImpl();
```

### 4. Running the Application

1. **Run tests** to verify everything works:
   ```bash
   mvn test
   ```
   Or right-click on `UserServiceTest.java` in your IDE and select "Run"

2. **Run the main application**:
   ```bash
   mvn compile exec:java -Dexec.mainClass="jm.task.core.jdbc.Main"
   ```
   Or run `Main.java` from your IDE

## Application Algorithm

The main method performs the following operations:

1. **Creates** a table for Users
2. **Adds** 4 Users to the database (with console output after each addition)
3. **Retrieves** and prints all Users from the database
4. **Clears** the Users table
5. **Drops** the Users table

### Expected Output

```
User с именем – John добавлен в базу данных
User с именем – Jane добавлен в базу данных
User с именем – Bob добавлен в базу данных
User с именем – Alice добавлен в базу данных

All users in database:
User{id=1, name='John', lastName='Doe', age=25}
User{id=2, name='Jane', lastName='Smith', age=30}
User{id=3, name='Bob', lastName='Johnson', age=35}
User{id=4, name='Alice', lastName='Williams', age=28}

Table cleared
Table dropped
```

When using Hibernate, you'll also see SQL queries in the console due to `show_sql=true`.

## Key Differences: JDBC vs Hibernate

### JDBC Implementation
- Direct SQL queries
- Manual parameter binding
- Manual result set mapping
- More control over SQL

### Hibernate Implementation
- Object-Relational Mapping
- Automatic SQL generation
- Entity state management
- Built-in caching
- HQL and Criteria API

## Testing

The project includes comprehensive JUnit tests that verify:
- Table creation and deletion
- User insertion and retrieval
- User deletion by ID
- Table clearing functionality

All tests work with both JDBC and Hibernate implementations.

## Troubleshooting

### Common Issues

1. **Connection refused**
   - Ensure MySQL is running
   - Check port 3306 is not blocked
   - Verify credentials in `Util.java`

2. **Unknown database**
   - Create the database first (see Database Setup)

3. **Access denied**
   - Check username/password
   - Grant necessary privileges:
   ```sql
   GRANT ALL PRIVILEGES ON jdbc_task.* TO 'your_user'@'localhost';
   ```

4. **Hibernate dialect issues**
   - For MySQL 5.x use: `MySQL5Dialect`
   - For MySQL 8.x use: `MySQL8Dialect`

## Performance Considerations

- **JDBC**: Generally faster for simple operations
- **Hibernate**: Better for complex object graphs and relationships
- **SessionFactory**: Created once (expensive operation)
- **Sessions**: Created per operation (lightweight)

## Security Note

The `Util.java` file containing database credentials is excluded from version control. Always keep your database credentials private and never commit them to a public repository.

## Future Enhancements

- Add connection pooling (HikariCP)
- Implement service-level transactions
- Add logging framework (SLF4J/Logback)
- Create REST API endpoints
- Add more complex relationships
- Implement caching strategies

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Author

Solution to the project provided by Woong Kim

## License

This project is created by Habsida for educational purposes as part of learning database operations with Java.

## Acknowledgments

- JM Task - For providing the assignment structure
- Hibernate team - For the excellent ORM framework
- MySQL team - For the reliable database system