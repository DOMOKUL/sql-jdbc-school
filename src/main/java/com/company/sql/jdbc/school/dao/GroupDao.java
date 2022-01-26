package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Group;

import java.util.Map;

public interface GroupDao extends CrudDao<Group> {

    Map<String, Integer> getGroupsWithLessSomeNumberEqualsStudents(Integer number);

    Group findById(Integer id);
}
