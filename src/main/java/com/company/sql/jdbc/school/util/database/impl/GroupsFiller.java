package com.company.sql.jdbc.school.util.database.impl;

import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;
import com.company.sql.jdbc.school.util.database.TableFiller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupsFiller implements TableFiller {

    @Override
    public void fillDatabase(String filePath) {
        List<String> groups = parseGroupName(Path.of(filePath));
        Collections.shuffle(groups);
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a group.sql"))) {
            for (int i = 1; i <= 10; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, groups.get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException cause) {
            throw new DaoException(cause);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> parseGroupName(Path filePath) {
        List<String> result = new ArrayList<>();
        try (Stream<String> lineStream = Files.lines(filePath)) {
            result = lineStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
