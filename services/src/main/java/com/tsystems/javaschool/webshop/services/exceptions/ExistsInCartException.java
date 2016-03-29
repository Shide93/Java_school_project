package com.tsystems.javaschool.webshop.services.exceptions;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Shide on 30.03.2016.
 */
public class ExistsInCartException extends Exception {
    /**
     * The User message.
     */
    @JsonView(ExistsInCartException.class)
    private String userMessage;

    /**
     * Instantiates a new Exists in cart exception.
     */
    public ExistsInCartException() {
    }

    /**
     * Instantiates a new Exists in cart exception.
     *
     * @param message    the message
     * @param usrMessage the user message
     */
    public ExistsInCartException(final String message, final String usrMessage) {
        super(message);
        this.userMessage = usrMessage;
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
     * @param usrMessage the user message
     */
    public final void setUserMessage(final String usrMessage) {
        this.userMessage = usrMessage;
    }
}
