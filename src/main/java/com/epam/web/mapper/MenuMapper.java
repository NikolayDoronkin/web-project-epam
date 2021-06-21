package com.epam.web.mapper;

import com.epam.web.entity.Menu;
import com.epam.web.entity.enums.FoodType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMapper implements Mapper<Menu>{
    @Override
    public Menu map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");

        String foodValue = resultSet.getString("type");
        FoodType foodType = FoodType.valueOf(foodValue);
        String foodName = "";
        try{
            foodName = resultSet.getString("food");
        } catch (Exception e){
            try{
                foodName = resultSet.getString("food_es");
            } catch (Exception ex){
                try {
                    foodName = resultSet.getString("food_ru");
                }catch (Exception exception){}
            }
        }

        Long price = resultSet.getLong("price");

        return new Menu(id, foodType, foodName, price);

    }
}
