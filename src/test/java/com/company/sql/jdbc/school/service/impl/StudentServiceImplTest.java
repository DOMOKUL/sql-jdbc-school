package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    private final Student student = new Student(1, 1, "Ivan", "Ivanov");
    private final List<Student> students = List.of(student, new Student(2, 2, "Petr", "Petrov"));
    private final String courseName = "math";
    @Mock
    StudentDao studentDao;
    private StudentService studentService;

    @Test
    void getAllStudentsWithThisCourseName() {
        when(studentDao.findStudentsByCourseName(courseName)).thenReturn(students);

    }

    @Test
    void createStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void addStudentToCourse() {
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void deleteStudentFromCourse() {
    }
}