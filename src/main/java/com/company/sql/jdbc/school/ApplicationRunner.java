package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.dao.impl.CourseDaoImpl;
import com.company.sql.jdbc.school.dao.impl.GroupDaoImpl;
import com.company.sql.jdbc.school.dao.impl.StudentDaoImpl;
import com.company.sql.jdbc.school.service.impl.CourseServiceImpl;
import com.company.sql.jdbc.school.service.impl.GroupServiceImpl;
import com.company.sql.jdbc.school.service.impl.StudentServiceImpl;
import com.company.sql.jdbc.school.ui.NavBar;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlScriptRunner;
import com.company.sql.jdbc.school.util.database.DataBaseFiller;
import com.company.sql.jdbc.school.util.database.impl.CoursesFiller;
import com.company.sql.jdbc.school.util.database.impl.GroupsFiller;
import com.company.sql.jdbc.school.util.database.impl.StudentsFiller;

import java.io.*;
import java.sql.SQLException;

public class ApplicationRunner {

    public static void main(String[] args) {
        try {
            SqlScriptRunner sqlScriptRunner = new SqlScriptRunner(DataSource.getConnection());
            sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("src/main/resources/sql/init.sql")));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

        DataBaseFiller dataBaseFiller = new DataBaseFiller(new CoursesFiller(), new GroupsFiller(), new StudentsFiller());
        dataBaseFiller.createRandomDataInDataBase();
        NavBar navBar = new NavBar(new CourseServiceImpl(new CourseDaoImpl()), new GroupServiceImpl(new GroupDaoImpl()), new StudentServiceImpl(new StudentDaoImpl()));
        navBar.controlBar();
    }
}
