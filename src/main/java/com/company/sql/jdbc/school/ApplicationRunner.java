package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.dao.impl.CourseDaoImpl;
import com.company.sql.jdbc.school.service.CourseService;
import com.company.sql.jdbc.school.service.impl.CourseServiceImpl;

public class ApplicationRunner {

    public static void main(String[] args) {
        CourseService service = new CourseServiceImpl(new CourseDaoImpl());
        service.getCourseById(1);
    }
}
