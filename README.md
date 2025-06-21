# Spring Dependency Injection Assignment

A Spring Framework project demonstrating automatic dependency injection using `@Autowired`, `@Qualifier`, and component scanning.

## Project Overview

This project showcases Spring's dependency injection capabilities:
- **@Autowired**: Automatic dependency injection
- **@Qualifier**: Resolving ambiguity when multiple beans of same type exist
- **@Component**: Automatic bean discovery through component scanning
- **Singleton behavior**: Same bean instance injected across multiple retrievals

## Project Structure

```
src/
├── main/java/
│   └── app/
│       ├── Application.java           # Main application class
│       ├── config/
│       │   └── AppConfig.java         # Spring configuration with component scanning
│       └── model/
│           ├── Animal.java            # Abstract base class
│           ├── Cat.java               # Cat component (extends Animal)
│           ├── Dog.java               # Dog component (extends Animal)
│           ├── AnimalsCage.java       # Main component with dependencies
│           ├── Timer.java             # Timer component (singleton)
│           └── DemoException.java     # Demonstrates NoUniqueBeanDefinitionException
└── test/java/
    └── AppTest.java                   # JUnit test verifying singleton behavior
```

## Key Components

### Classes and Their Roles

- **Application.java**: Main class that retrieves AnimalsCage bean 5 times
- **AppConfig.java**: Configuration class with `@ComponentScan` for automatic bean discovery
- **Animal.java**: Abstract base class for animals
- **Cat.java & Dog.java**: Concrete Animal implementations marked with `@Component`
- **AnimalsCage.java**: Main component that demonstrates dependency injection
- **Timer.java**: Singleton component that provides consistent timestamp
- **AppTest.java**: Test that verifies Timer singleton behavior

### Dependency Injection Configuration

```java
@Component
public class AnimalsCage {
    
    @Autowired
    @Qualifier("dog")           // Resolves ambiguity between Cat and Dog
    private Animal animal;
    
    @Autowired                  // Injects singleton Timer bean
    private Timer timer;
}
```

## The NoUniqueBeanDefinitionException Problem

When you have multiple beans of the same type (Cat and Dog both extend Animal), Spring throws `NoUniqueBeanDefinitionException` because it doesn't know which one to inject.

### Solution: Using @Qualifier

```java
@Autowired
@Qualifier("dog")  // Specifies to inject the "dog" bean specifically
private Animal animal;
```

Bean names default to the class name with lowercase first letter:
- `Cat` class → `"cat"` bean name
- `Dog` class → `"dog"` bean name

## How to Run

### Run the Application
```bash
mvn compile exec:java -Dexec.mainClass="app.Application"
```

### Run the Tests
```bash
mvn test
```

## Expected Output

When running `Application.java`, you should see:
```
Say:
Im a Dog
At:
[timestamp - same for all 5 iterations]
________________________
Say:
Im a Dog
At:
[same timestamp as above]
________________________
[repeats 5 times with same timestamp]
```

## What This Demonstrates

1. **Component Scanning**: `@ComponentScan` automatically discovers `@Component` classes
2. **Dependency Injection**: `@Autowired` automatically injects dependencies
3. **Qualifier Resolution**: `@Qualifier` resolves ambiguity between multiple beans of same type
4. **Singleton Behavior**: Same Timer instance is injected every time, maintaining same timestamp
5. **Polymorphism**: Animal reference holds Dog instance through dependency injection

## Technologies Used

- **Java 17**
- **Spring Framework 5.3.14** (Core + Context)
- **Maven** for dependency management
- **JUnit 4** for testing

## Learning Objectives

- Understand `@Autowired` annotation for automatic dependency injection
- Learn to resolve bean ambiguity using `@Qualifier`
- Practice component scanning with `@ComponentScan`
- Observe singleton behavior in Spring beans
- Write tests to verify dependency injection behavior

## Dependencies

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.3.14</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.14</version>
</dependency>
```

## Test Verification

The JUnit test (`AppTest.java`) verifies that:
- The same Timer instance is injected into AnimalsCage every time
- Timer maintains the same timestamp across multiple bean retrievals
- Singleton behavior works correctly with dependency injection

The test retrieves AnimalsCage bean 5 times and ensures the Timer's timestamp remains constant, proving it's the same singleton instance.