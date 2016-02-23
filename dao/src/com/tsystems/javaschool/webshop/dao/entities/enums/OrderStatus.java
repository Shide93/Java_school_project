package com.tsystems.javaschool.webshop.dao.entities.enums;

/**
 * Created by Shide on 23.02.2016.
 */
public enum OrderStatus {

    /**
     * new order, need to be paid
     */
    NEW,

    /**
     * paid order
     */
    PAID,

    /**
     * order is shipped to customer
     */
    SHIPPED,

    /**
     * order is delivered to customer
     */
    COMPLETED,

    /**
     * order is cancelled
     */
    CANCELLED,

}
