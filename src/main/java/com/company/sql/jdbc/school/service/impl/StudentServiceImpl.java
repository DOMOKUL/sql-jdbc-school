package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.StudentDao;
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
        return studentDao.findStudentsByCourseName(courseName);
    }

    @Override
    public void createStudent(Student student) {
        try {
            studentDao.create(student);
        } catch (SQLException cause) {
            throw new ServiceException(cause);
        }
    }

    @Override
    public void deleteStudent(Integer id) {
        studentDao.delete(id);
    }

    @Override
    public void addStudentToCourse(Integer studentId, Integer courseId) {
        studentDao.addStudentCourse(studentId,courseId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public void deleteStudentFromCourse(Integer studentId, Integer courseId) {
        studentDao.deleteStudentFromCourse(studentId, courseId);
    }
}
