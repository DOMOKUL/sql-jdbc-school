package com.company.sql.jdbc.school.dao.impl;

import com.company.sql.jdbc.school.dao.GroupDao;
import com.company.sql.jdbc.school.dao.exception.DaoException;
import com.company.sql.jdbc.school.domain.Group;
import com.company.sql.jdbc.school.util.DataSource;
import com.company.sql.jdbc.school.util.SqlScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GroupDaoImplTest {

    private final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");
    GroupDao groupDao;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        container.start();
        var dataSource = new DataSource(container.getJdbcUrl(), container.getDatabaseName(), container.getPassword());
        groupDao = new GroupDaoImpl(dataSource);
        SqlScriptRunner sqlScriptRunner = new SqlScriptRunner(dataSource.getConnection());
        sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("init.sql")));
        sqlScriptRunner.runSqlScript(new BufferedReader(new FileReader("src/test/resources/sql/SQL query that fill tables.sql")));
    }

    @Test
    void create_shouldReturnCorrectGroupId_whenInputGroupData() {
        Group testGroup = new Group(11, "RS-26");
        groupDao.create(testGroup);
        assertEquals(11, testGroup.groupId());
    }

    @Test
    void create_shouldReturnDaoException_whenInputExistGroupId() {
        Group testGroup = new Group(1, "RS-26");
        assertThrows(DaoException.class, () ->
                groupDao.create(testGroup));
    }

    @Test
    void findById_shouldReturnCorrectGroup_whenInputCorrectGroupId() {
        Group testGroup = new Group(1, "RS-26");
        assertEquals(testGroup, groupDao.findById(1));
    }

    @Test
    void findById_shouldThrowDaoException_whenInputNonExistentGroupId() {
        assertThrows(DaoException.class, () ->
                groupDao.findById(10000));
    }

    @Test
    void findAll_shouldReturnNotEmptyList() {
        List<Group> groups = groupDao.findAll();
        assertFalse(groups.isEmpty());
    }

    @Test
    void getGroupsWithLessSomeNumberEqualsStudents_shouldReturnHashMapWithGroupNameAndStudentCount_whenInputCorrectStudentCount() {
        Map<String, Integer> testMap = new HashMap<>();
        var testStudentCount = 10;
        testMap.put("RS-26", 2);
        testMap.put("RD-25", 2);
        testMap.put("RB-24", 2);
        assertEquals(testMap, groupDao.getGroupsWithLessSomeNumberEqualsStudents(testStudentCount));
    }

    @Test
    void getGroupsWithLessSomeNumberEqualsStudents_shouldThrowDaoException_whenInputIncorrectStudentCount() {
        assertThrows(DaoException.class, () ->
                groupDao.getGroupsWithLessSomeNumberEqualsStudents(1));
    }

    @Test
    void update_shouldUpdateGroupData_whenInputExistGroupId() {
        Group testGroup = new Group(1, "RS-26");
        var actual = groupDao.update(testGroup);
        assertTrue(actual);
    }

    @Test
    void update_shouldThrowDaoException_whenInputNotExistGroupId() {
        Group testGroup = new Group(10000, "RS-26");
        assertThrows(DaoException.class, () ->
                groupDao.update(testGroup));
    }

    @Test
    void delete_shouldDeleteGroupData_whenInputExistGroupId() {
        var actual = groupDao.delete(2);
        assertTrue(actual);
    }

    @Test
    void delete_shouldThrowDaoException_whenInputNotExistGroupId() {
        assertThrows(DaoException.class, () ->
                groupDao.delete(10000));
    }
}