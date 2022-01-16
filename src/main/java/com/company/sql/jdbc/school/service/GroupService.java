package com.company.sql.jdbc.school.service;

import com.company.sql.jdbc.school.domain.Group;

import java.util.Map;

public interface GroupService {

    Map<String, Integer> getAllGroupsWithLessOrEqualsStudentCount(Integer studentCount);

    void createGroup(Group group);
}
