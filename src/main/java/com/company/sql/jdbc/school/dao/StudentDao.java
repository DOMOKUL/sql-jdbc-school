package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;

import java.util.List;

public interface StudentDao extends CrudDao<Student> {

    List<Student> findStudentsByCourseName(Course course);
}
