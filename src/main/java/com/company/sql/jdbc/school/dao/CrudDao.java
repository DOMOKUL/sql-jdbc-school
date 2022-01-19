package com.company.sql.jdbc.school.dao;

import java.util.List;

public interface CrudDao<E> {

    void create(E e);

    E findById(Integer id);

    List<E> findAll();

    boolean update(E e);

    boolean delete(Integer id);
}
