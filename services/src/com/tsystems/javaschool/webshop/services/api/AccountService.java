package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.util.Date;

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
     * @throws ServiceException when user cannot been created
     */
    UserEntity signUpUser(String name, String lastName, String email, String password) throws ServiceException;

    /**
     * Sign in user to shop.
     *
     * @param email user email
     * @param password user password
     * @return user object if found
     * @throws ServiceException if password wrong or user not found
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
     * @param user user with new values
     * @return user that saved to db
     * @throws ServiceException if user save failed
     */
    UserEntity saveProfile(UserEntity user)
            throws ServiceException;

}
