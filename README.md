# JDBC User Management with Hibernate and Spring

This project demonstrates a simple user management system using Java, Spring, and Hibernate ORM with a MySQL database. It features a one-to-one relationship between `User` and `Car` entities, and showcases basic CRUD operations and HQL queries.

## Features
- **Spring + Hibernate** configuration (Java-based, no XML)
- **MySQL** database integration
- **User** entity with fields: `firstName`, `lastName`, `email`
- **Car** entity with fields: `model`, `series`
- **One-to-one relationship**: Each user owns one car
- **Add and list users with cars**
- **Query user by car model and series** using HQL

## Project Structure
```
src/main/java/hiber/
├── config/         # Spring and Hibernate configuration
├── dao/            # Data access layer (UserDao)
├── model/          # Entity classes (User, Car)
├── service/        # Service layer (UserService)
└── MainApp.java    # Main application entry point
src/main/resources/
└── db.properties   # Database and Hibernate settings
```

## Prerequisites
- Java 8+
- Maven
- MySQL server (running, with a user and password set)

## Setup
1. **Clone the repository**
2. **Configure the database**
   - Create a database named `spring_hiber`:
     ```sql
     CREATE DATABASE spring_hiber;
     ```
   - Update `src/main/resources/db.properties` with your MySQL username and password if needed.
3. **Build the project**
   ```sh
   mvn clean compile
   mvn dependency:copy-dependencies
   ```

## Running the Application
Run the main class using:
```sh
java -cp "target/classes;target/dependency/*" hiber.MainApp
```

## What the Application Does
- Creates several users, each with a car
- Saves them to the database
- Lists all users and their cars
- Demonstrates an HQL query to find a user by car model and series
- Handles the one-to-one relationship automatically

## Example Output
```
Id = 1
First Name = User1
Last Name = Lastname1
Email = user1@mail.ru
Car = BMW 5

Id = 2
First Name = User2
Last Name = Lastname2
Email = user2@mail.ru
Car = Audi 6
...
=== Testing HQL Query ===
User with BMW 5 series: User1 Lastname1
User with Audi 6 series: User2 Lastname2
No user found with Ferrari 1 series: No entity found for query
```

## Notes
- The database schema is dropped and recreated on each run (`hibernate.hbm2ddl.auto=create-drop`). For production, use `update` or `validate`.
- The project uses only the UserService and UserDao for all operations, including car-related queries.
- The `User` class has two constructors: one with and one without a `Car` parameter.

## License
MIT