package com.company.sql.jdbc.school.util.database.impl;

import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;
import com.company.sql.jdbc.school.util.database.TableFiller;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CoursesFiller implements TableFiller {

    private final DataSource dataSource;

    public CoursesFiller(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void fillDatabase(String filePath) throws IOException {
        LinkedHashMap<String, String> courses = parseCourseName(Path.of(filePath));
        int i = 1;
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a course.sql"))) {
            for (Map.Entry<String, String> entry : courses.entrySet()) {
                preparedStatement.setInt(1, i++);
                preparedStatement.setString(2, entry.getKey());
                preparedStatement.setString(3, entry.getValue());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException cause) {
            throw new DaoException("Fill table courses fail: " + cause);
        } catch (IOException e) {
            throw new DaoException("Troubles with file: ", e);
        }
    }

    private LinkedHashMap<String, String> parseCourseName(Path filePath) throws IOException {
        LinkedHashMap<String, String> result;
        try (Scanner scanner = new Scanner(new FileReader(String.valueOf(filePath)))) {
            result = new LinkedHashMap<>();
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split("-");
                result.put(columns[0], columns[1]);
            }
        }
        return result;
    }
}
