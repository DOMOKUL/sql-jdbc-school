package com.company.sql.jdbc.school.util;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SqlScriptRunnerTest {

    private final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");

    @Test
    void runSqlScript_shouldIOException_whenInputEmptyLine() throws IOException, SQLException {
            container.start();
            var dataSource = new DataSource(container.getJdbcUrl(), container.getDatabaseName(), container.getPassword());
            SqlScriptRunner sqlScriptRunner = new SqlScriptRunner(dataSource.getConnection());
            sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("init.sql")));

            assertThrows(IOException.class,()->
                    sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader(""))));
    }
}