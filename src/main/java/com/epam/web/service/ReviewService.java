package com.epam.web.service;

import com.epam.web.dao.*;

import java.sql.SQLException;

public class ReviewService {

    private final DaoHelperFactory daoHelperFactory;

    public ReviewService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void makeReview(int targetId, int mark, String comment) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.createDao()){
            ReviewDao reviewDao = daoHelper.createReviewDao();
            reviewDao.sendReview(targetId, mark, comment);
        }catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

}
