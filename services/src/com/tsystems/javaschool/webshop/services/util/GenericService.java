package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 *
 */
public interface GenericService {
    /**
     *
     * @param action
     * @throws ServiceException
     */
    public void executeTransactionally(ServiceExecuteAction action) throws ServiceException;

    /**
     *
     * @param action
     * @param <T>
     * @return
     * @throws ServiceException
     */
    public <T> T load(ServiceLoadAction<T> action) throws ServiceException;

    /**
     *
     * @param action
     * @param <T>
     * @return
     * @throws ServiceException
     */
    public <T> T loadTransactionally(ServiceLoadAction<T> action)
            throws ServiceException;
}
