package com.company.sql.jdbc.school.util.database.implementation;

import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.database.DataBaseFiller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CoursesFiller implements DataBaseFiller {

    private static final String SAVE_SQL = """
            INSERT INTO courses(course_id, name,description)
            VALUES (?, ?, ?);
            """;

    @Override
    public void fillDatabase(String filePath) throws FileNotFoundException {
        LinkedHashMap<String,String> courses = parseCourseName(Path.of(filePath));
        int i=1;
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            for (Map.Entry<String,String> entry: courses.entrySet()) {

                preparedStatement.setInt(1, i++);
                preparedStatement.setString(2, entry.getKey() );
                preparedStatement.setString(3, entry.getValue());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException cause) {
            throw new DaoException("fill table courses fail ");
        }
    }

    private LinkedHashMap<String,String> parseCourseName(Path filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(String.valueOf(filePath)));
        LinkedHashMap<String,String> result = new LinkedHashMap<>();
        while (scanner.hasNextLine()) {
            String[] columns = scanner.nextLine().split("-");
            result.put(columns[0],columns[1]);
        }
        return result;
    }
}
