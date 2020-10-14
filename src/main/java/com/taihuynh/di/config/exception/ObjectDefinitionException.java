package com.taihuynh.di.config.exception;

/**
 * The type Object definition exception defines all exception while registering object into application.
 */
public class ObjectDefinitionException extends RuntimeException {
    /**
     * Instantiates a new Object definition exception.
     *
     * @param message the message
     */
    public ObjectDefinitionException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Object definition exception.
     *
     * @param message the message
     * @param error   the error
     */
    public ObjectDefinitionException(final String message, final Throwable error) {
        super(message, error);
    }
}
