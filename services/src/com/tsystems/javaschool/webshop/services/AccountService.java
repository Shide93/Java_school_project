package com.tsystems.javaschool.webshop.services;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 * Used to manage user accounts.
 */
public interface AccountService {

    /**
     *  Signs up user in shop.
     *
     * @param name user name
     * @param lastName user last name
     * @param email user email
     * @param password user password
     * @return user id
     * @throws ServiceException
     */
    UserEntity signUpUser(String name, String lastName, String email, String password) throws ServiceException;

    /**
     * Sign in user to shop.
     *
     * @param email user email
     * @param password user password
     * @return user object
     * @throws ServiceException
     */
    UserEntity signInUser(String email, String password) throws ServiceException;

    /**
     * Checks is user exists in base.
     * @param userID user id
     * @return user object
     */
    UserEntity getUser(int userID);

    /**
     * Saves user profile.
     * @param user
     * @return
     */
    boolean saveUser(UserEntity user);
}
