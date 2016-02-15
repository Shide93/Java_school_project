package com.tsystems.javaschool.webshop.services;

import com.tsystems.javaschool.webshop.dao.DAOInterfaces.UsersDAO;
import com.tsystems.javaschool.webshop.dao.DAOImpl.UsersDAOImpl;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

    private UsersDAO usersDAO;

    public AccountServiceImpl() {
        this.usersDAO = new UsersDAOImpl();
    }


    public void signUpUser(String email, String password) throws ServiceException {

        //TODO: validate email, hash password

        if (usersDAO.getUserByEmail(email) != null) {
            throw new ServiceException("email registered");
        }
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setUserType("user");
        try {
            usersDAO.addUser(newUser);
        } catch (DaoException e) {
            LOGGER.error("Adding user failed", e);
            throw new ServiceException("Adding user failed", e);
        }
    }

    @Override
    public void signInUser(String email, String password) {


    }

    @Override
    public void logout() {

    }

    @Override
    public void getUSerInfo() {

    }
}