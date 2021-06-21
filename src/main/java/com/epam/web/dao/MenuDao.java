package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.*;
import com.epam.web.entity.enums.FoodType;
import com.epam.web.mapper.MenuMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class MenuDao extends AbstractDao<Menu> {

    private static final String TABLE_NAME = "menu";
    private static final String SHOW_CONCRETE_MENU_EN = "SELECT id, type, food, price FROM menu WHERE type = ?";
    private static final String SHOW_CONCRETE_MENU_ES = "SELECT id, type, food_es, price FROM menu WHERE type = ?";
    private static final String SHOW_CONCRETE_MENU_RU = "SELECT id, type, food_ru, price FROM menu WHERE type = ?";

    private static final String DELETE_ELEMENT_BY_NAME = "DELETE FROM menu WHERE user_id = ? AND menu_id IN (SELECT id FROM menu WHERE food = ?)";
    private static final String CREATE = "INSERT INTO menu (type, food, price) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE menu SET price = ? WHERE id = ?";

    private static final String FIND_BY_FOOD_AND_PRICE_EN = "SELECT * FROM menu WHERE type = ? AND food = ?";
    private static final String FIND_BY_FOOD_AND_PRICE_ES = "SELECT * FROM menu WHERE type = ? AND food_es = ?";
    private static final String FIND_BY_FOOD_AND_PRICE_RU = "SELECT * FROM menu WHERE type = ? AND food_ru = ?";

    private static final String GET_CART_EN = "SELECT id, type, food, price FROM menu  WHERE id IN (SELECT menu_id FROM cart WHERE user_id=?)";
    private static final String GET_CART_ES = "SELECT id, type, food_es, price FROM menu  WHERE id IN (SELECT menu_id FROM cart WHERE user_id=?)";
    private static final String GET_CART_RU = "SELECT id, type, food_ru, price FROM menu  WHERE id IN (SELECT menu_id FROM cart WHERE user_id=?)";

    private static final String DELETE_ELEMENT = "DELETE FROM cart WHERE user_id = ? AND menu_id = ?";

    private static final String FIND_BY_FOOD_NAME_EN = "select id, type, food, price from menu where id = ?";
    private static final String FIND_BY_FOOD_NAME_ES = "select id, type, food_es, price from menu where id = ?";
    private static final String FIND_BY_FOOD_NAME_RU = "select id, type, food_ru, price from menu where id = ?";


    public MenuDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new MenuMapper(), "menu");
    }

    public Optional<MenuDto> showAllMenu(String submenu, String lang) throws DaoException {
        MenuDto menuDto = new MenuDto();
        List<Menu> subMenu = selectLanguage(lang,
                executeQuery(SHOW_CONCRETE_MENU_EN, submenu),
                executeQuery(SHOW_CONCRETE_MENU_ES, submenu),
                executeQuery(SHOW_CONCRETE_MENU_RU, submenu));

        if(subMenu.isEmpty()){
            return Optional.empty();
        }
        menuDto.add(subMenu);

        return Optional.of(menuDto);

    }

    public Optional<Menu> select(int foodId, String lang) throws DaoException {
        switch(lang){
            case "en":
                return executeSingleResultQuery(FIND_BY_FOOD_NAME_EN, foodId);
            case "es":
                return executeSingleResultQuery(FIND_BY_FOOD_NAME_ES, foodId);
            case "ru":
                return executeSingleResultQuery(FIND_BY_FOOD_NAME_RU, foodId);
            default:
                return Optional.empty();
        }
    }

    public Optional<CartDto> getCarts(long userID, String lang) throws DaoException {
        List<Menu> cart = selectLanguage(lang, executeQuery(GET_CART_EN, userID), executeQuery(GET_CART_ES, userID), executeQuery(GET_CART_RU, userID));
        if(!cart.isEmpty()){
            CartDto cartDto = new CartDto(cart);
            return Optional.of(cartDto);
        }
        return Optional.empty();
    }

    private List<Menu> selectLanguage(String lang, List<Menu> menus, List<Menu> menus2, List<Menu> menus3) throws DaoException {
        List<Menu> cart = new ArrayList<>();
        switch(lang){
            case "en":
                cart = menus;
                break;
            case "es":
                cart = menus2;
                break;
            case "ru":
                cart = menus3;
                break;
        }
        return cart;
    }

    public void deleteElement(long userID, long foodId) throws DaoException {
        executeUpdate(DELETE_ELEMENT, userID, foodId);
    }

    public Optional<MenuDto> deleteFromMenuByID(long menuID, String menuType, String lang) throws DaoException {
        deleteById(menuID);
        return showAllMenu(menuType, lang);
    }


    public Optional<MenuDto> saveElement(String foodType, String foodName, long price, String lang) throws DaoException {
        Optional<Menu> menu = Optional.empty();
        switch(lang){
            case "en":
                menu = executeSingleResultQuery(FIND_BY_FOOD_AND_PRICE_EN, foodType, foodName);
                break;
            case "es":
                menu = executeSingleResultQuery(FIND_BY_FOOD_AND_PRICE_ES, foodType, foodName);
                break;
            case "ru":
                menu = executeSingleResultQuery(FIND_BY_FOOD_AND_PRICE_RU, foodType, foodName);
                break;
        }
        if (menu.isPresent()) {
            executeUpdate(UPDATE, price, menu.get().getID());
        } else {
            executeUpdate(CREATE, foodType, foodName, price);
        }
        return showAllMenu(foodType, lang);
    }

    @Override
    protected void create(Menu entity) throws DaoException {
        executeQuery(CREATE, entity);
    }

    @Override
    protected void update(Menu entity) throws DaoException {
        Optional<Menu> menu = findEntityById(entity.getID());
        if (!menu.isPresent()) {
            throw new DaoException("This user doesn't exist: " + entity.getID());
        }
        executeUpdate(UPDATE, entity);
    }
}