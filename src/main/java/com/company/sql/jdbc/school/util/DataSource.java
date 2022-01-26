package com.company.sql.jdbc.school.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class DataSource {

    private final HikariDataSource ds;

    private final HikariConfig config;

    public DataSource() {
        config = new HikariConfig("src/main/resources/application.properties");
        ds = new HikariDataSource(config);
    }

    public DataSource(String jdbcUrl, String databaseUsername, String databasePassword) {
        Properties properties = new Properties();
        properties.setProperty("jdbcUrl", jdbcUrl);
        properties.setProperty("username", databaseUsername);
        properties.setProperty("password", databasePassword);
        this.config = new HikariConfig(properties);
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}