package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.Category;

import java.util.List;

/**
 * DAO class to interact with Category Entity.
 */
public interface CategoryDAO extends GenericDAO<Category> {

    /**
     * Returns collection of category ids and names.
     *
     * @return List of categories with only id and names fields
     */
    List<Category> getAllIdNames();
}
