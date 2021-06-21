package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Review;
import com.epam.web.mapper.ReviewMapper;

import java.util.Optional;

public class ReviewDao extends AbstractDao<Review>{

    private static final String TABLE_NAME_REVIEW = "review";
    private static final String CREATE = "INSERT INTO review (target_id, mark, comment) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE review SET mark = ?, comment = ? WHERE target_id = ?";
    private static final String FIND = "SELECT * FROM review WHERE target_id = ?";

    public ReviewDao(ProxyConnection connection) {
        super(connection, new ReviewMapper(), TABLE_NAME_REVIEW);
    }

    public void sendReview(long targetId, long mark, String comment) throws DaoException {
        Review review = new Review(targetId, mark, comment);
        Optional<Review> optionalReview = executeSingleResultQuery(FIND, targetId);
        if(optionalReview.isPresent()){
            update(review);
        }else{
            create(review);
        }

    }

    @Override
    protected void create(Review entity) throws DaoException {
        executeUpdate(CREATE, entity.getID(), entity.getMark(), entity.getComment());
    }

    @Override
    protected void update(Review entity) throws DaoException {
        executeUpdate(UPDATE, entity.getMark(), entity.getComment(), entity.getID());
    }
}
