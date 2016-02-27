package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;

import javax.persistence.EntityManager;

/**
 * DAO class to interact with User Entity.
 */
public interface UsersDAO extends GenericDAO<UserEntity> {

    /**
     * Search a user by his email.
     *
     * @param email   user email to search with
     * @param manager the manager
     * @return User object or null if user not found
     */
    UserEntity getUserByEmail(String email, EntityManager manager);
}
