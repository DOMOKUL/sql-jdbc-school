package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.dao.implementation.CourseDaoImpl;
import com.company.sql.jdbc.school.dao.implementation.StudentDaoImpl;
import com.company.sql.jdbc.school.domain.Course;

import java.io.FileNotFoundException;

public class DaoRunner {

    public static void main(String[] args) throws FileNotFoundException {
//        var groupDao = GroupDao.getInstance();
//        var group = new Group();
//        group.setGroupId(7);
//        group.setGroupName("SR-07");
//

//        StudentDao.getInstance().showAllStudents();
//        System.out.println();
//
        var courseDao = CourseDaoImpl.getInstance();
        var course = new Course(11,"SomeCourse#11","Biggest");
        courseDao.createCourse(course);
//
//        courseDao.fillTableCourse("src/main/resources/generatedData/course-name-and-descrpiption.txt");
//
        var studentDao = StudentDaoImpl.getInstance();
//        var allStudentsWithGivenCourse = studentDao.findAllStudentsWithGivenCourse(createdCourse);
//        System.out.println(allStudentsWithGivenCourse);
//        studentDao.fillTableStudents("src/main/resources/generatedData/student-first-name.txt","src/main/resources/generatedData/student-last-name.txt");

    }
}

