package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.entity.User;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.UsersDto;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String userName, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findUserByLoginAndPassword(userName, password);
        } catch (SQLException | DaoException | ClassNotFoundException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> getUserInformation(long userID) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findUserByID(userID);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> getUserInformation(String userName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findUserByName(userName);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void resetPassword(String userName, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            userDao.resetPasswordByUserName(userName, password);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void createUser(String userName, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            userDao.createNewUser(userName, password);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<UsersDto> getUsers() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.getAllUsers();
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<UsersDto> blockUser(String userName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.createScoreZero(userName);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<UsersDto> editScores(String userName, long scores) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.editUsersScores(userName, scores);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateScoresForUsers() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            UserDao userDao = daoHelper.createUserDao();
            userDao.refreshScores();
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

}
