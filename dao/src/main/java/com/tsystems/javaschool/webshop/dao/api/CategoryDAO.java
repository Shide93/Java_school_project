package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;

import java.util.List;

/**
 * DAO class to interact with Category Entity.
 */
public interface CategoryDAO extends GenericDAO<CategoryEntity> {

    /**
     * Returns collection of category ids and names.
     *
     * @return List of categories with only id and names fields
     */
    List<CategoryEntity> getAllIdNames();
}
