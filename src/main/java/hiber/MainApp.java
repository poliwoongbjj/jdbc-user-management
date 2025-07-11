package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 5)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Audi", 6)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Mercedes", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Toyota", 7)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         }
         System.out.println();
      }

      // Test the HQL query method
      System.out.println("=== Testing HQL Query ===");
      try {
         User userWithBMW = userService.getUserByCarModelAndSeries("BMW", 5);
         System.out.println("User with BMW 5 series: " + userWithBMW.getFirstName() + " " + userWithBMW.getLastName());
      } catch (Exception e) {
         System.out.println("No user found with BMW 5 series: " + e.getMessage());
      }

      try {
         User userWithAudi = userService.getUserByCarModelAndSeries("Audi", 6);
         System.out.println("User with Audi 6 series: " + userWithAudi.getFirstName() + " " + userWithAudi.getLastName());
      } catch (Exception e) {
         System.out.println("No user found with Audi 6 series: " + e.getMessage());
      }

      // Test with a non-existent car
      try {
         User userWithFerrari = userService.getUserByCarModelAndSeries("Ferrari", 1);
         System.out.println("User with Ferrari 1 series: " + userWithFerrari.getFirstName() + " " + userWithFerrari.getLastName());
      } catch (Exception e) {
         System.out.println("No user found with Ferrari 1 series: " + e.getMessage());
      }

      context.close();
   }
}