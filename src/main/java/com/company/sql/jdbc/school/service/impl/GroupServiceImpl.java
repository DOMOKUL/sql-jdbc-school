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

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDaoImpl groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Map<String, Integer> getAllGroupsWithLessOrEqualsStudentCount(Integer studentCount) {
        try {
            return groupDao.getGroupsWithLessSomeNumberEqualsStudents(studentCount);
        } catch (DaoException cause) {
            throw new ServiceException("Groups with fewer students " + studentCount + " doesn't exist", cause);
        }
    }

    @Override
    public void printAllGroupsWithLessOrEqualsStudentCount(Integer studentCount) {
        var groups = groupDao.getGroupsWithLessSomeNumberEqualsStudents(studentCount);
        System.out.println("Groups with fewer students: " + studentCount);
        for (var entry : groups.entrySet()) {
            System.out.println("Group name : " + entry.getKey() + " Number of students: " + entry.getValue());
        }
    }

    @Override
    public void createGroup(Group group) {
        try {
            groupDao.create(group);
            System.out.println("Group with id: " + group.groupId() + " successfully created");
        } catch (SQLException cause) {
            throw new ServiceException(cause);
        }
    }
}
