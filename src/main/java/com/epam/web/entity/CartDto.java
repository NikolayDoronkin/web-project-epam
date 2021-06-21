package com.epam.web.entity;

import java.util.List;

public class CartDto {

    private List<Menu> cartList;

    public CartDto(List<Menu> cartList) {
        this.cartList = cartList;
    }

    public List<Menu> getCartList() {
        return cartList;
    }

    public void setCartList(List<Menu> cartList) {
        this.cartList = cartList;
    }

    public long calculate(){
        long summary = 0;
        for (Menu menu: cartList) {
            summary += menu.getFoodPrice();
        }
        return summary;
    }
}
