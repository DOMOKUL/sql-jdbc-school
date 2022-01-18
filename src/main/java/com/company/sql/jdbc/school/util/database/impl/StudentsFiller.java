package com.company.sql.jdbc.school.util.database.impl;

import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;
import com.company.sql.jdbc.school.util.database.TableFiller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentsFiller implements TableFiller {

    @Override
    public void fillDatabase(List<String> studentsName) {
        List<String> studentsFirstNameList = new ArrayList<>();
        List<String> studentsLastNameList = new ArrayList<>();
        boolean flag = false;
            try {
                File file = new File("/Users/prologistic/file.txt");
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (!flag) {
                    if (!line.equals(".")){
                        System.out.println(line);
                        line = reader.readLine();
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void fillTableStudents (String studentsFirstName, String studentsLastName){
            List<String> studentsFirstNameList = parseStudentName(Path.of(studentsFirstName));
            List<String> studentsLastNameList = parseStudentName(Path.of(studentsLastName));
            try (var connection = DataSource.getConnection();
                 var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a student.sql"))) {

                for (int i = 1; i <= 200; i++) {
                    preparedStatement.setInt(1, i);
                    preparedStatement.setInt(2, 1 + (int) (Math.random() * (10)));
                    preparedStatement.setString(3, randomizeStudentName(studentsFirstNameList));
                    preparedStatement.setString(4, randomizeStudentName(studentsLastNameList));
                    preparedStatement.executeUpdate();
                }
            } catch (Exception cause) {
                throw new DaoException("Fill table students fail " + cause);
            }
        }

        public void fillTableStudentsCourses () {
            try (var connection = DataSource.getConnection();
                 var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create courses_students.sql"))) {
                for (int i = 1; i < 1 + (int) (Math.random() * 400); i++) {
                    preparedStatement.setInt(1, 1 + (int) (Math.random() * 200));
                    preparedStatement.setInt(2, 1 + (int) (Math.random() * 10));
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException cause) {
                throw new DaoException("Fill table students_courses fail " + cause);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private List<String> parseStudentName (Path filePath){
            List<String> result = new ArrayList<>();
            try (Stream<String> lineStream = Files.lines(filePath)) {
                result = lineStream.collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        private String randomizeStudentName (List < String > nameList) {
            return nameList.get(new Random().nextInt(nameList.size()));
        }
    }
