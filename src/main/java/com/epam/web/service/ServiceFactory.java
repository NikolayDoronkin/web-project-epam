package com.epam.web.service;

import com.epam.web.dao.DaoHelperFactory;

public class ServiceFactory {

    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public UserService createUserService(){
        return new UserService(daoHelperFactory);
    }

    public MenuService createMenuService(){
        return new MenuService(daoHelperFactory);
    }

    public CartService createCartService(){
        return new CartService(daoHelperFactory);
    }

    public TargetService createTargetService(){
        return new TargetService(daoHelperFactory);
    }

    public ReviewService createReviewService(){
        return new ReviewService(daoHelperFactory);
    }



}
