package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Course;

public interface CourseDao extends CrudDao<Course> {

    Integer deleteCourseStudent(Integer student_id, Integer course_id);
}
