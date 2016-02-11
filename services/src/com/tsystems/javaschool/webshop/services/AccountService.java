package com.tsystems.javaschool.webshop.services;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/** Used to manage user accounts.
 */
public interface AccountService {

    void createUser(String email, String password) throws ServiceException;

}