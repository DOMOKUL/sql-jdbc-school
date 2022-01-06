package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.domain.Student;

import java.io.FileNotFoundException;
import java.util.List;

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
//        var courseDao = CourseDao.getInstance();
//        var course = new Course();
//        course.setCourseId(14);
//        course.setCourseName("SomeCourse#14");
//        course.setCourseDescription("About dog");
//
//        courseDao.fillTableCourse("src/main/resources/generatedData/courseNameAndDescrp");
//
        var studentDao = StudentDao.getInstance();
//        var allStudentsWithGivenCourse = studentDao.findAllStudentsWithGivenCourse(createdCourse);
//        System.out.println(allStudentsWithGivenCourse);
        studentDao.fillTableStudents("src/main/resources/generatedData/studentFirstName.txt","src/main/resources/generatedData/studentLastName.txt");

    }
}

