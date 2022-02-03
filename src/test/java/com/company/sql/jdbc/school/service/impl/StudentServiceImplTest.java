package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.impl.StudentDaoImpl;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    private final Student student = new Student(1, 1, "Ivan", "Ivanov");
    private final List<Student> students = List.of(student, new Student(2, 2, "Petr", "Petrov"));

    private final StudentDaoImpl studentDao = Mockito.mock(StudentDaoImpl.class);

    private final StudentService studentService = new StudentServiceImpl(studentDao);

    @Test
    void getAllStudentsWithThisCourseName() {
        String courseName = "math";
        when(studentDao.findStudentsByCourseName(courseName)).thenReturn(students);
        List<Student> actual = studentService.getAllStudentsWithThisCourseName(courseName);

        assertEquals(students, actual);

        verify(studentDao, times(1)).findStudentsByCourseName(courseName);
    }

    @Test
    void createStudent() {
        when(studentDao.create(new Student(1, 1, "Ivan", "Ivanov"))).thenReturn(student);
        Student actual = studentService.createStudent(student);

        assertEquals(student, actual);

        verify(studentDao, times(1)).create(student);
    }

    @Test
    void deleteStudent() {
        when(studentDao.delete(student.studentId())).thenReturn(true);
        boolean actual = studentService.deleteStudent(student.studentId());

        assertTrue(actual);

        verify(studentDao, times(1)).delete(student.studentId());
    }

    @Test
    void addStudentToCourse() {
        Integer courseId = 1;
        when(studentDao.addStudentToCourse(student.studentId(), courseId)).thenReturn(true);

        boolean actual = studentService.addStudentToCourse(student.studentId(), courseId);

        assertTrue(actual);

        verify(studentDao, times(1)).addStudentToCourse(student.studentId(), courseId);
    }

    @Test
    void getAllStudents() {
        when(studentDao.findAll()).thenReturn(students);

        assertEquals(students, studentService.getAllStudents());

        verify(studentDao, times(1)).findAll();
    }

    @Test
    void deleteStudentFromCourse() {
        Integer courseId = 1;
        when(studentDao.deleteStudentFromCourse(student.studentId(), courseId)).thenReturn(true);

        boolean actual = studentService.deleteStudentFromCourse(student.studentId(), courseId);

        assertTrue(actual);
        verify(studentDao, times(1)).deleteStudentFromCourse(student.studentId(), courseId);
    }
}