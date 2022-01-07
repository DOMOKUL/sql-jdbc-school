package com.company.sql.jdbc.school.util.database.implementation;

import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.ConnectionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentsFiller {

    private static final String SAVE_SQL = """
            INSERT INTO students(student_id, group_id, first_name, last_name)
            VALUES (?, ?, ?, ?);
            """;

    public void fillTableStudents(String studentsFirstName, String studentsLastName) {
        List<String> studentsFirstNameList = parseStudentName(Path.of(studentsFirstName));
        List<String> studentsLastNameList = parseStudentName(Path.of(studentsLastName));
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 1; i <= 200; i++) {

                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, (int) (Math.random() * (20 - 1 + 1)) + 1);
                preparedStatement.setString(3, randomizeStudentName(studentsFirstNameList));
                preparedStatement.setString(4, randomizeStudentName(studentsLastNameList));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException cause) {
            throw new DaoException("fill table students fail ", cause);
        }
    }

    private List<String> parseStudentName(Path filePath) {
        List<String> result = new ArrayList<>();
        try (Stream<String> lineStream = Files.lines(filePath)) {
            result = lineStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String randomizeStudentName(List<String> nameList) {
        var result = "";
        Random rand = new Random();
        var randomElement = nameList.get(rand.nextInt(nameList.size()));

        return randomElement;
    }
}
