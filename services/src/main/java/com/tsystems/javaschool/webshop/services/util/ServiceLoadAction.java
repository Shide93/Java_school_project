package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import javax.persistence.EntityManager;
import javax.security.auth.login.AccountException;

/**
 * Loads data from DAO.
 *
 * @param <T> type of return object
 */
@FunctionalInterface
public interface ServiceLoadAction<T> {
    /**
     * Perform action t.
     *
     * @param manager used to work with DAO
     * @return object of specified type
     */
    T performAction(EntityManager manager);
}
