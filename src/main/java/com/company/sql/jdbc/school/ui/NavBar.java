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
            System.out.println("""
                    Введите:
                    a.Показать все группы с указанным числом студентов
                    b.Показать всех студентов, относящихся к данной группе
                    с.Добавить нового студента
                    d.Удалить студента по id
                    e.Добавить студента к курсу
                    f.Удалить студента из курса
                    g.Показать всех студентов
                    h.Создать группу
                    i.Создать курс
                    exit.Прекратить выполнение
                    """);
            var input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            } else if ("a".equals(input)) {
                System.out.println("Введите количество студентов: ");
                var studentCount = scanner.nextLine();
                getAllGroupsWithLessOrEqualsStudentCountFormat(studentCount);
            } else if ("b".equals(input)) {
                System.out.println("Введите название курса: ");
                var courseName = scanner.nextLine();
                System.out.println(student.getAllStudentsWithThisCourseName(courseName));
            } else if ("c".equals(input)) {
                System.out.println("Введите id студента: ");
                var studentId = scanner.nextInt();
                System.out.println("Введите id группы: ");
                var groupId = scanner.nextInt();
                System.out.println("Введите имя и фамилию студента: ");
                createStudentFormat(studentId, groupId, scanner.nextLine());
            } else if ("d".equals(input)) {
                System.out.println("Введите id студента: ");
                var studentId = scanner.nextInt();
                student.deleteStudent(studentId);
                System.out.println("Студент с id: " + studentId + " успешно удален");
            } else if ("e".equals(input)) {
                System.out.println("Введите id студента: ");
                var studentId = scanner.nextInt();
                System.out.println("Введите id курса: ");
                var courseId = scanner.nextInt();
                student.addStudentToCourse(studentId, courseId);
                System.out.println("Студент с id: " + studentId + " успешно создан");
            } else if ("f".equals(input)) {
                System.out.println("Введите id студента: ");
                var studentId = scanner.nextInt();
                System.out.println("Введите id курса: ");
                var courseId = scanner.nextInt();
                student.deleteStudentFromCourse(studentId, courseId);
                System.out.println("Студент с id: " + studentId + " успешно удален");
            } else if ("g".equals(input)) {
                student.getAllStudents().forEach(System.out::println);
            } else if ("h".equals(input)) {
                System.out.println("Введите id группы: ");
                var groupId = scanner.nextInt();
                System.out.println("Введите имя группы: ");
                var groupName = scanner.nextLine();
                createGroupFormat(groupId, groupName);
            } else if ("i".equals(input)) {
                System.out.println("Введите id курса: ");
                var courseId = scanner.nextInt();
                System.out.println("Введите название курса: ");
                var courseName = scanner.next();
                System.out.println("Введите описание курса: ");
                var courseDescription = scanner.next();
                createCourseFormat(courseId, courseName, courseDescription);
            } else {
                System.out.println("Неверный ввод! Повторите попытку!");
                break;
            }
        }
    }

    private void getAllGroupsWithLessOrEqualsStudentCountFormat(String count) {
        var groups = group.getAllGroupsWithLessOrEqualsStudentCount(Integer.valueOf(count));
        for (Map.Entry entry : groups.entrySet()) {
            System.out.println("Название группы: " + entry.getKey() + " Количество студентов: " + entry.getValue());
        }
        System.out.println("Группы с числом студентов, меньше: " + count);
    }

    private void createStudentFormat(Integer studentId, Integer groupId, String studentName) {
        var splitName = studentName.split(", ");
        var firstName = splitName[0];
        var lastName = splitName[1];
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




