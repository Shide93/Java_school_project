package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.util.List;

/**
 * Service provides CRUD manipulation logic for services.
 * @param <T> entity type
 */
public interface GenericService<T> {

    /**
     * adds object to DB.
     * @param object object to add
     * @throws ServiceException if add failed
     */
    void add(T object) throws ServiceException;

    /**
     * updates object in DB.
     * @param object object to update
     * @throws ServiceException if update failed
     */
    void update(T object) throws ServiceException;

    /**
     * deletes object from DB.
     * @param object object to delete
     * @throws ServiceException if delete failed
     */
    void delete(T object) throws ServiceException;

    /**
     * gets object by id.
     * @param objectId id of object
     * @return object with specified id
     * @throws ServiceException if get failed
     */
    T get(int objectId) throws ServiceException;

    /**
     * Returns all existing objects.
     * @return all existing objects
     * @throws ServiceException f get failed
     */
    List<T> getAll() throws ServiceException;

}
