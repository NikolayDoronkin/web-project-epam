package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.TargetDao;
import com.epam.web.entity.TargetDto;

import java.sql.SQLException;
import java.util.Optional;

public class TargetService {

    private final DaoHelperFactory daoHelperFactory;

    public TargetService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<TargetDto> createTarget(long userID, long cost, String date, String payment) throws SQLException, ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            TargetDao targetDao = daoHelper.createTargetDao();
            return targetDao.updateTargets(userID, cost, date, payment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<TargetDto> findTargetByUserID(long userID) throws ServiceException, SQLException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            TargetDao targetDao = daoHelper.createTargetDao();
            return targetDao.findTargetByUserID(userID);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<TargetDto> findAllTargets() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            TargetDao targetDao = daoHelper.createTargetDao();
            return targetDao.findTargets();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<TargetDto> editStatusOfTarget(long targetID, String statusType) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            TargetDao targetDao = daoHelper.createTargetDao();
            return targetDao.editStatusByID(targetID, statusType);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void refresh() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            TargetDao targetDao = daoHelper.createTargetDao();
            targetDao.deletePaidOrDeclinedTargets();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }


}
