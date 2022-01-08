package com.company.sql.jdbc.school.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class DataSource {

    private static HikariConfig config = new HikariConfig(
            "src/main/resources/application.properties");
    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource(config);
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}