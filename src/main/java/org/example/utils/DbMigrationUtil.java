package org.example.utils;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class DbMigrationUtil {
    public static void runMigrations(String dbUrl, String dbUser, String dbPassword) {
        log.info("Запуск миграций...");

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);

            Database database = new PostgresDatabase();
            database.setConnection(jdbcConnection);

            Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.yaml",
                    new ClassLoaderResourceAccessor(),
                    database);

            liquibase.update("");
            log.info("Миграции успешно применены");
        } catch (Exception exception) {
            log.error("Ошибка при выполнении миграций: {}", exception.getMessage());
            throw new RuntimeException("Не удалось применить миграции", exception);
        }
    }
}
