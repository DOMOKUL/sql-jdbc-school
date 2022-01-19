package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final DataSource dataSource;

    public StudentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean deleteStudentFromCourse(Integer studentId, Integer courseId) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete student from course.sql"))) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return true;
            }
            throw new DaoException("Student with id " + studentId + " or course with id " + courseId + " is not exist");

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public List<Student> findStudentsByCourseName(String courseName) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all students by course name.sql"))) {
            preparedStatement.setString(1, courseName);
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            if (students.isEmpty()) {
                throw new DaoException("Course with name: " + courseName + " is not found");
            }
            return students;
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public boolean addStudentToCourse(Integer studentId, Integer courseId) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create courses_students.sql"))) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return true;
            }
            throw new DaoException("Student with id " + studentId + " or course with id " + courseId + " is not exist");

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database " + cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public void create(Student student) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a student.sql"))) {
            preparedStatement.setInt(1, student.studentId());
            preparedStatement.setInt(2, student.groupId());
            preparedStatement.setString(3, student.firstName());
            preparedStatement.setString(4, student.lastName());
            preparedStatement.executeUpdate();

        } catch (SQLException cause) {
            throw new DaoException("Student with id: " + student.studentId() + " is already exists");
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public Student findById(Integer id) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find student by id.sql"))) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Student student = null;
            if (resultSet.next()) {
                student = buildStudent(resultSet);
            }
            if (student == null) {
                throw new DaoException("Student with id: " + id + " is not found");
            }
            return student;

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public List<Student> findAll() {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all students.sql"))) {
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public boolean update(Student student) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that update student by id.sql"))) {
            preparedStatement.setInt(1, student.studentId());
            preparedStatement.setInt(2, student.groupId());
            preparedStatement.setString(3, student.firstName());
            preparedStatement.setString(4, student.lastName());
            preparedStatement.setInt(5, student.studentId());
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return true;
            }
            throw new DaoException("Student with id: " + student.studentId() + " is not exist");

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public boolean delete(Integer studentId) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete student by id.sql"))) {
            preparedStatement.setInt(1, studentId);
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return true;
            }
            throw new DaoException("Student with id: " + studentId + " is not exist");

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
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
