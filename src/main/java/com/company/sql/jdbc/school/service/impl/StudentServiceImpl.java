package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.StudentDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.domain.Student;
import com.company.sql.jdbc.school.service.StudentService;
import com.company.sql.jdbc.school.service.exceptions.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudentsWithThisCourseName(Course course) {
        return studentDao.findStudentsByCourseName(course);
    }

    @Override
    public void saveStudent(Student student) {
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
    public void addStudentToCourse(Student student) {
        studentDao.addStudentCourseSet(student);
    }

    @Override
    public List<Student> showAllStudent() {
        return studentDao.findAll();
    }

    @Override
    public void deleteStudentFromCourse(Integer studentId, Integer courseId) {
        studentDao.deleteStudentFromCourse(studentId, courseId);
    }
}
