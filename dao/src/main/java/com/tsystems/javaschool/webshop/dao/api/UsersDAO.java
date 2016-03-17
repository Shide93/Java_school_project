package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.User;


/**
 * DAO class to interact with User Entity.
 */
public interface UsersDAO extends GenericDAO<User> {

    /**
     * Search a user by his email.
     *
     * @param email   user email to search with
     * @return User object or null if user not found
     */
    User getUserByEmail(String email);
}
