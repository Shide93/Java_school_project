package com.tsystems.javaschool.webshop.services;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 * Used to manage user accounts.
 */
public interface AccountService {

    /**
     * Signs up user in shop.
     * @param email user email
     * @param password user password
     * @throws ServiceException
     */
    void signUpUser(String email, String password) throws ServiceException;

    /**
     * Sign in user to shop.
     * @param email user email
     * @param password user password
     */
    void signInUser(String email, String password);

    void logout();

    void getUSerInfo();
}
