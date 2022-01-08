package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.util.DataSource;
import org.postgresql.Driver;

import java.sql.SQLException;

public class ApplicationRunner {

    public static void main(String[] args) throws SQLException {
        Class<Driver> driverClass = Driver.class;
        String sql = """
                CREATE DATABASE 
                """;
        try (var connection = DataSource.getConnection();
             var statement = connection.createStatement();) {
            System.out.println(connection.getTransactionIsolation());
statement.execute("");
        }

    }
}
