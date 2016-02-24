package com.tsystems.javaschool.webshop.dao.exceptions;

/**
 * Exception of dao layer.
 */
public class DaoException extends Exception {

    /**
     * Instantiates a new Dao exception.
     */
    public DaoException() {
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     */
    public DaoException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DaoException(final String message,
                        final Throwable cause) {
        super(message, cause);
    }
    /**
     * Instantiates a new Dao exception.
     *
     * @param cause the cause
     */
    public DaoException(final Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public DaoException(final String message,
                        final Throwable cause,
                        final boolean enableSuppression,
                        final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
