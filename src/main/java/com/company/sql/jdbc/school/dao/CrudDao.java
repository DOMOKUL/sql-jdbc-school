package com.company.sql.jdbc.school.dao;

import java.util.List;

public interface CrudDao<E> {
    void create(E t);

    Object findById(Integer id);

    List<E> findAll();

    void update(E t);

    void delete(Integer id);
}
