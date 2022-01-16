package com.company.sql.jdbc.school.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<E> {

    void create(E e) throws SQLException;

    List<E> findAll();

    void update(E e);

    void delete(Integer id);
}
