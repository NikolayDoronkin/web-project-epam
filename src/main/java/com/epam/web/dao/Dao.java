package com.epam.web.dao;

import com.epam.web.entity.Entity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T extends Entity>{

    Optional<T> findEntityById(long id) throws DaoException;
    List<T> findAll() throws DaoException, SQLException;

    void save(T entity) throws DaoException;
    void deleteById(long id) throws DaoException;

}
