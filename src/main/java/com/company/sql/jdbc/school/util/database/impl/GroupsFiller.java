package com.company.sql.jdbc.school.util.database.impl;

import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.database.DataBaseFiller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupsFiller implements DataBaseFiller {

    private static final String SAVE_SQL = """
            INSERT INTO groups(group_id, name)
            VALUES (?, ?);
            """;

    @Override
    public void fillDatabase(String filePath) throws FileNotFoundException {
        List<String> groups = parseGroupName(Path.of(filePath));
        Collections.shuffle(groups);
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 1; i <= 10; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, groups.get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException cause) {
            throw new DaoException("fill table groups fail ");
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
