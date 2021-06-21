package com.epam.web.dao;


import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Entity;
import com.epam.web.mapper.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {

    private static final String FIND_BY_ID = "SELECT * FROM %s WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM %s";
    private static final String DELETE = "DELETE FROM %s WHERE id = ?";

    private final ProxyConnection connection;
    private final String tableName;
    private final Mapper<T> mapper;

    public AbstractDao(ProxyConnection connection, Mapper<T> mapper, String tableName) {
        this.connection = connection;
        this.mapper = mapper;
        this.tableName = tableName;
    }

    private PreparedStatement createStatement(String query, Object... parameters) throws SQLException {
        query = String.format(query, parameters);
        PreparedStatement statement = connection.prepareStatement(query);
        for (int index = 0; index < parameters.length; index++) {
            statement.setObject(index + 1, parameters[index]);
        }
        return statement;
    }

    @Override
    public Optional<T> findEntityById(long id) throws DaoException {
        String query = String.format(FIND_BY_ID, tableName);
        return executeSingleResultQuery(query, id);
    }

    protected Optional<T> executeSingleResultQuery(String query, Object ... parameters) throws DaoException {
        List<T> entityElements = executeQuery(query, parameters);
        int size = entityElements.size();
        if(size > 1){
            throw new DaoException("More than one result: " + size);
        }
        if(size > 0){
            return Optional.of(entityElements.get(0));
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() throws DaoException, SQLException {
        String query = String.format(FIND_ALL, tableName);
        return executeQuery(query);
    }

    protected List<T> executeQuery(String query, Object... parameters) throws DaoException {
        try(PreparedStatement statement = createStatement(query, parameters)){
            ResultSet resultSet = statement.executeQuery();
            List<T> entityElements = new ArrayList<>();
            while(resultSet.next()){
                T entityElement = mapper.map(resultSet);
                entityElements.add(entityElement);
            }
            return entityElements;
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }

    @Override
    public void save(T entity) throws DaoException {
        if (entity.getID() == null) {
            create(entity);
        } else {
            update(entity);
        }
    }

    @Override
    public void deleteById(long id) throws DaoException {
        String query = String.format(DELETE, tableName);
        executeUpdate(query, id);
    }

    protected void executeUpdate(String query, Object... parameters) throws DaoException {
        try(PreparedStatement statement = createStatement(query, parameters)) {
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }

    protected abstract void create(T entity) throws DaoException;

    protected abstract void update(T entity) throws DaoException;

}
