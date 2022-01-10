package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GroupDaoImpl implements GroupDao {

    @Override
    public void create(Group group) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a group.sql"), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, group.groupId());
            preparedStatement.setString(2, group.groupName());
            preparedStatement.executeUpdate();

        } catch (Exception cause) {
            throw new DaoException("Group with id: " + group.groupId() + " is already exist");

        }
    }

    @Override
    public Optional<Group> findById(Integer id) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find group by id.sql"))) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Group group = null;
            if (resultSet.next()) {
                group = buildGroup(resultSet);
            }
            return Optional.ofNullable(group);
        } catch (Exception cause) {
            throw new DaoException("Group with id: " + id + " is not found");
        }
    }

    @Override
    public List<Group> findAll() {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all groups.sql"));) {
            var resultSet = preparedStatement.executeQuery();
            List<Group> groups = new ArrayList<>();
            while (resultSet.next()) {
                groups.add(buildGroup(resultSet));
            }
            return groups;
        } catch (Exception cause) {
            throw new DaoException("No groups found");
        }
    }

    @Override
    public void update(Group group) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that update group by id.sql"));) {
            preparedStatement.setInt(1, group.groupId());
            preparedStatement.setString(2, group.groupName());
            preparedStatement.executeUpdate();
        } catch (Exception cause) {
            throw new DaoException("Group with id: " + group.groupId() + " is not found");
        }
    }

    @Override
    public void delete(Integer id) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete group by id.sql"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception cause) {
            throw new DaoException("Group with id: " + id + " is not found");
        }
    }

    @Override
    public List<Group> getGroupsWithLessSomeNumberEqualsStudents(Integer number) {
        List<Group> result = new ArrayList<>();
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find groups with less some number students.sql"))) {
            preparedStatement.setInt(1, number);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group(resultSet.getInt("group_id"),resultSet.getString("group_name"));
                result.add(group);
            }
            return result;
        } catch (Exception cause) {
            throw new DaoException("No groups lower than this number: " + number);
        }
    }

    @Override
    public Map<String, Integer> getCountStudentsIntoGroups() {
        Map<String, Integer> result = new HashMap<>();
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that count students into group.sql"))) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.put(
                        resultSet.getString("name"),
                        resultSet.getInt("count")
                );
            }
            return result;
        } catch (Exception cause) {
            throw new DaoException("Count student is fail");
        }
    }

    @Override
    public Optional<Group> getGroupByName(String groupName) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find group by name.sql"))) {
            preparedStatement.setString(2, groupName);
            var resultSet = preparedStatement.executeQuery();
            Group group = null;
            if (resultSet.next()) {
                group = buildGroup(resultSet);
            }
            return Optional.ofNullable(group);
        } catch (Exception cause) {
            throw new DaoException("Group with this name: " + groupName + " is not exists");
        }
    }

    private Group buildGroup(ResultSet resultSet) throws SQLException {
        return new Group(
                resultSet.getInt("group_id"),
                resultSet.getString("name")
        );
    }
}
