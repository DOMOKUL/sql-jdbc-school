package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.service.CourseService;
import com.company.sql.jdbc.school.service.exception.ServiceException;

import java.sql.SQLException;

public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void createCourse(Course course) {
        try {
            courseDao.create(course);
            System.out.println("Группа с id: " + course.courseId() + " успешно создана");
        } catch (SQLException cause) {
            throw new ServiceException(cause);
        }
    }
}
