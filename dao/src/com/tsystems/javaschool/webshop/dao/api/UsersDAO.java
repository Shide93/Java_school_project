package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

/**
 * DAO class to interact with User Entity.
 */
public interface UsersDAO {

    /**
     *
     * Adds new user to DB.
     * @param newUser user to add
     * @return user id
     * @throws DaoException throws when adding failed
     */
    UserEntity addUser(UserEntity newUser) throws DaoException;

    /**
     * Search a user by his email.
     * @param email user email to search with
     * @return User object or null if user not found
     */
    UserEntity getUserByEmail(String email);

    /**
     * Search user by his id
     * @param id user id in DB
     * @return User object or null if user not found
     */
    UserEntity getUserById(int id);

    /**
     * Update user with new values.
     * @param user user object with new values
     * @return updated object
     * @throws DaoException if update failed
     */
    UserEntity updateUser(UserEntity user) throws DaoException;
}
