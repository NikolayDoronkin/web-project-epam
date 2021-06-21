package com.epam.web.mapper;

import com.epam.web.entity.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements Mapper<Cart>{
    @Override
    public Cart map(ResultSet resultSet) throws SQLException {
        Long targetID = resultSet.getLong("user_id");
        Long menuID = resultSet.getLong("menu_id");
        int counter = resultSet.getInt("counter");

        return new Cart(targetID, menuID, counter);
    }
}
