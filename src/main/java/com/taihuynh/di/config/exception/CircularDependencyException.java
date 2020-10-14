package com.taihuynh.di.config.exception;

/**
 * The type Circular dependency definition exception.
 */
public class CircularDependencyException extends RuntimeException {

    private String objectName;

    /**
     * Instantiates a new Circular dependency exception.
     *
     * @param objectName the object name
     */
    public CircularDependencyException(final String objectName) {
       this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }
}
