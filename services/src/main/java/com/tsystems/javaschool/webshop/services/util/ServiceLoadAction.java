package com.tsystems.javaschool.webshop.services.util;

import javax.persistence.EntityManager;

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
