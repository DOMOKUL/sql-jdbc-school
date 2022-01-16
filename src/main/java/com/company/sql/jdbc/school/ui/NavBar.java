package com.company.sql.jdbc.school.ui;

import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.service.CourseService;
import com.company.sql.jdbc.school.service.GroupService;
import com.company.sql.jdbc.school.service.StudentService;
import com.company.sql.jdbc.school.service.exception.ServiceException;
import com.company.sql.jdbc.school.ui.exception.UiException;

import java.util.Scanner;

public class NavBar {

    private final CourseService courseService;
    private final GroupService groupService;
    private final StudentService studentService;

    public NavBar(CourseService courseService, GroupService groupService, StudentService studentService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    public void controlBar() {
        try {
            var scanner = new Scanner(System.in);
            while (true) {
                var queryScanner = new Scanner(System.in);
                System.out.println("""
                        Введите:
                        1.Показать все группы с указанным числом студентов
                        2.Показать всех студентов, относящихся к данному курсу
                        3.Добавить нового студента
                        4.Удалить студента по id
                        5.Добавить студента к курсу
                        6.Удалить студента из курса
                        7.Показать всех студентов
                        8.Создать группу
                        9.Создать курс
                        0.Прекратить выполнение
                        """);
                var input = scanner.nextInt();
                if (input == 0) {
                    break;
                } else if (input == 1) {
                    System.out.println("Введите количество студентов: ");
                    var studentCount = queryScanner.nextInt();
                    groupService.printAllGroupsWithLessOrEqualsStudentCount(studentCount);
                } else if (input == 2) {
                    System.out.println("Введите название курса: ");
                    var courseName = queryScanner.nextLine();
                    studentService.printAllStudentsWithThisCourseName(courseName);
                } else if (input == 3) {
                    System.out.println("Введите id студента: ");
                    var studentId = queryScanner.nextInt();
                    System.out.println("Введите id группы: ");
                    var groupId = queryScanner.nextInt();
                    System.out.println("Введите имя студента: ");
                    var firstName = scanner.next();
                    System.out.println("Введите фамилию студента: ");
                    var lastName = queryScanner.next();
                    studentService.createStudent(new Student(studentId, groupId, firstName, lastName));
                } else if (input == 4) {
                    System.out.println("Введите id студента: ");
                    var studentId = queryScanner.nextInt();
                    studentService.deleteStudent(studentId);
                } else if (input == 5) {
                    System.out.println("Введите id студента: ");
                    var studentId = queryScanner.nextInt();
                    System.out.println("Введите id курса: ");
                    var courseId = queryScanner.nextInt();
                    studentService.addStudentToCourse(studentId, courseId);
                } else if (input == 6) {
                    System.out.println("Введите id студента: ");
                    var studentId = queryScanner.nextInt();
                    System.out.println("Введите id курса: ");
                    var courseId = queryScanner.nextInt();
                    studentService.deleteStudentFromCourse(studentId, courseId);
                } else if (input == 7) {
                    studentService.getAllStudents().forEach(System.out::println);
                } else if (input == 8) {
                    System.out.println("Введите id группы: ");
                    var groupId = queryScanner.nextInt();
                    System.out.println("Введите имя группы: ");
                    var groupName = queryScanner.next();
                    groupService.createGroup(new Group(groupId, groupName));
                } else if (input == 9) {
                    System.out.println("Введите id курса: ");
                    var courseId = queryScanner.nextInt();
                    System.out.println("Введите название курса: ");
                    var courseName = queryScanner.next();
                    System.out.println("Введите описание курса: ");
                    var courseDescription = queryScanner.next();
                    courseService.createCourse(new Course(courseId, courseName, courseDescription));
                }
            }
        } catch (ServiceException cause) {
            throw new UiException("UI exception ", cause);
        }
    }
}




