package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.MenuDao;
import com.epam.web.entity.CartDto;
import com.epam.web.entity.Menu;
import com.epam.web.entity.MenuDto;

import java.sql.SQLException;
import java.util.Optional;

public class MenuService {

    private final DaoHelperFactory daoHelperFactory;

    public MenuService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<MenuDto> showMenu(String submenu, String lang) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.createDao()){
            MenuDao menuDao = daoHelper.createMenuDao();
            return menuDao.showAllMenu(submenu, lang);
        }catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

    public Optional<MenuDto> deleteFromMenu(long menuID, String menuType, String lang) throws ServiceException{
        try(DaoHelper daoHelper = daoHelperFactory.createDao()){
            MenuDao menuDao = daoHelper.createMenuDao();
            return menuDao.deleteFromMenuByID(menuID, menuType, lang);
        }catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

    public Optional<MenuDto> saveMenu(String foodType, String foodName, long price, String lang) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.createDao()){
            MenuDao menuDao = daoHelper.createMenuDao();
            return menuDao.saveElement(foodType, foodName, price, lang);
        }catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

    public Optional<Menu> selectFromMenu(int foodId, String lang) throws ServiceException, SQLException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            MenuDao menuDao = daoHelper.createMenuDao();
            return menuDao.select(foodId, lang);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

}
