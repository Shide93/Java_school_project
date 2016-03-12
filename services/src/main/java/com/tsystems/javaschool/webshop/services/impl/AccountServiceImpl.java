package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Account service.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AccountService.class);

    /**
     * The Users dao.
     */
    @Autowired
    private UsersDAO usersDAO;

    @Override
    public final UserEntity signUpUser(final String name,
                                       final String lastName,
                                       final String email,
                                       final String password)
            throws AccountServiceException {

            if (usersDAO.getUserByEmail(email) != null) {
                throw new AccountServiceException("email already registered");
            }
            UserEntity newUser = new UserEntity();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setName(name);
            newUser.setLastName(lastName);
            newUser.setIsAdmin(false);

            usersDAO.create(newUser);
            return newUser;
    }


    @Override
    public final UserEntity signInUser(final String email,
                                       final String password)
            throws AccountServiceException {

        UserEntity user = usersDAO.getUserByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            throw new AccountServiceException(
                    "Wrong password or email");
        }
        return user;
    }
    @Override
    public final UserEntity getUser(final int userID) {
        return usersDAO.getById(userID);
    }

    @Override
    public final UserEntity saveProfile(final UserEntity user) {
        usersDAO.update(user);
        return user;
    }

    @Override
    public final List<UserEntity> getAll() {
        return usersDAO.getAll();
    }

    @Override
    public final void setUserRights(final int userId, final boolean isAdmin) {
        usersDAO.getById(userId)
        .setIsAdmin(isAdmin);
    }
}
