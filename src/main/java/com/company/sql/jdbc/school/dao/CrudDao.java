package com.company.sql.jdbc.school.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao<E> {

    void create(E e) throws SQLException;

    Optional<E> findById(Integer id);

    List<E> findAll();

    void update(E e);

    void delete(Integer id);
}
