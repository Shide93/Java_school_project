package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.PaymentDAO;
import com.tsystems.javaschool.webshop.dao.entities.Payment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * The type Payment dao.
 */
@Repository
public class PaymentDAOImpl extends AbstractGenericDAO<Payment>
        implements PaymentDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartDAOImpl.class);
}
