package com.company.sql.jdbc.school.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<E> {

    void create(E t) throws SQLException;

    Object findById(Integer id);

    List<E> findAll();

    void update(E t);

    void delete(Integer id);
}
