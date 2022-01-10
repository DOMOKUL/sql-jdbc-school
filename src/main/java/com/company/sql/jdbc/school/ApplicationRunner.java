package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.dao.impl.CourseDaoImpl;
import com.company.sql.jdbc.school.service.CourseService;
import com.company.sql.jdbc.school.service.impl.CourseServiceImpl;
import com.company.sql.jdbc.school.ui.NavBar;

public class ApplicationRunner {

    public static void main(String[] args) {
        NavBar menu = new NavBar();
        menu.controlMenu();
    }
}
