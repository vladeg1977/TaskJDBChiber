package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age INT)";
        Statement statement;
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица создана");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Таблица не создана");
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        Statement statement;
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Таблица не удалена");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        String sql = "INSERT users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User не добавлен");
        }

    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User с номером - " + id + " удалён");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User не удалён");
        }


    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastname, age FROM users";
        Statement statement;
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                System.out.println("Все Users получены из базы");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Users не получены из базы");
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        PreparedStatement preparedStatement;
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Таблица полностью очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Таблица не очищена");
        }

    }
}
