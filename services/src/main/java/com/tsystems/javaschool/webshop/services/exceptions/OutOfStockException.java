package com.tsystems.javaschool.webshop.services.exceptions;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Shide on 29.03.2016.
 */
public class OutOfStockException extends Exception {

    /**
     * The Max stock.
     */
    @JsonView(OutOfStockException.class)
    private int maxStock;
    /**
     * The User message.
     */
    @JsonView(OutOfStockException.class)
    private String userMessage;

    /**
     * Instantiates a new Out of stock exception.
     */
    public OutOfStockException() {
    }

    /**
     * Instantiates a new Out of stock exception.
     *
     * @param message the message
     */
    public OutOfStockException(final String message) {
        super(message);
    }


    /**
     * Instantiates a new Out of stock exception with stock param.
     *
     * @param message    the message
     * @param stock      the stock
     * @param usrMessage the user message
     */
    public OutOfStockException(final String message,
                               final String usrMessage,
                               final Integer stock) {

        super(message);
        this.maxStock = stock;
        this.userMessage = usrMessage;
    }

    /**
     * Gets max stock.
     *
     * @return the max stock
     */
    public final int getMaxStock() {
        return maxStock;
    }

    /**
     * Sets max stock.
     *
     * @param stock the stock
     */
    public final  void setMaxStock(final int stock) {
        this.maxStock = maxStock;
    }

    /**
     * Gets user message.
     *
     * @return the user message
     */
    public final String getUserMessage() {
        return userMessage;
    }

    /**
     * Sets user message.
     *
     * @param usrMessage the usr message
     */
    public final void setUserMessage(final String usrMessage) {
        this.userMessage = usrMessage;
    }
}
