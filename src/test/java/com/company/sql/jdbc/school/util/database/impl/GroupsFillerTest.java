package com.company.sql.jdbc.school.util.database.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupsFillerTest {

    private GroupsFiller groupsFiller;

    @Test
    void fillDatabase_shouldThrowNullPointerException_whenInputEmptyLine() {
        assertThrows(NullPointerException.class,()->
                groupsFiller.fillDatabase(""));
    }
}