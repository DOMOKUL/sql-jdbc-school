package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlFileReader;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupDaoImpl implements GroupDao {

    private final DataSource dataSource;

    public GroupDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Group create(Group group) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that create a group.sql"))) {
            preparedStatement.setInt(1, group.groupId());
            preparedStatement.setString(2, group.groupName());
            preparedStatement.executeUpdate();
            return group;

        } catch (SQLException cause) {
            throw new DaoException("Group with id: " + group.groupId() + " is already exist");
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public Group findById(Integer id) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find group by id.sql"))) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Group group = null;
            if (resultSet.next()) {
                group = buildGroup(resultSet);
            }
            if (group == null) {
                throw new DaoException("Group with id: " + id + " is not found");
            }
            return group;
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public List<Group> findAll() {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find all groups.sql"))) {
            var resultSet = preparedStatement.executeQuery();
            List<Group> groups = new ArrayList<>();
            while (resultSet.next()) {
                groups.add(buildGroup(resultSet));
            }
            return groups;
        } catch (SQLException cause) {
            throw new DaoException("No courses found ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public boolean update(Group group) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that update group by id.sql"))) {
            preparedStatement.setInt(1, group.groupId());
            preparedStatement.setString(2, group.groupName());
            preparedStatement.setInt(3, group.groupId());
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return true;
            }
            throw new DaoException("Group with id: " + group.groupId() + " is not found");

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public boolean delete(Integer groupId) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that delete group by id.sql"))) {
            preparedStatement.setInt(1, groupId);
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return true;
            }
            throw new DaoException("Group with id: " + groupId + " is not exist");

        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    @Override
    public Map<String, Integer> getGroupsWithLessSomeNumberEqualsStudents(Integer number) {
        Map<String, Integer> result = new HashMap<>();
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SqlFileReader.readSqlFile("src/main/resources/sql/queries/SQL query that find groups with less some number students.sql"))) {
            preparedStatement.setInt(1, number);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var count = resultSet.getInt("count");
                var name = resultSet.getString("name");
                result.put(name, count);
            }
            if (result.isEmpty()) {
                throw new DaoException("No groups lower than this number: " + number);
            }
            return result;
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        } catch (IOException e) {
            throw new DaoException("File not found");
        }
    }

    private Group buildGroup(ResultSet resultSet) throws SQLException {
        return new Group(
                resultSet.getInt("group_id"),
                resultSet.getString("name")
        );
    }
}
