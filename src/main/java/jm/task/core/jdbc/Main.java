package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Юлия", "Попова", (byte) 25);
        userService.saveUser("Владимир", "Дегтярёв", (byte) 44);
        userService.saveUser("Максим", "Горьков", (byte) 37);
        userService.saveUser("Валентина", "Трескунова", (byte) 11);
        List<User> list = userService.getAllUsers();
        for (User user: list) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
