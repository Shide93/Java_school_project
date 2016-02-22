package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;

/**
 * Interface is user to perform update, delete and insert actions to DAO layer.
 */
@FunctionalInterface
public interface ServiceExecuteAction {
    /**
     * performs implemented actions.
     * @param mgr used to work with DAO
     * @throws DaoException if something wrong
     */
    void performAction(EntityManager mgr) throws DaoException;
}
