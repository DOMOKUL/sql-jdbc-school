package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.service.StudentService;
import com.company.sql.jdbc.school.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudentsWithThisCourseName(String courseName) {
        try {
            var students = studentDao.findStudentsByCourseName(courseName);
            students.forEach(System.out::println);
            return students;
        } catch (DaoException cause) {
            throw new ServiceException("Course with name: " + courseName + " is not found", cause);
        }
    }

    @Override
    public void createStudent(Student student) {
        try {
            studentDao.create(student);
            System.out.println("Студент с id: " + student.studentId() + " был создан");
        } catch (DaoException | SQLException cause) {
            throw new ServiceException("Студент с id " + student.studentId() + " уже существует", cause);
        }
    }

    @Override
    public void deleteStudent(Integer studentId) {
        try {
            studentDao.delete(studentId);
            System.out.println("Студент с id: " + studentId + " был удален");
        } catch (DaoException cause) {
            throw new ServiceException("Student with id: " + studentId + " is not found", cause);
        }
    }

    @Override
    public void addStudentToCourse(Integer studentId, Integer courseId) {
        try {
            studentDao.addStudentToCourse(studentId, courseId);
            System.out.println("Студент с id: " + studentId + " был добавлен на курс с id " + courseId);
        } catch (DaoException cause) {
            throw new ServiceException("Студент с id " + studentId + " не найден", cause);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            return studentDao.findAll();
        } catch (DaoException cause) {
            throw new ServiceException("Не найдено ни одного студента", cause);
        }
    }

    @Override
    public void deleteStudentFromCourse(Integer studentId, Integer courseId) {
        try {
            studentDao.deleteStudentFromCourse(studentId, courseId);
            System.out.println("Студент с id: " + studentId + " был удален с курса с id " + courseId);
        } catch (DaoException cause) {
            throw new ServiceException("На курсе с id " + studentId + " не принадлежит курсу с id " + courseId, cause);
        }
    }
}
