package com.company.sql.jdbc.school.dao.implementation;

import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    private static final StudentDaoImpl INSTANCE = new StudentDaoImpl();

    private StudentDaoImpl() {
    }

    public static StudentDaoImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public void saveStudentOnCourses(Student student) {

    }

    @Override
    public void deleteStudentFromCourse(Integer studentId, Integer courseId) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete student from course.sql"))) {
        preparedStatement.setInt(1,studentId);
        preparedStatement.setInt(2,courseId);
        preparedStatement.executeUpdate();
        }
        catch (Exception cause) {
            throw new DaoException("delete student from course fail", cause);
        }
    }

    @Override
    public List<Student> findStudentsByCourseName(Course course) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all students by course name.sql"))) {
            preparedStatement.setInt(1, course.courseId());
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (Exception cause) {
            throw new DaoException("Getting students from course fail ", cause);
        }
    }

    @Override
    public void addCourseSet(Student student) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create courses_students.sql"))) {
            for (Integer courseId : student.courses()) {
                preparedStatement.setInt(1, student.studentId());
                preparedStatement.setInt(2, courseId);
                preparedStatement.executeUpdate();
            }
        } catch (Exception cause) {
            throw new DaoException("add course to student fail", cause);
        }
    }

    @Override
    public void create(Student student) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a student.sql"), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, student.studentId());
            preparedStatement.setInt(2, student.groupId());
            preparedStatement.setString(3, student.firstName());
            preparedStatement.setString(4, student.lastName());
            preparedStatement.executeUpdate();

        } catch (Exception cause) {
            throw new DaoException("Creating student fail", cause);
        }
    }

    @Override
    public Object findById(Integer id) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find student by id.sql"))) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Student student = null;
            if (resultSet.next()) {
                student = buildStudent(resultSet);
            }
            return Optional.ofNullable(student);
        } catch (Exception cause) {
            throw new DaoException("find student fail", cause);
        }
    }

    @Override
    public List<Student> findAll() {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all students.sql"));) {
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (Exception cause) {
            throw new DaoException("find student fail", cause);
        }
    }

    @Override
    public void update(Student student) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that update student by id.sql"));) {
            preparedStatement.setInt(1, student.studentId());
            preparedStatement.setInt(2, student.groupId());
            preparedStatement.setString(3, student.firstName());
            preparedStatement.setString(4, student.lastName());
            preparedStatement.executeUpdate();
        } catch (Exception cause) {
            throw new DaoException("update student fail", cause);
        }
    }

    @Override
    public void delete(Integer id) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete student by id.sql"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException cause) {
            throw new DaoException("delete student fail", cause);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Student buildStudent(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("student_id"),
                resultSet.getInt("group_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                Collections.singleton(resultSet.getInt("courses"))
        );
    }
}
