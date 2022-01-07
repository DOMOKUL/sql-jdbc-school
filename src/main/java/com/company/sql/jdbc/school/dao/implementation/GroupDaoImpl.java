package com.company.sql.jdbc.school.dao.implementation;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.exception.DaoException;
import com.company.sql.jdbc.school.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GroupDaoImpl implements GroupDao {

    private static final GroupDaoImpl INSTANCE = new GroupDaoImpl();
    private static final String SAVE_SQL = """
            INSERT INTO groups(group_id, name)
            VALUES (?, ?);
            """;

    private GroupDaoImpl() {
    }

    public static GroupDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Group group) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, group.groupId());
            preparedStatement.setString(2, group.groupName());
            preparedStatement.executeUpdate();

        } catch (SQLException cause) {
            throw new DaoException("Creating group fail", cause);
        }
    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public List<Group> findAll() {
        return null;
    }

    @Override
    public void update(Group t) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Group> getGroupsWithLessSomeNumberEqualsStudents(Integer number) {
        return null;
    }
}
