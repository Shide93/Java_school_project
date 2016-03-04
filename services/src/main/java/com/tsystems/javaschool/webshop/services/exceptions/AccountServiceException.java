package com.tsystems.javaschool.webshop.services.exceptions;

/**
 * Occurs when user's account action cannot be performed.
 * It is needed to forward to request form with exception message.
 */
public class AccountServiceException extends RuntimeException {
    /**
     * Instantiates a new Account service exception.
     */
    public AccountServiceException() {
    }

    /**
     * Instantiates a new Account service exception.
     *
     * @param message the message
     */
    public AccountServiceException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Account service exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AccountServiceException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Account service exception.
     *
     * @param cause the cause
     */
    public AccountServiceException(final Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Account service exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public AccountServiceException(final String message,
                                   final Throwable cause,
                                   final boolean enableSuppression,
                                   final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
