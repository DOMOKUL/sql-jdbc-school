package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.dao.impl.GroupDaoImpl;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.service.GroupService;
import com.company.sql.jdbc.school.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.Map;

public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    public GroupServiceImpl(GroupDaoImpl groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Map<String, Integer> getAllGroupsWithLessOrEqualsStudentCount(Integer studentCount) {
        try {
            var groups = groupDao.getGroupsWithLessSomeNumberEqualsStudents(studentCount);
            System.out.println("Группы с числом студентов, меньше: " + studentCount);
            for (var entry : groups.entrySet()) {
                System.out.println("Название группы: " + entry.getKey() + " Количество студентов: " + entry.getValue());
            }
            return groups;
        } catch (DaoException cause) {
            throw new ServiceException("Групп с числом студентов, меньшим " + studentCount + " не существует", cause);
        }
    }

    @Override
    public void createGroup(Group group) {
        try {
            groupDao.create(group);
            System.out.println("Группа с id: " + group.groupId() + " успешно создана");
        } catch (SQLException cause) {
            throw new ServiceException(cause);
        }
    }
}
