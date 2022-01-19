package com.company.sql.jdbc.school.dao;

import java.util.List;

public interface CrudDao<T> {

    T create(T t);

    T findById(Integer id);

    List<T> findAll();

    boolean update(T e);

    boolean delete(Integer id);
}
