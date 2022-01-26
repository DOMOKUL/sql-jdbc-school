package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CourseDaoImplTest {

    private final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");
    private CourseDao courseDao;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        container.start();
        var dataSource = new DataSource(container.getJdbcUrl(), container.getDatabaseName(), container.getPassword());
        courseDao = new CourseDaoImpl(dataSource);
        SqlScriptRunner sqlScriptRunner = new SqlScriptRunner(dataSource.getConnection());
        sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("init.sql")));
        sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("src/test/resources/sql/SQL query that fill tables.sql")));
    }

    @Test
    void create_shouldReturnCorrectIdCourse_whenInputCorrectData() {
        Course testCourse = new Course(11, "math", "love");
        courseDao.create(testCourse);
        assertEquals(11, testCourse.courseId());
    }

    @Test
    void create_shouldReturnDaoException_whenInputExistId() {
        Course testCourse = new Course(1, "math", "<3");
        assertThrows(DaoException.class, () ->
                courseDao.create(testCourse));
    }

    @Test
    void findById_shouldReturnCorrectCourse_whenInputCorrectCourseId() {
        Course testCourse = new Course(1, "math", "<3");
        assertEquals(testCourse, courseDao.findById(1));
    }

    @Test
    void findById_shouldThrowDaoException_whenInputNonExistentCourseId() {
        assertThrows(DaoException.class, () ->
                courseDao.findById(10000));
    }

    @Test
    void findAll_shouldReturnNotEmptyList() {
        List<Course> courses = courseDao.findAll();
        assertFalse(courses.isEmpty());
    }

    @Test
    void update_shouldUpdateCourseData_whenInputExistCourseId(){
        var testCourse = new Course(1, "math", "<3");
        var actual = courseDao.update(testCourse);
        assertTrue(actual);
    }

    @Test
    void update_shouldThrowDaoException_whenInputNotExistCourseId(){
        var testCourse = new Course(100, "math", "<3");
        assertThrows(DaoException.class,()->
                courseDao.update(testCourse));
    }

    @Test
    void delete_shouldDeleteCourseData_whenInputExistCourseId() {
        var actual = courseDao.delete(1);
        assertTrue(actual);
    }

    @Test
    void delete_shouldThrowDaoException_whenInputNotExistCourseId(){
        assertThrows(DaoException.class,()->
                courseDao.delete(100));
    }
}