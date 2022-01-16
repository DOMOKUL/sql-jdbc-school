package com.company.sql.jdbc.school.service;

import com.company.sql.jdbc.school.domain.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudentsWithThisCourseName(String courseName);

    void createStudent(Student student);

    void deleteStudent(Integer id);

    void addStudentToCourse(Integer studentId, Integer courseId);

    List<Student> getAllStudents();

    void deleteStudentFromCourse(Integer studentId, Integer courseId);

}
