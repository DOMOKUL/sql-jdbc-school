package com.company.sql.jdbc.school.service;

import com.company.sql.jdbc.school.domain.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudentsWithThisCourseName(String courseName);

    void printAllStudentsWithThisCourseName(String courseName);

    Student createStudent(Student student);

    boolean deleteStudent(Integer id);

    boolean addStudentToCourse(Integer studentId, Integer courseId);

    List<Student> getAllStudents();

    boolean deleteStudentFromCourse(Integer studentId, Integer courseId);

}
