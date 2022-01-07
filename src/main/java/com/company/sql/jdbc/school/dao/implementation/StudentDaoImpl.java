package com.company.sql.jdbc.school.dao.implementation;

import com.company.sql.jdbc.school.dao.StudentDao;
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
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDaoImpl implements StudentDao {

    private static final StudentDaoImpl INSTANCE = new StudentDaoImpl();
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

    private StudentDaoImpl() {
    }

    public static StudentDaoImpl getInstance() {
        return INSTANCE;
    }


    //    public void createStudent() {
//        try (var connection = ConnectionManager.get();
//             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.
//        } catch (SQLException throwable) {
//            throw new DaoException("Creating student fail", throwable);
//        }
//    }
    @Override
    public List<Student> findStudentsByCourseName(Course course) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_STUDENTS_BY_COURSE)) {
            preparedStatement.setInt(1, course.courseId());
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException cause) {
            throw new DaoException("Getting students from course fail ", cause);
        }
    }

    @Override
    public void create(Student t) {

    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public void update(Student t) {

    }

    @Override
    public void delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException cause) {
            throw new DaoException("Delete student fail", cause);
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
}
