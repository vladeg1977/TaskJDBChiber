package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age INT)";
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Таблица не создана");
        }

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Таблица не удалена");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction;
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("User не добавлен");
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction;
        User user = new User();
        user.setId(id);

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            System.out.println("User с номером - " + id + " удалён");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("User не удалён");
        }

    }

    @Override
    public  List<User> getAllUsers() {
        Transaction transaction;
        List<User>users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User ").list();
            transaction.commit();
            System.out.println("Все Users получены из базы");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Users не получены из базы");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User ").executeUpdate();
            transaction.commit();
            System.out.println("Таблица полностью очищена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Таблица не очищена");
        }

    }
}
