package com.company.sql.jdbc.school.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao<E> {

    void create(E t) throws SQLException;

    Optional<E> findById(Integer id);

    List<E> findAll();

    void update(E t);

    void delete(Integer id);
}
