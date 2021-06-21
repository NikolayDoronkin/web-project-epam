package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Target;
import com.epam.web.entity.TargetDto;
import com.epam.web.mapper.Mapper;
import com.epam.web.mapper.TargetMapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TargetDao extends AbstractDao<Target>{

    private final static String TABLE_NAME = "target";
    private static final String CREATE = "INSERT INTO target (user_id, cost, time, payment) VALUES (?, ?, TIME(?), ?)";
    private static final String FIND_TARGET_BY_USER_ID = "SELECT * FROM target WHERE user_id = ?";
    private static final String EDIT_STATUS_BY_ID = "UPDATE target SET status = ? WHERE id = ?";
    private static final String DELETE_PAID_OR_DECLINED = "DELETE FROM target WHERE status = ? OR status = ?";
    private static final String FIND_NOT_PAID = "SELECT * FROM target WHERE status != 'PAID'";


    public TargetDao(ProxyConnection connection) {
        super(connection, new TargetMapper(), TABLE_NAME);
    }

    public Optional<TargetDto> updateTargets(long userID, long cost, String date, String payment) throws DaoException {
        executeUpdate(CREATE, userID, cost, date, payment);
        List<Target> targets = executeQuery(FIND_TARGET_BY_USER_ID, userID);
        return Optional.of(new TargetDto(targets));
    }

    public Optional<TargetDto> findTargets() throws SQLException, DaoException {
        List<Target> targets = executeQuery(FIND_NOT_PAID);
        return Optional.of(new TargetDto(targets));

    }

    public Optional<TargetDto> findTargetByUserID(long userID) throws DaoException {
        List<Target> targets =  executeQuery(FIND_TARGET_BY_USER_ID, userID);
        if(!targets.isEmpty()){
            return Optional.of(new TargetDto(targets));
        }
        return Optional.empty();
    }

    public Optional<TargetDto> editStatusByID(long targetID, String statusType) throws DaoException, SQLException {
        executeUpdate(EDIT_STATUS_BY_ID, statusType, targetID);
        return findTargets();
    }

    public void deletePaidOrDeclinedTargets() throws DaoException {
        executeUpdate(DELETE_PAID_OR_DECLINED, "PAID", "DECLINED");
    }

    @Override
    protected void create(Target entity) throws DaoException {

    }

    @Override
    protected void update(Target entity) throws DaoException {

    }
}
