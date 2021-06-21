package com.epam.web.entity;

import java.util.List;

public class UsersDto {

    private List<User> users;

    public UsersDto(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
