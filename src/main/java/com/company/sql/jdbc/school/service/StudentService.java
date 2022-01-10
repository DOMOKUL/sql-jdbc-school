package com.company.sql.jdbc.school.service;

import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudentsWithThisCourseName(Course course);

    void saveStudent(Student student);

    void deleteStudent(Integer id);

    void addStudentToCourse(Student student);

    List<Student> showAllStudent();

    void deleteStudentFromCourse(Integer studentId, Integer courseId);

}
