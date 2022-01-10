package com.company.sql.jdbc.school.service;

import com.company.sql.jdbc.school.domain.Group;

import java.util.*;

public interface GroupService {

    List<Group> getAllGroupsWithLessOrEqualsStudentCount(Integer studentCount);

    void saveGroup(Group group);
}
