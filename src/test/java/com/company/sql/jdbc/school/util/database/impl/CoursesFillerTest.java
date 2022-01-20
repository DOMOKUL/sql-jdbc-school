package com.company.sql.jdbc.school.util.database.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CoursesFillerTest {

    private CoursesFiller coursesFiller;

    @BeforeEach
    void setUp() throws SQLException, IOException {
    }

    @Test
    void fillDatabase_shouldThrowNullPointerException_whenInputEmptyLine() {
        assertThrows(NullPointerException.class,()->
                coursesFiller.fillDatabase(""));
    }

    @Test
    void fillDatabase_shouldIOException_whenInputEmptyFile() {
        assertThrows(NullPointerException.class,()->
                coursesFiller.fillDatabase("src/test/resources/generatedTestData/course-name-and-description-empty.txt"));
    }

    @Test
    void fillDatabase_shouldSQLException_whenInputEmptyFile() {//todo сделать sqlException
        assertThrows(NullPointerException.class,()->
                coursesFiller.fillDatabase("src/test/resources/generatedTestData/course-name-and-description-empty.txt"));
    }
}