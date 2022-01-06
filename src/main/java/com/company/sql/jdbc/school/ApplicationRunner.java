package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationRunner {

    public static void main(String[] args) throws SQLException {
        Class<Driver> driverClass = Driver.class;
        String sql = """
                CREATE DATABASE 
                """;
        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement();) {
            System.out.println(connection.getTransactionIsolation());
statement.execute("");
        }

    }
}
