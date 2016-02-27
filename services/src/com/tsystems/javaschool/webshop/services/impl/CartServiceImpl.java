package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.entities.CartProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.impl.CartDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.ProductDAOImpl;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Cart service.
 */
public class CartServiceImpl implements CartService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartServiceImpl.class);
    /**
     * The Cart dao.
     */
    private CartDAO cartDAO;
    /**
     * The Product dao.
     */
    private ProductDAO productDAO;
    /**
     * The Service helper.
     */
    private ServiceHelper serviceHelper;

    /**
     * Instantiates a new Cart service.
     */
    public CartServiceImpl() {
        cartDAO = new CartDAOImpl();
        productDAO = new ProductDAOImpl();
        serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    @Override
    public final void add(final CartEntity cart) {
        serviceHelper.executeInTransaction(manager -> {

            cartDAO.create(cart, manager);
        });
    }

    @Override
    public final void update(final CartEntity cart) {
        serviceHelper.executeInTransaction(manager -> {

            cartDAO.update(cart, manager);
        });
    }

    @Override
    public final void delete(final CartEntity cart) {
        serviceHelper.executeInTransaction(manager -> {

            cartDAO.delete(cart, manager);
        });
    }

    @Override
    public final CartEntity get(final int cartId) {
        return serviceHelper.load(manager -> {

            return cartDAO.getById(cartId, manager);
        });
    }

    @Override
    public final List<CartEntity> getAll() {
        return serviceHelper.loadInTransaction(manager -> {

            return cartDAO.getAll(manager);
        });
    }

    @Override
    public final CartEntity addToCart(final Integer productId,
                                      final Integer quantity,
                                      final Integer cartId) {
        return serviceHelper.loadInTransaction(manager -> {
            CartEntity cart = cartDAO.getById(cartId, manager);
            ProductEntity product = productDAO.getById(productId, manager);
            CartProductEntity item = new CartProductEntity();

            item.setQuantity(quantity);
            item.setProduct(product);
            item.setCart(cart);
            item.setProductId(productId);
            item.setCartId(cart.getId());

            cart.getItems().add(item);
            cart.setCount(cart.getCount() + quantity);
            cart.setSummary(cart.getSummary()
                    + (product.getPrice() * quantity));
            cartDAO.update(cart, manager);
            return cart;
        });
    }

    @Override
    public final CartEntity editCartProduct(final Integer productId,
                                            final Integer quantity,
                                            final Integer cartId) {
        return serviceHelper.loadInTransaction(manager -> {
            CartEntity cart = cartDAO.getById(cartId, manager);

            for (CartProductEntity cartProduct : cart.getItems()) {
                if (productId.equals(cartProduct.getProductId())) {
                    cartProduct.setQuantity(quantity);
                    break;
                }
            }
            return cart;
        });
    }

    @Override
    public final CartEntity removeFromCart(final Integer productId,
                                           final Integer cartId) {
        return serviceHelper.loadInTransaction(manager -> {
            cartDAO.removeFromCart(productId, cartId, manager);
            return cartDAO.getById(cartId, manager);
        });
    }
}

