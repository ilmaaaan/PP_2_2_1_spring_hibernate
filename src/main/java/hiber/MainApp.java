package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User userOne = new User("User1", "Lastname1", "user1@mail.ru");
      User userTwo = new User("User2", "Lastname2", "user2@mail.ru");
      User userThree = new User("User3", "Lastname3", "user3@mail.ru");
      User userFour = new User("User4", "Lastname4", "user4@mail.ru");

      Car carOne = new Car("Toyota", 111);
      Car carTwo = new Car("Mazda", 222);
      Car carThree = new Car("Lada", 333);
      Car carFour = new Car("UAZ", 444);

      userService.add(userOne, carOne);
      userService.add(userTwo, carTwo);
      userService.add(userThree, carThree);
      userService.add(userFour, carFour);

      System.out.println(userService.getUserByCar("Toyota", 111));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) { System.out.println("Car = " + user.getCar().getModel()); }
         System.out.println();
      }
      context.close();
   }
}