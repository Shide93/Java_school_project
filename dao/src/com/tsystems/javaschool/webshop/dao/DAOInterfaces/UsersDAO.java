package com.tsystems.javaschool.webshop.dao.DAOInterfaces;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

/**
 * DAO class to interact with User Entity.
 */
public interface UsersDAO {

    /**
     * Adds new user to DB.
     * @param newUser user to add
     * @throws DaoException throws when adding failed
     */
    void addUser(UserEntity newUser) throws DaoException;

    /**
     * Search a user by his email.
     * @param email user email to search with
     * @return User object or null if user not found
     */
    UserEntity getUserByEmail(String email);

}
