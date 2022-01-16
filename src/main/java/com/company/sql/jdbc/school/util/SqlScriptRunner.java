package com.company.sql.jdbc.school.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringJoiner;

public record SqlScriptRunner(Connection connection) {

    public void runSqlScript(Reader reader) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        String line;
        try (BufferedReader lineReader = new BufferedReader(reader)) {
            while ((line = lineReader.readLine()) != null) {
                stringJoiner.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] queries = stringJoiner.toString().split(";");
        for (String s : queries) {
            executeSql(s);
        }
    }

    private void executeSql(String query) {
        try (var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile(query))) {
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException cause) {
            cause.printStackTrace();
        }
    }
}
