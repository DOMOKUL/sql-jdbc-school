package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Group;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GroupDao extends CrudDao<Group> {

    Map<String,Integer> getGroupsWithLessSomeNumberEqualsStudents(Integer number);

    Map<String,Integer> getCountStudentsIntoGroups();

    Optional<Group> findById(Integer id);

    Optional<Group> getGroupByName(String groupName);
}
