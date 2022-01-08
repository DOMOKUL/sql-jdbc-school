package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.dao.implementation.CourseDaoImpl;
import com.company.sql.jdbc.school.dao.implementation.GroupDaoImpl;
import com.company.sql.jdbc.school.dao.implementation.StudentDaoImpl;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.util.database.implementation.StudentsFiller;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class DaoRunner {

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        var groupDao = GroupDaoImpl.getInstance();
        var group = new Group(12,"SR-12");
        groupDao.create(group);


//        StudentDao.getInstance().showAllStudents();
//        System.out.println();
//
//        var courseDao = CourseDaoImpl.getInstance();
//        var course = new Course(16,"SomeCourse#12","Biggest1");
//        courseDao.create(course);
//
//        courseDao.fillTableCourse("src/main/resources/generatedData/course-name-and-descrpiption.txt");
//
//        var studentsFiller = new StudentsFiller();
//        studentsFiller.fillTableStudents("src/main/resources/generatedData/student-first-name.txt","src/main/resources/generatedData/student-last-name.txt");

    }
}

