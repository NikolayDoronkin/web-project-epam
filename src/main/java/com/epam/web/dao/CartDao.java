package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Cart;
import com.epam.web.entity.CartDto;
import com.epam.web.entity.Menu;
import com.epam.web.mapper.CartMapper;

import java.util.List;
import java.util.Optional;

public class CartDao extends AbstractDao<Cart> {

    private static final String TABLE_NAME_TARGET = "cart";
    private static final String INSERT = "INSERT INTO cart (user_id, menu_id) VALUES (?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM cart WHERE user_id = ?";


    public CartDao(ProxyConnection connection) {
        super(connection, new CartMapper(), TABLE_NAME_TARGET);
    }

    public void insert(long userID, long menuID) throws DaoException {
        executeUpdate(INSERT, userID, menuID);
    }

    public void refreshCartByID(long userID) throws DaoException {
        executeUpdate(DELETE_BY_ID, userID);
    }


    @Override
    protected void create(Cart entity) throws DaoException {

    }

    @Override
    protected void update(Cart entity) throws DaoException {

    }
}