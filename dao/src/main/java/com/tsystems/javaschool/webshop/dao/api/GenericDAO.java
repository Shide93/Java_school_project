package com.tsystems.javaschool.webshop.dao.api;

import java.util.List;

/**
 * Generic DAO interface. Provides CRUD operations with entities.
 *
 * @param <T> object to work with
 */
public interface GenericDAO<T> {
    /**
     * Creates object in DB.
     *
     * @param newObj  object to save
     */
    void create(T newObj);

    /**
     * Reads an object from DB.
     *
     * @param id      identificator of object in DB
     * @return found object or null if object not found
     */
    T getById(int id);

    /**
     * Updates existing object in DB.
     *
     * @param obj     object to update
     */
    void update(T obj);

    /**
     * Deletes object from DB.
     *
     * @param objId   obj id to delete
     */
    void delete(Integer objId);

    /**
     * Lists all objects of specified type.
     *
     * @return list of objects of specified type
     */
    List<T> getAll();
}
