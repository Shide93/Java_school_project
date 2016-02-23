package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.entities.CartProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.impl.CartDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.ProductDAOImpl;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;

import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class CartServiceImpl implements CartService {

    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private ServiceHelper serviceHelper;

    public CartServiceImpl() {
        cartDAO = new CartDAOImpl();
        productDAO = new ProductDAOImpl();
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

    @Override
    public void addToCart(Integer productId, Integer quantity, CartEntity cart) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {
            ProductEntity product = productDAO.getById(productId, manager);
            CartProductEntity item= new CartProductEntity();

            item.setQuantity(quantity);
            item.setProduct(product);
            item.setCart(cart);
            item.setProductId(productId);
            item.setCartId(cart.getId());

            if (cart.getItems() == null) {
                cart.setItems(new HashSet<>());
            }
            cart.getItems().add(item);
            cart.setCount(cart.getCount() + quantity);
            cart.setSummary(cart.getSummary() + (product.getPrice() * quantity));
            cartDAO.update(cart, manager);
        });
    }
}

