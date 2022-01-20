package com.company.sql.jdbc.school.service.impl;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.dao.impl.GroupDaoImpl;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.service.GroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    private final Group group = new Group(1, "RS-25");
    private final Map<String, Integer> groups = Map.of(group.groupName(), 10, "RS-26", 20);

    private final GroupDaoImpl groupDao = Mockito.mock(GroupDaoImpl.class);

    private final GroupService groupService = new GroupServiceImpl(groupDao);

    @Test
    void getAllGroupsWithLessOrEqualsStudentCount() {
        Integer studentCount = 20;
        when(groupDao.getGroupsWithLessSomeNumberEqualsStudents(studentCount)).thenReturn(groups);

        assertEquals(groups, groupService.getAllGroupsWithLessOrEqualsStudentCount(studentCount));

        verify(groupDao, times(1)).getGroupsWithLessSomeNumberEqualsStudents(studentCount);
    }

    @Test
    void createGroup() {
        when(groupService.createGroup(new Group(1, "RS-25"))).thenReturn(group);
        Group actual = groupService.createGroup(group);

        assertEquals(group, actual);

        verify(groupDao, times(1)).create(group);
    }
}