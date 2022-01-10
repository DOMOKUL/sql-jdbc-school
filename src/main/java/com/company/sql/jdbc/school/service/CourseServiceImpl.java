package com.company.sql.jdbc.school.service;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.dao.implementation.CourseDaoImpl;
import com.company.sql.jdbc.school.domain.Course;

import java.util.Optional;

public class CourseServiceImpl implements CourseService{

    @Override
    public Course findCourseById(Integer id) {
        CourseDao courseDao = new CourseDaoImpl();
        return courseDao.findById(id).get();
    }
}
