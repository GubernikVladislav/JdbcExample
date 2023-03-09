package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для доступа к базе данных
 */
public class DbConnector {

    private DbConnector() {
    }

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres"; //URL базы данных по-умолчанию в PostgreSQL, если при установке не менялись никакие параметры
    private static final String DB_USER = "postgres"; //имя учетной записи по-умолчанию в PostgreSQL

    private static final String DB_PASSWORD = "admin"; //тут может потребоваться сменить пароль на указанный при установки PostgreSQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
