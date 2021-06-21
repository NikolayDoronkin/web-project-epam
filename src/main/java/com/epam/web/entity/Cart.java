package com.epam.web.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Entity{

    private Long targetID;
    private Long menuID;
    private int counter;

    public Cart(Long targetID, Long menuID, int counter) {
        this.targetID = targetID;
        this.menuID = menuID;
        this.counter = counter;
    }

    public Long getTargetID() {
        return targetID;
    }

    public void setTargetID(Long targetID) {
        this.targetID = targetID;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Long getMenuID() {
        return menuID;
    }

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    @Override
    public Long getID() {
        return targetID;
    }
}
