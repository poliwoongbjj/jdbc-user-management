# Spring Bean Scopes Assignment

A simple Spring Framework project demonstrating different bean scopes (singleton vs prototype) using Java-based configuration.

## Project Overview

This project contains a basic Spring application that showcases how Spring manages beans with different scopes:
- **Singleton scope**: One instance per Spring container (default behavior)
- **Prototype scope**: New instance created on each request

## Project Structure

```
src/
├── main/java/
│   ├── App.java           # Main application class
│   ├── AppConfig.java     # Spring configuration class
│   ├── HelloWorld.java    # Simple POJO (singleton bean)
│   └── Cat.java           # Simple POJO (prototype bean)
└── test/java/
    └── AppTest.java       # JUnit test to verify bean behavior
```

## Key Components

### Classes

- **App.java**: Contains the main method that demonstrates bean retrieval and comparison
- **AppConfig.java**: Spring configuration class with `@Configuration` annotation
- **HelloWorld.java**: Simple POJO with message property (singleton scope)
- **Cat.java**: Simple POJO with name and color properties (prototype scope)
- **AppTest.java**: JUnit test that verifies correct bean scope behavior

### Bean Configurations

```java
@Bean(name="helloworld")           // Singleton scope (default)
public HelloWorld getHelloWorld()

@Bean(name="cat")                  // Prototype scope
@Scope("prototype")
public Cat getCat()
```

## How to Run

### Run the Application
```bash
mvn compile exec:java -Dexec.mainClass="App"
```

### Run the Tests
```bash
mvn test
```

## Expected Output

When running `App.java`, you should see:
```
Hello World!
HelloWorld beans are the same reference: true
Cat beans are the same reference: false
HelloWorld bean hashCode: [same number] and [same number]
Cat bean hashCode: [different number] and [different number]
```

## What This Demonstrates

1. **Singleton Beans**: HelloWorld beans return `true` for reference comparison because Spring returns the same instance
2. **Prototype Beans**: Cat beans return `false` for reference comparison because Spring creates a new instance each time

## Technologies Used

- **Java 17**
- **Spring Framework 5.3.14**
- **Maven** for dependency management
- **JUnit 4** for testing

## Learning Objectives

- Understand Spring bean scopes
- Configure beans using Java-based configuration
- Use `@Configuration` and `@Bean` annotations
- Apply `@Scope` annotation for prototype beans
- Write tests to verify Spring container behavior

## Dependencies

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.14</version>
</dependency>
```

## Test Verification

The JUnit test (`AppTest.java`) automatically verifies:
- HelloWorld beans are the same reference (singleton)
- Cat beans are different references (prototype)

Both assertions must pass for the assignment to be considered complete.