package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.util.List;


/**
 * Used to manage user accounts.
 */
public interface AccountService {

    /**
     * Signs up user in shop.
     *
     * @param name     user name
     * @param lastName user last name
     * @param email    user email
     * @param password user password
     * @return user id
     * @throws AccountServiceException if email already registered
     */
    UserEntity signUpUser(String name,
                          String lastName,
                          String email,
                          String password)
            throws AccountServiceException;

    /**
     * Sign in user to shop.
     *
     * @param email    user email
     * @param password user password
     * @return user object if found
     * @throws AccountServiceException if password wrong or user not found
     */
    UserEntity signInUser(String email, String password)
            throws AccountServiceException;

    /**
     * Returns user or null if not exists.
     *
     * @param userID user id
     * @return user object
     */
    UserEntity getUser(int userID);

    /**
     * Saves user profile.
     *
     * @param user user with new values
     * @return user that saved to db
     */
    UserEntity saveProfile(UserEntity user);

    /**
     * Gets all users.
     *
     * @return all users
     */
    List<UserEntity> getAll();

    /**
     * Sets user rights.
     *
     * @param userId  the user id
     * @param isAdmin is user admin
     */
    void setUserRights(int userId, boolean isAdmin);

}
