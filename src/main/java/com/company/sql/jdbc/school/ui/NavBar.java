package com.company.sql.jdbc.school.ui;

import com.company.sql.jdbc.school.dao.impl.CourseDaoImpl;
import com.company.sql.jdbc.school.dao.impl.GroupDaoImpl;
import com.company.sql.jdbc.school.dao.impl.StudentDaoImpl;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.service.impl.CourseServiceImpl;
import com.company.sql.jdbc.school.service.impl.GroupServiceImpl;
import com.company.sql.jdbc.school.service.impl.StudentServiceImpl;

import java.util.Scanner;

public class NavBar {

    private static final CourseDaoImpl courseDao = new CourseDaoImpl();
    private static final GroupDaoImpl groupDao = new GroupDaoImpl();
    private static final StudentDaoImpl studentDao = new StudentDaoImpl();
    private static final CourseServiceImpl course = new CourseServiceImpl(courseDao);
    private static final GroupServiceImpl group = new GroupServiceImpl(groupDao);
    private static final StudentServiceImpl student = new StudentServiceImpl(studentDao);

    public void controlMenu() {
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите: ");
            var input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            } else if ("a".equals(input)) {
                System.out.println("Введите количество: ");
                var answer = scanner.nextLine();
                group.getAllGroupsWithLessOrEqualsStudentCount(Integer.valueOf(answer));
            } else if ("b".equals(input)) {
                System.out.println("Введите название курса: ");
                var answer = scanner.nextLine();
                student.getAllStudentsWithThisCourseName(answer);
            } else if ("c".equals(input)) {
                System.out.println("Введите данные для создания студента: ");
                var studentId = scanner.nextInt();
                var groupId = scanner.nextInt();
                var firstName = scanner.nextLine();
                var lastName = scanner.nextLine();
                student.saveStudent(new Student(studentId, groupId, firstName, lastName));
            } else if ("d".equals(input)) {
                System.out.println("Введите id студента: ");
                var answer = scanner.nextInt();
                student.deleteStudent(answer);
            } else if ("e".equals(input)) {
                System.out.println("Введите данные для создания студента: ");
                var studentId = scanner.nextInt();
                var courseId = scanner.nextInt();
                student.addStudentToCourse(studentId, courseId);
            } else if ("f".equals(input)) {
                System.out.println("Введите id студента: ");
                var studentId = scanner.nextInt();
                System.out.println("Введите id курса: ");
                var courseId = scanner.nextInt();
                student.deleteStudentFromCourse(studentId, courseId);
            } else if ("g".equals(input)) {
                student.showAllStudent();
            } else if ("h".equals(input)) {
                System.out.println("Введите id группы: ");
                var groupId = scanner.nextInt();
                System.out.println("Введите имя группы: ");
                var groupName = scanner.nextLine();
                group.saveGroup(new Group(groupId, groupName));
            } else if ("i".equals(input)) {
                System.out.println("Введите id курса: ");
                var courseId = scanner.nextInt();
                System.out.println("Введите название курса: ");
                var courseName = scanner.nextLine();
                System.out.println("Введите описание курса: ");
                var courseDescr = scanner.nextLine();

                course.createCourse(new Course(courseId, courseName, courseDescr));
            } else {
                System.out.println("Неверный ввод! Повторите попытку!");
                break;
            }
        }
    }
}
