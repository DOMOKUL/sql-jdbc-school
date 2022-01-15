package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.ui.NavBar;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;
import com.company.sql.jdbc.school.util.database.DataBaseFiller;
import com.company.sql.jdbc.school.util.database.impl.CoursesFiller;
import com.company.sql.jdbc.school.util.database.impl.GroupsFiller;
import com.company.sql.jdbc.school.util.database.impl.StudentsFiller;

import java.io.IOException;
import java.sql.SQLException;

public class ApplicationRunner {

    public static void main(String[] args) {
        try {
            var connection = DataSource.getConnection();
            try (var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/init.sql"))) {
                preparedStatement.executeUpdate();
            }
            DataBaseFiller coursesFiller = new CoursesFiller();
            DataBaseFiller groupsFiller = new GroupsFiller();
            StudentsFiller studentsFiller = new StudentsFiller();
            coursesFiller.fillDatabase("src/main/resources/generatedData/course-name-and-descrpiption.txt");
            groupsFiller.fillDatabase("src/main/resources/generatedData/group-name.txt");
            studentsFiller.fillTableStudents("src/main/resources/generatedData/student-first-name.txt", "src/main/resources/generatedData/student-last-name.txt");
            studentsFiller.fillTableStudentsCourses();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        NavBar menu = new NavBar();
        menu.controlMenu();
    }
}
