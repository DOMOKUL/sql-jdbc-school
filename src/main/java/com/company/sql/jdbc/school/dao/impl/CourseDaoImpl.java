package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private final DataSource dataSource;

    public CourseDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Course create(Course course) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a course.sql"))) {
            preparedStatement.setInt(1, course.courseId());
            preparedStatement.setString(2, course.courseName());
            preparedStatement.setString(3, course.courseDescription());
            preparedStatement.executeUpdate();
            return course;
        } catch (SQLException cause) {
            throw new DaoException("Course with id: " + course.courseId() + " is already exists");
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public Course findById(Integer id) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find course by id.sql"))) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Course course = null;
            if (resultSet.next()) {
                course = buildCourse(resultSet);
            }
            if (course == null) {
                throw new DaoException("Course with id: " + id + " is not found");
            }
            return course;
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public List<Course> findAll() {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all courses.sql"))) {
            var resultSet = preparedStatement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(buildCourse(resultSet));
            }
            return courses;
        } catch (SQLException cause) {
            throw new DaoException("No courses found ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public boolean update(Course course) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that update course by id.sql"))) {
            preparedStatement.setInt(1, course.courseId());
            preparedStatement.setString(2, course.courseName());
            preparedStatement.setString(3, course.courseDescription());
            preparedStatement.setInt(4, course.courseId());
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return true;
            }
            throw new DaoException("Course with id: " + course.courseId() + " is not found");

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public boolean delete(Integer courseId) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete course by id.sql"))) {
            preparedStatement.setInt(1, courseId);
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return true;
            }
            throw new DaoException("Course with id: " + courseId + " is not exist");

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    private Course buildCourse(ResultSet resultSet) throws SQLException {
        return new Course(
                resultSet.getInt("course_id"),
                resultSet.getString("name"),
                resultSet.getString("description")
        );
    }
}
