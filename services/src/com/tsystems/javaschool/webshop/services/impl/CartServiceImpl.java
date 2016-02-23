package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.impl.CartDAOImpl;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;

import java.util.List;

/**
 *
 */
public class CartServiceImpl implements CartService {

    private CartDAO cartDAO;
    private ServiceHelper serviceHelper;

    public CartServiceImpl() {
        cartDAO = new CartDAOImpl();
        serviceHelper = new ServiceHelperImpl();
    }

    @Override
    public void add(CartEntity cart) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {

            cartDAO.create(cart, manager);
        });
    }

    @Override
    public void update(CartEntity cart) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {

            cartDAO.update(cart, manager);
        });
    }

    @Override
    public void delete(CartEntity cart) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {

            cartDAO.delete(cart, manager);
        });
    }

    @Override
    public CartEntity get(int cartId) throws ServiceException {
        return serviceHelper.load(manager -> {

            return cartDAO.getById(cartId, manager);
        });
    }

    @Override
    public List<CartEntity> getAll() throws ServiceException {
        return serviceHelper.loadTransactionally(manager -> {

            return cartDAO.getAll(manager);
        });
    }

    @Override
    public CartEntity getByCookie(String cookie) throws ServiceException{
        return serviceHelper.load(manager -> {

            return cartDAO.getByCookie(cookie, manager);
        });
    }
}

