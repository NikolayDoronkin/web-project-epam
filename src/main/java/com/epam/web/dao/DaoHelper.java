package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection proxyConnection;

    public DaoHelper(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public UserDao createUserDao() {
        return new UserDao(proxyConnection);
    }

    public MenuDao createMenuDao() {
        return new MenuDao(proxyConnection);
    }

    public CartDao createCartDao() {
        return new CartDao(proxyConnection);
    }

    public TargetDao createTargetDao() {
        return new TargetDao(proxyConnection);
    }

    public ReviewDao createReviewDao() {
        return new ReviewDao(proxyConnection);
    }

    @Override
    public void close() throws SQLException {
        proxyConnection.setAutoCommit(true);
        proxyConnection.close();
    }
}
