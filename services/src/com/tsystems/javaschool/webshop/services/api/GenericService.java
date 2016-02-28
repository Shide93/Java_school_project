package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.util.List;

/**
 * Service provides CRUD manipulation logic for services.
 *
 * @param <T> entity type
 */
public interface GenericService<T> {

    /**
     * adds object to DB.
     *
     * @param object object to add
     */
    void add(T object);

    /**
     * updates object in DB.
     *
     * @param object object to update
     */
    void update(T object);

    /**
     * deletes object from DB.
     *
     * @param objId the obj id to delete
     */
    void delete(Integer objId);

    /**
     * gets object by id.
     *
     * @param objectId id of object
     * @return object with specified id
     */
    T get(int objectId);

    /**
     * Returns all existing objects.
     *
     * @return all existing objects
     */
    List<T> getAll();

}
