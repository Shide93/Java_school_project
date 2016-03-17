package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Cart service.
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartServiceImpl.class);
    /**
     * The Cart dao.
     */
    @Autowired
    private CartDAO cartDAO;
    /**
     * The Product dao.
     */
    @Autowired
    private ProductDAO productDAO;

    @Override
    public final void add(final Cart cart) {
        cartDAO.create(cart);
    }

    @Override
    public final void update(final Cart cart) {
        cartDAO.update(cart);
    }

    @Override
    public final void delete(final Integer cartId) {
        cartDAO.delete(cartId);
    }

    @Override
    public final Cart get(final int cartId) {
        return cartDAO.getById(cartId);
    }

    @Override
    public final List<Cart> getAll() {
        return cartDAO.getAll();
    }

    @Override
    public final Cart addToCart(final Integer productId,
                                final Integer quantity,
                                final Integer cartId) {
        Cart cart = cartDAO.getById(cartId);
        Product product = productDAO.getById(productId);

        CartProduct item = new CartProduct();

        item.setQuantity(quantity);
        item.setProduct(product);
        item.setCart(cart);
        item.setProductId(productId);
        item.setCartId(cart.getId());
        if (cart.getItems().contains(item)) {
            throw new ServiceException("Product already in cart");
        }
        cart.getItems().add(item);
        updateSummaryCount(cart);
        return cart;

    }

    @Override
    public final Cart editCartProduct(final Integer productId,
                                      final Integer quantity,
                                      final Integer cartId) {

        Cart cart = cartDAO.getById(cartId);

        for (CartProduct cartProduct : cart.getItems()) {
            if (productId.equals(cartProduct.getProductId())) {
                cartProduct.setQuantity(quantity);
                break;
            }
        }
        updateSummaryCount(cart);
        return cart;
    }

    @Override
    public final Cart removeFromCart(final Integer productId,
                                     final Integer cartId) {

        cartDAO.removeFromCart(productId, cartId);
        Cart cart = cartDAO.getById(cartId);
        updateSummaryCount(cart);
        return cart;
    }

    /**
     * Update summary and count of cart.
     *
     * @param cart the cart
     */
    private void updateSummaryCount(final Cart cart) {

        int count = 0;
        int summary = 0;
        for (CartProduct item : cart.getItems()) {
            count += item.getQuantity();
            summary += item.getQuantity() * item.getProduct().getPrice();
        }
        cart.setCount(count);
        cart.setSummary(summary);
    }
}

