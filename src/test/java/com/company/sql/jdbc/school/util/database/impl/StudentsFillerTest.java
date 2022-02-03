package com.company.sql.jdbc.school.util.database.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentsFillerTest {

    StudentsFiller studentsFiller;

    @Test
    void fillTableStudents() {
        assertThrows(NullPointerException.class, () ->
                studentsFiller.fillTableStudents("", ""));
    }
}