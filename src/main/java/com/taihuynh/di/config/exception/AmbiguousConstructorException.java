package com.taihuynh.di.config.exception;

/**
 * The type Ambiguous constructor exception.
 */
public class AmbiguousConstructorException extends RuntimeException {

    /**
     * Instantiates a new Ambiguous constructor exception.
     *
     * @param message the message
     */
    public AmbiguousConstructorException(final String message) {
        super(message);
    }
}
