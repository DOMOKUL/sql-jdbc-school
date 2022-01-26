package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Student;

import java.util.List;

public interface StudentDao extends CrudDao<Student> {

    boolean deleteStudentFromCourse(Integer studentId, Integer courseId);

    List<Student> findStudentsByCourseName(String courseName);

    boolean addStudentToCourse(Integer studentId, Integer courseId);

    Student findById(Integer id);

}
