package com.epam.web.entity;

import com.epam.web.entity.enums.FoodType;

public class Menu implements Entity{

    private Long ID;
    private FoodType foodType;
    private String foodName;
    private Long foodPrice;

    public Menu(Long ID, FoodType foodType, String foodName, Long foodPrice) {
        this.ID = ID;
        this.foodType = foodType;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Long foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public Long getID() {
        return ID;
    }

    public void setID(Long ID){
        this.ID = ID;
    }
}
