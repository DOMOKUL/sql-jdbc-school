package com.company.sql.jdbc.school.dao;

import com.company.sql.jdbc.school.domain.Group;

import java.util.List;

public interface GroupDao extends CrudDao<Group> {

    List<Group> getGroupsWithLessSomeNumberEqualsStudents(Integer number);
}
