package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.CartDto;

import java.sql.SQLException;
import java.util.Optional;

public class CartService {

    private final DaoHelperFactory daoHelperFactory;

    public CartService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void pullIntoCart(long userID, long menuID) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.createDao()){
            CartDao cartDao = daoHelper.createCartDao();
            cartDao.insert(userID, menuID);
        }catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

    public void refreshCartByID(long userID) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.createDao()){
            CartDao cartDao = daoHelper.createCartDao();
            cartDao.refreshCartByID(userID);
        }catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

    public Optional<CartDto> getAllCart(long userID, String lang) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.createDao()){
            MenuDao menuDao = daoHelper.createMenuDao();
            return menuDao.getCarts(userID, lang);
        }catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

    public void deleteElements(long userID, long foodId) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.createDao()){
            MenuDao menuDao = daoHelper.createMenuDao();
            menuDao.deleteElement(userID, foodId);
        }catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

}