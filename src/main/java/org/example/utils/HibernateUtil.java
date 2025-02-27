package org.example.utils;

import lombok.Getter;
import org.example.model.Genre;
import org.example.model.Media;
import org.example.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    @Getter
    private  static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.username", EnvLoader.getEnv("DB_USER"));
            configuration.setProperty("hibernate.connection.password", EnvLoader.getEnv("DB_PASSWORD"));
            configuration.setProperty("hibernate.connection.url", EnvLoader.getEnv("DB_URL"));

            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

            configuration.addAnnotatedClass(Media.class);
            configuration.addAnnotatedClass(Genre.class);
            configuration.addAnnotatedClass(User.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
