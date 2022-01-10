package com.company.sql.jdbc.school.service;

import com.company.sql.jdbc.school.domain.Course;

import java.util.Optional;

public interface CourseService {

    Optional<Course> findCourseById(Integer id);
}
