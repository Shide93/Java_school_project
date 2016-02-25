package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 * The interface Cart service.
 */
public interface CartService extends GenericService<CartEntity> {
    /**
     * Method adds product with it's quantity to cart.
     *
     * @param productId id of product to add
     * @param quantity  quantity of product in cart
     * @param cartId      user's cart
     * @return updated cart
     * @throws ServiceException if something wrong
     */
    CartEntity addToCart(Integer productId,
                         Integer quantity, Integer cartId)
            throws ServiceException;

    /**
     * Method edits product quantity in cart.
     *
     * @param productId id of product to edit in cart
     * @param quantity  new quantity of product in cart
     * @param cartId      user's cart
     * @return updated cart
     * @throws ServiceException if something wrong
     */
    CartEntity editCartProduct(Integer productId,
                         Integer quantity, Integer cartId)
            throws ServiceException;

    /**
     * Method removes product from cart.
     *
     * @param productId id of product to remove
     * @param cartId      user's cart
     * @return updated cart
     * @throws ServiceException if something wrong
     */
    CartEntity removeFromCart(Integer productId, Integer cartId)
            throws ServiceException;
}
