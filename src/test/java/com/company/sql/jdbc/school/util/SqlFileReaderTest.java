package com.company.sql.jdbc.school.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SqlFileReaderTest {

    @Test
    void readSqlFile_shouldReturnString_whenInputCorrectFilePath() {
        try {
            var actual = SqlFileReader.readSqlFile("src/test/resources/sql/SQL query that create a course.sql");
            var expected = "INSERT INTO courses(course_id, name,description) VALUES (?, ?, ?);";
            assertEquals(expected, actual);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void readSqlFile_shouldThrowNullPointerException_whenInputNull(){
            assertThrows(NullPointerException.class, () ->
                    SqlFileReader.readSqlFile(null));
    }

    @Test
    void readSqlFile_shouldThrowIOException_whenInputIncorrectFilePath(){
        assertThrows(IOException.class,()->
                SqlFileReader.readSqlFile(""));
    }
}