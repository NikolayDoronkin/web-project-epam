package com.epam.web.entity;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {
    private List<Menu> mainMenu;

    public List<Menu> getMenus() {
        return mainMenu;
    }

    public void setMenus(List<Menu> menus) {
        this.mainMenu = menus;
    }

    public MenuDto() {
        mainMenu = new ArrayList<>();
    }

    public void add(List<Menu> menu){
        mainMenu.addAll(menu);
    }

}
