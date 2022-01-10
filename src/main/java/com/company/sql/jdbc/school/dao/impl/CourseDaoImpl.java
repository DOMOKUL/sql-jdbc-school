package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl implements CourseDao {

    @Override
    public void create(Course course) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/11 SQL query that create a course.sql"), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, course.courseId());
            preparedStatement.setString(2, course.courseName());
            preparedStatement.setString(3, course.courseDescription());
            preparedStatement.executeUpdate();

        } catch (Exception cause) {
            throw new DaoException("Course with id: " + course.courseId() + " is already exists");
        }
    }

    @Override
    public Optional<Course> findById(Integer id) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find course by id.sql"))) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            var resultSet = preparedStatement.executeQuery();
            Course course = null;
            if (resultSet.next()) {
                course = buildCourse(resultSet);
            }
            return Optional.ofNullable(course);
        } catch (Exception cause) {
            throw new DaoException("Course with id: " + id + " is not found");
        }
    }

    @Override
    public List<Course> findAll() {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all courses.sql"));) {
            var resultSet = preparedStatement.executeQuery();
            List<Course> course = new ArrayList<>();
            while (resultSet.next()) {
                course.add(buildCourse(resultSet));
            }
            return course;
        } catch (Exception cause) {
            throw new DaoException("No courses found");
        }
    }

    @Override
    public void update(Course course) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that update course by id.sql"));) {
            preparedStatement.setInt(1, course.courseId());
            preparedStatement.setString(2, course.courseName());
            preparedStatement.setString(3, course.courseDescription());
            preparedStatement.executeUpdate();
        } catch (Exception cause) {
            throw new DaoException("Course with id: " + course.courseId() + " is not found");
        }
    }

    @Override
    public void delete(Integer id) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete course by id.sql"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception cause) {
            throw new DaoException("Course with id: " + id + "is not found");
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