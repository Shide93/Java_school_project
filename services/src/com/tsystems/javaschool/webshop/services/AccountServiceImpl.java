package com.tsystems.javaschool.webshop.services;

import com.tsystems.javaschool.webshop.dao.User;
import com.tsystems.javaschool.webshop.dao.UsersDAO;
import com.tsystems.javaschool.webshop.dao.UsersDAOImpl;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 * Created by Shide on 08.02.2016.
 */
public class AccountServiceImpl implements AccountService {
    private UsersDAO usersDAO;

    public AccountServiceImpl() {
        this.usersDAO = new UsersDAOImpl();
    }

    public void createUser(String email, String password) throws ServiceException {

        //TODO: validate email, hash password
        System.out.println(usersDAO.isRegistered(email));
        if (usersDAO.isRegistered(email)) {
            throw new ServiceException("email registered");
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setUserType("user");
        try {
            usersDAO.addUser(newUser);
        } catch (DaoException e) {
            //log
            throw new ServiceException("add user failed", e);
        }
    }
}