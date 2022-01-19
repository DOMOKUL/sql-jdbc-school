package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.CourseDao;
import com.company.sql.jdbc.school.domain.Course;
import com.company.sql.jdbc.school.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    private final Course course = new Course(1, "math", "love");

    @Mock
    private CourseDao courseDao;

    private CourseService courseService;

    @Test
    void createCourse_shouldCreateCourse() {
        when(courseDao.create(new Course(1, "math", "love"))).thenReturn(course);
        Course actual = courseService.createCourse(course);

        assertEquals(course, actual);

//        verify(courseDao, times(1)).create(course);
    }
}

