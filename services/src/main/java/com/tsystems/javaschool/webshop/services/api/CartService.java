package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.services.exceptions.ExistsInCartException;
import com.tsystems.javaschool.webshop.services.exceptions.OutOfStockException;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 * The interface Cart service.
 */
public interface CartService extends GenericService<Cart> {
    /**
     * Method adds product with it's quantity to cart.
     * <p/>
     * Cart object must be already encapsulated in CartProduct
     *
     * @param cartProduct the cart product
     * @return updated cart
     * @throws ServiceException the service exception
     */
    Cart addToCart(CartProduct cartProduct)
            throws ServiceException, OutOfStockException, ExistsInCartException;

    /**
     * Method edits product quantity in cart.
     *
     * @param cartProduct the cart product
     * @return updated cart
     */
    Cart editCartProduct(CartProduct cartProduct) throws OutOfStockException;

    /**
     * Method removes product from cart.
     *
     * @param cartProduct the cart product
     * @return updated cart
     */
    Cart removeFromCart(CartProduct cartProduct);
}
