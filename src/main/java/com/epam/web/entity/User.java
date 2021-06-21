package com.epam.web.entity;

import com.epam.web.entity.enums.UserType;

public class User implements Entity{

    private long ID;
    private String userName;
    private String password;
    private UserType userType;
    private int privilegeScores;

    public User(long ID, String userName, String password, UserType userType, int privilegeScores) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.privilegeScores = privilegeScores;
    }

    public User(String userName, String password, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getPrivilegeScores() {
        return privilegeScores;
    }

    public void setPrivilegeScores(int privilegeScores) {
        this.privilegeScores = privilegeScores;
    }


    @Override
    public Long getID() {
        return ID;
    }

    public void setID(Long ID){
        this.ID = ID;
    }
}
