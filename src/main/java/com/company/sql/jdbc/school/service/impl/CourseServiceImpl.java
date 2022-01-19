package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.dao.impl.CourseDaoImpl;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.service.CourseService;
import com.company.sql.jdbc.school.service.exception.ServiceException;

public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDaoImpl courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void createCourse(Course course) {
        try {
            courseDao.create(course);
            System.out.println("Group with id : " + course.courseId() + " successfully created");
        } catch (DaoException cause) {
            throw new ServiceException(cause);
        }
    }
}
