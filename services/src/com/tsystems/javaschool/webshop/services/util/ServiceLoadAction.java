package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;

/**
 * Loads data from DAO.
 * @param <T> type of return object
 */
@FunctionalInterface
public interface ServiceLoadAction<T> {
    /**
     *
     * @param manager used to work with DAO
     * @return object of specified type
     * @throws DaoException if something wrong
     */
    T performAction(EntityManager manager) throws DaoException;
}
