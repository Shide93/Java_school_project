package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ExistsInCartException;
import com.tsystems.javaschool.webshop.services.exceptions.OutOfStockException;
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
    public final Cart addToCart(final CartProduct item)
            throws ServiceException, OutOfStockException, ExistsInCartException {

        Product product = productDAO.getById(item.getProductId());
        Cart cart = item.getCart();
        item.setProduct(product);

        if (product.getStock() < 1) {
            throw new OutOfStockException("Product with id="
                    + item.getProductId() + "is out of stock",
                    "Sorry, the product is out of stock",
                    product.getStock());
        }

        if (cart.getItems().contains(item)) {
            throw new ExistsInCartException("Product with id "
                    + product.getId() + " is already in cart",
                    "Product is already in cart");
        }

        cart.getItems().add(item);
        updateSummaryCount(cart);
        cartDAO.update(cart);
        return cart;

    }

    @Override
    public final Cart editCartProduct(final CartProduct item) throws OutOfStockException {

        Cart cart = item.getCart();
        Product product = productDAO.getById(item.getProductId());
        item.setProduct(product);

        if (product.getStock() < item.getQuantity()) {
            throw new OutOfStockException("Product with id="
                    + item.getProductId() + "is out of stock",
                    "Sorry, there is only " + product.getStock()
                            + " units in stock",
                    product.getStock());
        }

        for (CartProduct cartProduct : cart.getItems()) {
            if (item.getProductId() == cartProduct.getProductId()) {
                cartProduct.setQuantity(item.getQuantity());
                break;
            }
        }
        updateSummaryCount(cart);
        cartDAO.update(cart);
        return cart;
    }

    @Override
    public final Cart removeFromCart(final CartProduct item) {
        Cart cart = item.getCart();
        cart.getItems().remove(item);
        updateSummaryCount(cart);
        cartDAO.update(cart);
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

