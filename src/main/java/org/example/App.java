package org.example;

import org.example.configuration.MyConfig;
import org.example.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication",
                Communication.class);

        communication.getAllUsers();

        User user = new User(3L, "James", "Brown", (byte) 25);
        communication.saveUser(user);

        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.updateUser(user);

        communication.deleteUser(3L);
    }
}
