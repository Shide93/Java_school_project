package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.enums.UserRole;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.List;


/**
 * Used to manage user accounts.
 */
public interface AccountService {

    /**
     * Signs up user in shop.
     *
     * @param user new user
     * @return user id
     * @throws AccountServiceException if email already registered
     */
    User signUpUser(User user)
            throws AccountServiceException;

    /**
     * Sign in user to shop.
     *
     * @param email    user email
     * @param password user password
     * @return user object if found
     * @throws AccountServiceException if password wrong or user not found
     */
    User signInUser(String email, String password)
            throws AccountServiceException;

    /**
     * Returns user or null if not exists.
     *
     * @param userID user id
     * @return user object
     */
    User getUser(int userID);

    /**
     * Saves user profile.
     *
     * @param user user with new values
     * @return user that saved to db
     * @throws AccountServiceException the account service exception
     */
    User saveProfile(User user)  throws AccountServiceException;

    /**
     * Save credentials user.
     *
     * @param credentials the credentials
     * @param oldEmail    the old email
     * @return the user
     * @throws AccountServiceException the account service exception
     */
    User saveCredentials(User credentials,
                         String oldEmail)
            throws AccountServiceException;

    /**
     * Gets all users.
     *
     * @return all users
     */
    List<User> getAll();

    /**
     * Sets user rights.
     *
     * @param userId  the user id
     * @param isAdmin is user admin
     */
    void setUserRights(int userId, UserRole isAdmin);

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    User getUserByEmail(String email);

    /**
     * Do auto login.
     *
     * @param username the username
     * @param password the password
     * @param detils   the detils
     * @throws AuthenticationException the authentication exception
     */
    void doAutoLogin(final String username,
                                  final String password,
                                  final WebAuthenticationDetails detils)
            throws AuthenticationException;
}
