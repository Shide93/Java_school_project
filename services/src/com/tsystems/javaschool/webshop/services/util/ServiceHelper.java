package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 * Provides methods that encapsulate all transaction managing work.
 *
 * Service logic can be executed by providing ServiceExecuteAction and ServiceLoadAction implementation.
 */
public interface ServiceHelper {
    /**
     * Executes update, delete, insert logic inside a transaction.
     * @param action implementation of functional interface with useful logic
     * @throws ServiceException if something wrong
     */
    public void executeTransactionally(ServiceExecuteAction action) throws ServiceException;

    /**
     * Loads an object from DAO without transaction
     * @param action implementation of functional interface with useful logic
     * @param <T> type of return object
     * @return specified object
     * @throws ServiceException if something wrong
     */
    public <T> T load(ServiceLoadAction<T> action) throws ServiceException;

    /**
     * Loads an object from DAO inside a transaction
     * @param action implementation of functional interface with useful logic
     * @param <T> type of return object
     * @return specified object
     * @throws ServiceException if something wrong
     */
    public <T> T loadTransactionally(ServiceLoadAction<T> action)
            throws ServiceException;
}
