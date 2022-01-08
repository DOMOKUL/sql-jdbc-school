package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;

import java.util.List;
import java.util.Set;

public interface StudentDao extends CrudDao<Student> {

    void deleteStudentFromCourse(Integer studentId, Integer courseId);

    List<Student> findStudentsByCourseName(Course course);

    void addCourseSet(Student student);
}
