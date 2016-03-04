package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.AddressEntity;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.entities.PaymentEntity;
import com.tsystems.javaschool.webshop.dao.entities.ShippingEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;

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
    List<PaymentEntity> getPaymentTypes();

    /**
     * Gets shipping types.
     *
     * @return the shipping types
     */
    List<ShippingEntity> getShippingTypes();

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
    void createOrder(UserEntity user,
                     AddressEntity address,
                     CartEntity cart,
                     Integer paymentId,
                     Integer shippingId,
                     String comment);
}
