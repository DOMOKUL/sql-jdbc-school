package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;

import java.sql.*;
import java.util.*;

public class StudentDaoImpl implements StudentDao {

    @Override
    public void deleteStudentFromCourse(Integer studentId, Integer courseId) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete student from course.sql"))) {
        preparedStatement.setInt(1,studentId);
        preparedStatement.setInt(2,courseId);
        preparedStatement.executeUpdate();
        }
        catch (Exception cause) {
            throw new DaoException("Delete student from course fail");
        }
    }

    @Override
    public List<Student> findStudentsByCourseName(String courseName) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all students by course name.sql"))) {
            preparedStatement.setString(1, courseName);
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (Exception cause) {
            throw new DaoException("Course with name: " + courseName + " is not found");
        }
    }

    @Override
    public void addStudentCourse(Integer studentId, Integer courseId) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create courses_students.sql"))) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, courseId);
                preparedStatement.executeQuery();

        } catch (Exception cause) {
            throw new DaoException("Student with id: " + studentId + " is not found");
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
            throw new DaoException("Student with id: " + student.studentId() + " is already exists");
        }
    }

    @Override
    public Optional<Student> findById(Integer id) {
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
            throw new DaoException("Student with id: " + id + " is not found");
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
            throw new DaoException("No students found");
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
            throw new DaoException("Student with id: " + student.studentId() + " is not found");
        }
    }

    @Override
    public void delete(Integer id) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete student by id.sql"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception cause) {
            throw new DaoException("Student with id: " + id + " is not found");
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
