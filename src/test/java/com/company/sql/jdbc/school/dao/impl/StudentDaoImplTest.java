package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoImplTest {

    private final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");

    StudentDao studentDao;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        container.start();
        var dataSource = new DataSource(container.getJdbcUrl(), container.getDatabaseName(), container.getPassword());
        studentDao = new StudentDaoImpl(dataSource);
        SqlScriptRunner sqlScriptRunner = new SqlScriptRunner(dataSource.getConnection());
        sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("init.sql")));
        sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("src/test/resources/sql/SQL query that fill tables.sql")));
    }

    @Test
    void deleteStudentFromCourse_shouldReturnTrue_whenInputCorrectStudentIdAndCourseId() {
        Student student = new Student(1, 1, "Ivan", "Ivanov");
        Course course = new Course(1, "math", "<3");
        var actual = studentDao.deleteStudentFromCourse(student.studentId(), course.courseId());
        assertTrue(actual);
    }

    @Test
    void deleteStudentFromCourse_shouldThrowDaoException_whenInputNotExistStudentIdAndCourseId() {
        Student student = new Student(100, 1, "Ivan", "Ivanov");
        Course course = new Course(100, "math", "<3");
        assertThrows(DaoException.class, () ->
                studentDao.deleteStudentFromCourse(student.studentId(), course.courseId()));
    }

    @Test
    void findStudentsByCourseName_shouldReturnStudentsList_whenInputCorrectCourseName() {
        Course course = new Course(1, "math", "<3");
        List<Student> testStudentsList = new ArrayList<>();
        testStudentsList.add(new Student(1, 1, "Ivan", "Ivanov"));
        testStudentsList.add(new Student(2, 2, "Petr", "Petrov"));
        assertEquals(testStudentsList, studentDao.findStudentsByCourseName(course.courseName()));
    }

    @Test
    void findStudentsByCourseName_shouldThrowDaoException_whenInputIncorrectCourseName() {
        Course course = new Course(100, "math1488", "<3");
        assertThrows(DaoException.class,()->
                studentDao.findStudentsByCourseName(course.courseName()));
    }

    @Test
    void addStudentToCourse_shouldReturnTrue_whenInputCorrectId() {
        Student student = new Student(3, 3, "Alex", "Alexandrov");
        Course course = new Course(1, "math", "<3");
        var actual = studentDao.addStudentToCourse(student.studentId(), course.courseId());
        assertTrue(actual);
    }

    @Test
    void addStudentToCourse_shouldThrowDaoException_whenInputIncorrectStudentIdAndCourseId() {
        Student student = new Student(100, 3, "Alex", "Alexandrov");
        Course course = new Course(100, "math", "<3");
        assertThrows(DaoException.class,()->
                studentDao.addStudentToCourse(student.studentId(),course.courseId()));
    }

    @Test
    void create_shouldReturnCorrectStudentId_whenInputStudentData() {
        Student testStudent = new Student(7, 1,"Ivan","Alexandrov");
        studentDao.create(testStudent);
        assertEquals(7, testStudent.studentId());
    }

    @Test
    void create_shouldReturnDaoException_whenInputExistStudentId() {
        Student testStudent = new Student(1, 1, "Ivan", "Ivanov");
        assertThrows(DaoException.class, () ->
                studentDao.create(testStudent));
    }

    @Test
    void findById_shouldReturnCorrectStudent_whenInputCorrectStudentId() {
        Student testStudent = new Student(1, 1, "Ivan", "Ivanov");
        assertEquals(testStudent, studentDao.findById(testStudent.studentId()));
    }

    @Test
    void findById_shouldThrowDaoException_whenInputNonExistentStudentId() {
        var testStudent = new Student(100, 1, "Ivan", "Ivanov");
        assertThrows(DaoException.class, () ->
                studentDao.findById(testStudent.studentId()));
    }

    @Test
    void findAll_shouldReturnNotEmptyList() {
        List<Student> testStudents = studentDao.findAll();
        assertFalse(testStudents.isEmpty());
    }

    @Test
    void update_shouldUpdateStudentData_whenInputExistStudentId() {
        Student testStudent = new Student(1, 1, "Ivan", "Ivanov");
        var actual = studentDao.update(testStudent);
        assertTrue(actual);
    }

    @Test
    void update_shouldThrowDaoException_whenInputNotExistStudentId(){
        var testStudent = new Student(100, 1, "Ivan", "Ivanov");
        assertThrows(DaoException.class,()->
                studentDao.update(testStudent));
    }

    @Test
    void delete_shouldDeleteStudentData_whenInputExistStudentId() {
        Student testStudent = new Student(1, 1, "Ivan", "Ivanov");
        var actual = studentDao.delete(testStudent.studentId());
        assertTrue(actual);
    }

    @Test
    void delete_shouldThrowDaoException_whenInputNotExistStudentId(){
        Student testStudent = new Student(100, 1, "Ivan", "Ivanov");
        assertThrows(DaoException.class,()->
                studentDao.delete(testStudent.studentId()));
    }
}