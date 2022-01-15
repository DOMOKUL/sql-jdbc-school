package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.service.GroupService;
import com.company.sql.jdbc.school.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Map<String,Integer> getAllGroupsWithLessOrEqualsStudentCount(Integer studentCount) {
        return groupDao.getGroupsWithLessSomeNumberEqualsStudents(studentCount);
    }

    @Override
    public void createGroup(Group group) {
        try {
            groupDao.create(group);
        } catch (SQLException cause) {
            throw new ServiceException(cause);
        }
    }
}
