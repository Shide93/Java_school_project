package com.tsystems.javaschool.webshop.services;

import com.tsystems.javaschool.webshop.dao.User;
import com.tsystems.javaschool.webshop.dao.UsersDAO;
import com.tsystems.javaschool.webshop.services.AccountService;

/**
 * Created by Shide on 08.02.2016.
 */
public class AccountServiceImpl implements AccountService {

    public void createUser(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        UsersDAO usersDAO = new UsersDAO();
        usersDAO.addUser();
    }
}