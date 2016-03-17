package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.*;
import com.tsystems.javaschool.webshop.dao.entities.Address;

import java.util.List;

/**
 * Service provides checkout logic.
 */
public interface CheckoutService {

    /**
     * Gets payment types.
     *
     * @return the payment types
     */
    List<Payment> getPaymentTypes();

    /**
     * Gets shipping types.
     *
     * @return the shipping types
     */
    List<Shipping> getShippingTypes();

    /**
     * Create order.
     *
     * @param user       the user
     * @param address    the address
     * @param cart       the cart
     * @param paymentId  the payment id
     * @param shippingId the shipping id
     * @param comment    the comment
     */
    void createOrder(User user,
                     Address address,
                     Cart cart,
                     Integer paymentId,
                     Integer shippingId,
                     String comment);
}
