package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.User;
import com.epam.web.entity.UsersDto;
import com.epam.web.entity.enums.UserType;
import com.epam.web.mapper.UserMapper;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static final String TABLE_NAME = "user";
    private static final String FIND_BY_USERNAME_AND_PASSWORD = "select id, username, password, score, role  from user where username = ? and password = md5(?)";
    private static final String FIND_BY_USERNAME = "select id, username, password, score, role  from user where username = ?";
    private static final String CREATE = "INSERT INTO user (username, password) VALUES (?, MD5(?))";
    private static final String UPDATE = "UPDATE user SET username = ?, password = MD5(?), role = ? WHERE id = ?";
    private static final String UPDATE_BY_PASSWORD = "UPDATE user SET password = MD5(?) WHERE username = ?";
    private static final String SET_SCORE_ZERO_TO_USER_BY_NAME = "UPDATE user SET score = 0 WHERE username = ?";
    private static final String SET_NEW_SCORES_FOR_USER_BY_NAME = "UPDATE user SET score = ? WHERE username = ?";
    private static final String EDIT_SCORES = "UPDATE user SET score = score + ? WHERE id IN (SELECT user_id FROM target WHERE status = ?)";


    public UserDao(ProxyConnection connection) {
        super(connection, new UserMapper(), TABLE_NAME);
    }

    public Optional<UsersDto> getAllUsers() throws SQLException, DaoException {
        List<User> users = findAll();
        return Optional.of(new UsersDto(users));
    }

    public Optional<User> findUserByLoginAndPassword(final String login, final String password) throws DaoException, ClassNotFoundException {
        return executeSingleResultQuery(FIND_BY_USERNAME_AND_PASSWORD, login, password);
    }

    public void resetPasswordByUserName(String userName, String password) throws DaoException {
        executeUpdate(UPDATE_BY_PASSWORD, password, userName);
    }

    public Optional<User> findUserByID(long userID) throws DaoException {
        return findEntityById(userID);
    }

    public Optional<User> findUserByName(String userName) throws DaoException {
        return executeSingleResultQuery(FIND_BY_USERNAME, userName);
    }

    public void createNewUser(String userName, String password) throws DaoException {
        executeUpdate(CREATE, userName, password);
    }

    public Optional<UsersDto> createScoreZero(String userName) throws DaoException, SQLException {
        executeUpdate(SET_SCORE_ZERO_TO_USER_BY_NAME, userName);
        return getAllUsers();
    }

    public Optional<UsersDto> editUsersScores(String userName, long scores) throws DaoException, SQLException {
        executeUpdate(SET_NEW_SCORES_FOR_USER_BY_NAME, scores, userName);
        return getAllUsers();
    }

    public void refreshScores() throws DaoException {
        executeUpdate(EDIT_SCORES, 5, "PAID");
        executeUpdate(EDIT_SCORES, -5, "DECLINED");
    }


    @Override
    protected void create(User entity) throws DaoException {
        executeQuery(CREATE, entity.getUserName(), entity.getPassword());
    }

    @Override
    protected void update(User entityUser) throws DaoException {
        Optional<User> user = findEntityById(entityUser.getID());
        if (!user.isPresent()) {
            throw new DaoException("This user doesn't exist: " + entityUser.getID());
        }
        executeUpdate(UPDATE, entityUser);
    }
}
