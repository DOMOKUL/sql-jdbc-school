package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.ConnectionManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseDao {

    private static final CourseDao INSTANCE = new CourseDao();
    private static final String SAVE_SQL = """
            INSERT INTO courses(course_id, name,description)
            VALUES (?, ?, ?);
            """;

    private CourseDao() {
    }

    public static CourseDao getInstance() {
        return INSTANCE;
    }

    public Course createCourse(Course course) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setString(3, course.getCourseDescription());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setCourseId(generatedKeys.getInt("course_id"));
            }
            return course;

        } catch (SQLException throwable) {
            throw new DaoException("Creating course fail", throwable);
        }
    }

    public void fillTableCourse(String filePath) throws FileNotFoundException {
        LinkedHashMap<String,String> courses = parseCourseName(Path.of(filePath));
        int i=1;
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            for (Map.Entry<String,String> entry: courses.entrySet()) {

                preparedStatement.setInt(1, i++);
                preparedStatement.setString(2, entry.getKey() );
                preparedStatement.setString(3, entry.getValue());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwable) {
            throw new DaoException("fill table courses fail ", throwable);
        }
    }

    public void addStudentToTheCourse() {
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
