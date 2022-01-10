package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;

import java.util.List;

public interface StudentDao extends CrudDao<Student> {

    void deleteStudentFromCourse(Integer studentId, Integer courseId);

    List<Student> findStudentsByCourseName(String courseName);

    void addStudentCourse(Integer studentId, Integer courseId);
}
