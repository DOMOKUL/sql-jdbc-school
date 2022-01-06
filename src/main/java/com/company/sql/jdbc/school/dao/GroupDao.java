package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.ConnectionManager;

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

public class GroupDao {

    private static final GroupDao INSTANCE = new GroupDao();
    private static final String SAVE_SQL = """
            INSERT INTO groups(group_id, name)
            VALUES (?, ?);
            """;

    private GroupDao() {
    }

    public static GroupDao getInstance() {
        return INSTANCE;
    }

    public void fillTableGroup(String filePath) {
        List<String> groups = parseGroupName(Path.of(filePath));
        Collections.shuffle(groups);
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 1; i <= 10; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, groups.get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwable) {
            throw new DaoException("fill table groups fail ", throwable);
        }
    }

    public Group createGroup(Group group) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, group.getGroupId());
            preparedStatement.setString(2, group.getGroupName());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                group.setGroupId(generatedKeys.getInt("group_id"));
            }
            return group;
        } catch (SQLException throwable) {
            throw new DaoException("Creating group fail", throwable);
        }

    }

    public void findAllGroupsWithLessOrEqualsStudentCount() {
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
