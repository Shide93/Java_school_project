package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.PaymentDAO;
import com.tsystems.javaschool.webshop.dao.entities.PaymentEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The type Payment dao.
 */
public class PaymentDAOImpl extends AbstractGenericDAO<PaymentEntity>
        implements PaymentDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartDAOImpl.class);

    /**
     * Instantiates a new Payment dao.
     */
    public PaymentDAOImpl() {
        super(PaymentEntity.class, LOGGER);
    }
}
