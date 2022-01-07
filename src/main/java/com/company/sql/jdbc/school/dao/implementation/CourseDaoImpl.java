package com.company.sql.jdbc.school.dao.implementation;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private static final CourseDaoImpl INSTANCE = new CourseDaoImpl();
    private static final String SAVE_SQL = """
            INSERT INTO courses(course_id, name,description)
            VALUES (?, ?, ?);
            """;

    private CourseDaoImpl() {
    }

    public static CourseDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void setCourseStudent(Integer student_id, Integer course_id) {

    }

    @Override
    public Integer deleteCourseStudent(Integer student_id, Integer course_id) {
        return null;
    }

    @Override
    public List<Course> findStudentCourseByStudentId(Integer id) {
        return null;
    }

    @Override
    public void create(Course course) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, course.courseId());
            preparedStatement.setString(2, course.courseName());
            preparedStatement.setString(3, course.courseDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException cause) {
            throw new DaoException("Creating course fail", cause);
        }

    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public void update(Course t) {

    }

    @Override
    public void delete(Integer id) {

    }
}
