package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.dao.impl.StudentDaoImpl;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.service.StudentService;
import com.company.sql.jdbc.school.service.exception.ServiceException;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDaoImpl studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudentsWithThisCourseName(String courseName) {
        try {
            return studentDao.findStudentsByCourseName(courseName);
        } catch (DaoException cause) {
            throw new ServiceException("Course with name: " + courseName + " is not found", cause);
        }
    }

    @Override
    public void printAllStudentsWithThisCourseName(String courseName) {
        try {
            var students = studentDao.findStudentsByCourseName(courseName);
            students.forEach(System.out::println);
        } catch (DaoException cause) {
            throw new ServiceException("Course with name: " + courseName + " is not found", cause);
        }
    }

    @Override
    public void createStudent(Student student) {
        try {
            studentDao.create(student);
            System.out.println("Student with id: " + student.studentId() + " successfully created");
        } catch (DaoException cause){
            throw new ServiceException("Student with id: " + student.studentId() + " exists", cause);
        }
    }

    @Override
    public void deleteStudent(Integer studentId) {
        try {
            studentDao.delete(studentId);
            System.out.println("Student with id: " + studentId + " was deleted");
        } catch (DaoException cause) {
            throw new ServiceException("Student with id: " + studentId + " is not found", cause);
        }
    }

    @Override
    public void addStudentToCourse(Integer studentId, Integer courseId) {
        try {
            studentDao.addStudentToCourse(studentId, courseId);
            System.out.println("Student with id: " + studentId + " has been added to the course with the id " + courseId);
        } catch (DaoException cause) {
            throw new ServiceException("Student with id " + studentId + " not found", cause);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            return studentDao.findAll();
        } catch (DaoException cause) {
            throw new ServiceException("No students were found", cause);
        }
    }

    @Override
    public void deleteStudentFromCourse(Integer studentId, Integer courseId) {
        try {
            studentDao.deleteStudentFromCourse(studentId, courseId);
            System.out.println("Student with id: " + studentId + " has been removed from the course with the id " + courseId);
        } catch (DaoException cause) {
            throw new ServiceException("Student with id: " + studentId + " does not belong to a course with the id " + courseId, cause);
        }
    }
}
