package com.company.sql.jdbc.school;

import com.company.sql.jdbc.school.dao.impl.CourseDaoImpl;
import com.company.sql.jdbc.school.dao.impl.GroupDaoImpl;
import com.company.sql.jdbc.school.dao.impl.StudentDaoImpl;
import com.company.sql.jdbc.school.service.CourseService;
import com.company.sql.jdbc.school.service.GroupService;
import com.company.sql.jdbc.school.service.StudentService;
import com.company.sql.jdbc.school.service.impl.CourseServiceImpl;
import com.company.sql.jdbc.school.service.impl.GroupServiceImpl;
import com.company.sql.jdbc.school.service.impl.StudentServiceImpl;
import com.company.sql.jdbc.school.ui.NavBar;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlScriptRunner;
import com.company.sql.jdbc.school.util.database.DataBaseFiller;
import com.company.sql.jdbc.school.util.database.TableFiller;
import com.company.sql.jdbc.school.util.database.impl.CoursesFiller;
import com.company.sql.jdbc.school.util.database.impl.GroupsFiller;
import com.company.sql.jdbc.school.util.database.impl.StudentsFiller;

import java.io.StringReader;
import java.sql.SQLException;

public class ApplicationRunner {

    public static void main(String[] args) {
        try {
            SqlScriptRunner sqlScriptRunner = new SqlScriptRunner(DataSource.getConnection());
            sqlScriptRunner.runSqlScript(new StringReader("src/main/resources/sql/init.sql"));
            TableFiller coursesFiller = new CoursesFiller();
            TableFiller groupsFiller = new GroupsFiller();
            StudentsFiller studentsFiller = new StudentsFiller();
            DataBaseFiller dataBaseFiller = new DataBaseFiller(coursesFiller,groupsFiller,studentsFiller);
            dataBaseFiller.createRandomDataInDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
        GroupService groupService = new GroupServiceImpl(new GroupDaoImpl());
        StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
        NavBar navBar = new NavBar();
        navBar.controlBar(courseService, groupService, studentService);
    }
}
