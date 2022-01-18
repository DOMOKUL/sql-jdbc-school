package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.domain.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CourseDaoImplTest {

    private CourseDao courseDao;

    @BeforeEach
    void setUp() {
        courseDao = new CourseDaoImpl();
    }

    @Test
    void create_shouldReturnCorrectIdCourse_whenInputCorrectData() throws SQLException {
        Course testCourse = new Course(11, "math", "love");
        courseDao.create(testCourse);
        assertEquals(11, testCourse.courseId());
    }


    @Test
    void findById_shouldReturnCorrectCourse_whenInputCorrectId() {
        Course testCourse = new Course(11, "math", "love");
        assertEquals(testCourse, courseDao.findById(11).get());
    }

    @Test
    void findAll() {
        List<Course> courses = courseDao.findAll();
        for (Course course : courses) {
            var courseId = course.courseId();
            var courseName = course.courseName();
            var courseDescription = course.courseDescription();
            assertTrue(Integer.toString(courseId).matches("^\\d+$"));
            assertTrue(courseName.matches("^[-a-z-A-Z]+$"));
            assertTrue(courseDescription.matches("^[-a-z-A-Z]+$"));
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}