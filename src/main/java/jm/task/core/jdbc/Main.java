package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList;
        userService.createUsersTable();
        userService.saveUser("Владимир", "Дегтярёв", (byte) 44);
        userService.saveUser("Антон", "Иванов", (byte) 63);
        userService.saveUser("Иван", "Пупкин", (byte) 5);
        userService.saveUser("Максим", "Ерохин", (byte) 58);
        userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
