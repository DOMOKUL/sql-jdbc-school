package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.ConnectionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDao {

    private static final StudentDao INSTANCE = new StudentDao();
    private static final String DELETE_SQL = """
            DELETE FROM students
            WHERE student_id=?
            """;
    private static final String FIND_ALL_SQL = """
             SELECT public.students.student_id,
             public.students.group_id,
             public.students.first_name,
             public.students.last_name
             FROM public.students
            """;
    private static final String FIND_ALL_STUDENTS_BY_COURSE = """
            SELECT first_name, last_name
            FROM students JOIN students_courses USING(student_id)
            WHERE course_id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO students(student_id, group_id, first_name, last_name)
            VALUES (?, ?, ?, ?);
            """;

    private StudentDao() {
    }

    public static StudentDao getInstance() {
        return INSTANCE;
    }

    public void fillTableStudents(String studentsFirstName, String studentsLastName) {
        List<String> studentsFirstNameList = parseStudentName(Path.of(studentsFirstName));
        List<String> studentsLastNameList = parseStudentName(Path.of(studentsLastName));
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 1; i <= 200; i++) {

                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, (int) (Math.random() * (20 - 1 + 1)) + 1 );
                preparedStatement.setString(3, randomizeStudentName(studentsFirstNameList));
                preparedStatement.setString(4, randomizeStudentName(studentsLastNameList));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwable) {
            throw new DaoException("fill table students fail ", throwable);
        }
    }

    public void deleteStudentById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throw new DaoException("Delete student fail", throwable);
        }
    }

//    public void addNewStudent() {
//        try (var connection = ConnectionManager.get();
//             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.
//        } catch (SQLException throwable) {
//            throw new DaoException("Creating student fail", throwable);
//        }
//    }

    public List<Student> findAllStudentsWithGivenCourse(Course course) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_STUDENTS_BY_COURSE)) {
            preparedStatement.setInt(1, course.getCourseId());
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException throwable) {
            throw new DaoException("Getting students from course fail ", throwable);
        }
    }

    public void removeStudentFromCourse() {
    }

    public List<Student> showAllStudents() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException throwable) {
            throw new DaoException("Show students fail", throwable);
        }

    }

    private Student buildStudent(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("student_id"),
                resultSet.getInt("group_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
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
