package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 * Provides methods that encapsulate all transaction managing work.
 *
 * Service logic can be executed by providing
 * ServiceExecuteAction and ServiceLoadAction implementation.
 */
public interface ServiceHelper {
    /**
     * Executes update, delete, insert logic inside a transaction.
     * @param action implementation of functional interface with useful logic
     */
    void executeInTransaction(ServiceExecuteAction action);

    /**
     * Loads an object from DAO without transaction.
     * @param action implementation of functional interface with useful logic
     * @param <T> type of return object
     * @return specified object
     */
    <T> T load(ServiceLoadAction<T> action);

    /**
     * Loads an object from DAO inside a transaction.
     * @param action implementation of functional interface with useful logic
     * @param <T> type of return object
     * @return specified object
     */
    <T> T loadInTransaction(ServiceLoadAction<T> action);
}
