package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Group;

import java.util.List;
import java.util.Map;

public interface GroupDao extends CrudDao<Group> {

    List<Group> getGroupsWithLessSomeNumberEqualsStudents(Integer number);

    Map<String,Integer> getCountStudentsIntoGroups();
}
