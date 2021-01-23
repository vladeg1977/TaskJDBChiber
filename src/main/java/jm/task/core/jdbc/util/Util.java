package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=Europe/Moscow";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "panteley";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String SHOW_SQL = "true";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return conn;
    }
    public  static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, DRIVER);
            properties.put(Environment.URL, URL);
            properties.put(Environment.USER, LOGIN);
            properties.put(Environment.PASS, PASSWORD);
            properties.put(Environment.DIALECT, DIALECT);
            properties.put(Environment.SHOW_SQL, SHOW_SQL);
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Соединение установлено");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Соединение не установлено");
        }
        return sessionFactory;

    }
}
