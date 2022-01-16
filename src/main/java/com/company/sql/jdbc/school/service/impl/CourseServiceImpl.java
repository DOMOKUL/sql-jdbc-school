package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.service.CourseService;
import com.company.sql.jdbc.school.service.exception.ServiceException;

import java.sql.SQLException;

public record CourseServiceImpl(CourseDao courseDao) implements CourseService {

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
