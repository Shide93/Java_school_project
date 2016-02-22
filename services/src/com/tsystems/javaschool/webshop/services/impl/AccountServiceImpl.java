package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.impl.UsersDAOImpl;
import com.tsystems.javaschool.webshop.dao.entities.AddressEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 *
 */
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

    private UsersDAO usersDAO;

    public AccountServiceImpl() {
        this.usersDAO = new UsersDAOImpl();
    }


    public UserEntity signUpUser(String name, String lastName, String email, String password) throws ServiceException {

        //TODO: validate email, hash password

        if (usersDAO.getUserByEmail(email) != null) {
            throw new ServiceException("email registered");
        }
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setIsAdmin(false);
        try {
            return usersDAO.addUser(newUser);
        } catch (DaoException e) {
            LOGGER.error("Adding user failed", e);
            throw new ServiceException("Adding user failed", e);
        }

    }


    @Override
    public UserEntity signInUser(String email, String password) throws ServiceException{

        UserEntity user = usersDAO.getUserByEmail(email);

        if (user == null) {
            throw new ServiceException("User with this email not found");
        }
        //TODO: hash password

        if (!user.getPassword().equals(password)) {
            throw new ServiceException("Wrong password");
        }
        return user;

    }

    @Override
    public UserEntity getUser(int userID) {
        return usersDAO.getUserById(userID);
    }

    @Override
    public UserEntity saveProfile(UserEntity user)
            throws ServiceException {

        try {
            usersDAO.updateUser(user);
            return null;
        } catch (DaoException e) {
            LOGGER.error("save profile failed", e);
            throw new ServiceException(e);
        }
    }


}