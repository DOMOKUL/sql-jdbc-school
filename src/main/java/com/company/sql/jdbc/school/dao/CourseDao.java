package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Course;

import java.util.Optional;

public interface CourseDao extends CrudDao<Course> {

    Optional<Course> findById(Integer id);
}
