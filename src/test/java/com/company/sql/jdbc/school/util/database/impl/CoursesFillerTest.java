package com.company.sql.jdbc.school.util.database.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CoursesFillerTest {

    private CoursesFiller coursesFiller;

    @Test
    void fillDatabase_shouldThrowNullPointerException_whenInputEmptyLine() {
        assertThrows(NullPointerException.class, () ->
                coursesFiller.fillDatabase(""));
    }

}