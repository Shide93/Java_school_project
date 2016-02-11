package com.tsystems.javaschool.webshop.dao;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

/**
 * Created by Shide on 11.02.2016.
 */
public interface UsersDAO {

    void addUser(User newUser) throws DaoException;
    boolean isRegistered(String email);

}
