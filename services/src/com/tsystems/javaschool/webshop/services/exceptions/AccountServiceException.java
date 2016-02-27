package com.tsystems.javaschool.webshop.services.exceptions;

/**
 * Occurs when user's account action cannot be performed.
 * It is needed to forward to request form with exception message.
 */
public class AccountServiceException extends RuntimeException {
    public AccountServiceException() {
    }

    public AccountServiceException(final String message) {
        super(message);
    }

    public AccountServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AccountServiceException(final Throwable cause) {
        super(cause);
    }

    public AccountServiceException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
