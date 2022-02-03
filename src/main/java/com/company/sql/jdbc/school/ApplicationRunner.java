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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class ApplicationRunner {

    public static void main(String[] args) throws IOException {
        var dataSource = new DataSource();
        try {
            SqlScriptRunner sqlScriptRunner = new SqlScriptRunner(dataSource.getConnection());
            sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("init.sql")));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

        DataBaseFiller dataBaseFiller = new DataBaseFiller(new CoursesFiller(dataSource), new GroupsFiller(dataSource), new StudentsFiller(dataSource));
        dataBaseFiller.createRandomDataInDataBase();
        NavBar navBar = new NavBar(new CourseServiceImpl(new CourseDaoImpl(dataSource)), new GroupServiceImpl(new GroupDaoImpl(dataSource)), new StudentServiceImpl(new StudentDaoImpl(dataSource)));
        navBar.controlBar();
    }

}
