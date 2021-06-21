package com.epam.web.dao;

import com.epam.web.connection.ConnectionPool;

public class DaoHelperFactory {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public DaoHelper createDao(){
        return new DaoHelper(connectionPool.getConnection());
    }

}
