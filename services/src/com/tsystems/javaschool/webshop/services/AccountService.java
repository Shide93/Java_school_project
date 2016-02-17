package com.tsystems.javaschool.webshop.services;

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
    int signUpUser(String name, String lastName, String email, String password) throws ServiceException;

    /**
     * Sign in user to shop.
     *
     * @param email user email
     * @param password user password
     * @return user id
     * @throws ServiceException
     */
    int signInUser(String email, String password) throws ServiceException;

    void logout();

    void getUSerProfile();
}
