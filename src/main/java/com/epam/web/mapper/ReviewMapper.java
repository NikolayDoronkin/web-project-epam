package com.epam.web.mapper;

import com.epam.web.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements Mapper<Review> {
    @Override
    public Review map(ResultSet resultSet) throws SQLException {
        Long targetID = resultSet.getLong("target_id");
        Long mark = resultSet.getLong("mark");
        String comment = resultSet.getString("comment");

        return new Review(targetID,mark, comment);

    }
}
