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

import java.util.Map;
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
            var queryScanner = new Scanner(System.in);
            System.out.println("""
                    Введите:
                    1.Показать все группы с указанным числом студентов
                    2.Показать всех студентов, относящихся к данной группе
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
                var studentCount = queryScanner.nextLine();
                getAllGroupsWithLessOrEqualsStudentCountFormat(studentCount);
            } else if (input == 2) {
                System.out.println("Введите название курса: ");
                var courseName = queryScanner.nextLine();
                var allStudentsWithThisCourseName = student.getAllStudentsWithThisCourseName(courseName);
                allStudentsWithThisCourseName.forEach(System.out::println);
            } else if (input == 3) {
                System.out.println("Введите id студента: ");
                var studentId = queryScanner.nextInt();
                System.out.println("Введите id группы: ");
                var groupId = queryScanner.nextInt();
                System.out.println("Введите имя студента: ");
                var firstName = scanner.next();
                System.out.println("Введите фамилию студента: ");
                var lastName = queryScanner.next();
                createStudentFormat(studentId, groupId, firstName, lastName);
            } else if (input == 4) {
                System.out.println("Введите id студента: ");
                var studentId = queryScanner.nextInt();
                student.deleteStudent(studentId);
                System.out.println("Студент с id: " + studentId + " успешно удален");
            } else if (input == 5) {
                System.out.println("Введите id студента: ");
                var studentId = queryScanner.nextInt();
                System.out.println("Введите id курса: ");
                var courseId = queryScanner.nextInt();
                student.addStudentToCourse(studentId, courseId);
                System.out.println("Студент с id: " + studentId + " успешно создан");
            } else if (input == 6) {
                System.out.println("Введите id студента: ");
                var studentId = queryScanner.nextInt();
                System.out.println("Введите id курса: ");
                var courseId = queryScanner.nextInt();
                student.deleteStudentFromCourse(studentId, courseId);
                System.out.println("Студент с id: " + studentId + " успешно удален");
            } else if (input == 7) {
                student.getAllStudents().forEach(System.out::println);
            } else if (input == 8) {
                System.out.println("Введите id группы: ");
                var groupId = queryScanner.nextInt();
                System.out.println("Введите имя группы: ");
                var groupName = queryScanner.next();
                createGroupFormat(groupId, groupName);
            } else if (input == 9) {
                System.out.println("Введите id курса: ");
                var courseId = queryScanner.nextInt();
                System.out.println("Введите название курса: ");
                var courseName = queryScanner.next();
                System.out.println("Введите описание курса: ");
                var courseDescription = queryScanner.next();
                createCourseFormat(courseId, courseName, courseDescription);
            }
        }
    }

    private void getAllGroupsWithLessOrEqualsStudentCountFormat(String count) {
        var groups = group.getAllGroupsWithLessOrEqualsStudentCount(Integer.valueOf(count));
        System.out.println("Группы с числом студентов, меньше: " + count);
        for (Map.Entry entry : groups.entrySet()) {
            System.out.println("Название группы: " + entry.getKey() + " Количество студентов: " + entry.getValue());
        }
    }

    private void createStudentFormat(Integer studentId, Integer groupId, String firstName, String lastName) {
        student.createStudent(new Student(studentId, groupId, firstName, lastName));
        System.out.println("Студент с id: " + studentId + " был создан");
    }


    private void createGroupFormat(Integer groupId, String groupName) {
        group.createGroup(new Group(groupId, groupName));
        System.out.println("Группа с id: " + groupId + " успешно создана");
    }

    private void createCourseFormat(Integer courseId, String courseName, String courseDescription) {
        course.createCourse(new Course(courseId, courseName, courseDescription));
        System.out.println("Группа с id: " + courseId + " успешно создана");
    }
}




