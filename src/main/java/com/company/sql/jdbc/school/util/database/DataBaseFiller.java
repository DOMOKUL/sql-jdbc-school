package com.company.sql.jdbc.school.util.database;

import com.company.sql.jdbc.school.util.database.impl.StudentsFiller;

import java.io.FileNotFoundException;

public record DataBaseFiller(TableFiller coursesFiller,
                             TableFiller groupsFiller,
                             StudentsFiller studentsFiller) {

    public void createRandomDataInDataBase() {
        try {
            coursesFiller.fillDatabase("src/main/resources/generatedData/course-name-and-description.txt");
            groupsFiller.fillDatabase("src/main/resources/generatedData/group-name.txt");
            studentsFiller.fillTableStudents("src/main/resources/generatedData/student-first-name.txt", "src/main/resources/generatedData/student-last-name.txt");
            studentsFiller.fillTableStudentsCourses();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
