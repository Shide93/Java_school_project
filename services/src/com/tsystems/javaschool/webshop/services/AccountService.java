package com.tsystems.javaschool.webshop.services;

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
     * @param email user email
     * @param password user password
     * @param name user name
     * @param lastName user lastName
     * @param phone user phone
     * @param birthDate user birthDate
     * @param country user country
     * @param region user region
     * @param city user city
     * @param zip user zip
     * @param addr user addr
     * @param oldUser old user
     * @return user that saved to db
     * @throws ServiceException if user save failed
     */
    UserEntity saveProfile(String email,
                           String password,
                           String name,
                           String lastName,
                           String phone,
                           Date birthDate,
                           String country,
                           String region,
                           String city,
                           Integer zip,
                           String addr,
                           UserEntity oldUser)
            throws ServiceException;

}
