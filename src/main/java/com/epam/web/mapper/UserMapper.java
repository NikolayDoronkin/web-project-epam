package com.epam.web.mapper;

import com.epam.web.entity.User;
import com.epam.web.entity.enums.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override

    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");

        String userName = resultSet.getString("username");
        String password = resultSet.getString("password");
        String userValue = resultSet.getString("role");
        UserType userType = UserType.valueOf(userValue);

        int privilegeScores = resultSet.getInt("score");

        return new User(id, userName, password, userType, privilegeScores);

    }
}
