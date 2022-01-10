package com.company.sql.jdbc.school.dao.implementation;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;

import java.sql.*;
import java.util.*;

public class GroupDaoImpl implements GroupDao {

    @Override
    public void create(Group group) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a group.sql"), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, group.groupId());
            preparedStatement.setString(2, group.groupName());
            preparedStatement.executeUpdate();

        } catch (SQLException cause) {
            throw new DaoException("Creating group fail", cause);
        } catch (Exception e) {
            e.printStackTrace();
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
            throw new DaoException("find group fail", cause);
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
            throw new DaoException("find group fail", cause);
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
            throw new DaoException("update group fail", cause);
        }
    }

    @Override
    public void delete(Integer id) {
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete group by id.sql"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception cause) {
            throw new DaoException("Delete group fail", cause);
        }
    }

    @Override
    public List<Group> getGroupsWithLessSomeNumberEqualsStudents(Integer number) {
        return null;
    }

    @Override
    public Map<String, Integer> getCountStudentsIntoGroups() {
        Map<String, Integer> result = new HashMap<>();
        try (var connection = DataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that count students into group.sql"))) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.put(
                        resultSet.getString("name"),
                        resultSet.getInt("count")
                );
            }
            return result;
        } catch (Exception cause) {
            throw new DaoException("count student fail", cause);
        }
    }

    private Group buildGroup(ResultSet resultSet) throws SQLException {
        return new Group(
                resultSet.getInt("group_id"),
                resultSet.getString("name")
        );
    }
}
