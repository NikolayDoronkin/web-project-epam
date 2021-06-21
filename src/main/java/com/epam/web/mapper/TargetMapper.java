package com.epam.web.mapper;

import com.epam.web.entity.Target;
import com.epam.web.entity.enums.PaymentType;
import com.epam.web.entity.enums.StatusType;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class TargetMapper implements Mapper<Target>{
    @Override
    public Target map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long userID = resultSet.getLong("user_id");
        long cost = resultSet.getLong("cost");
        Time time = resultSet.getTime("time");

        String status = resultSet.getString("status");
        StatusType statusType = StatusType.valueOf(status);

        String payment = resultSet.getString("payment");
        PaymentType paymentType = PaymentType.valueOf(payment);

        return new Target(id,userID, cost, time, statusType, paymentType);
    }
}
