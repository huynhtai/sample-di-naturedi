package com.taihuynh.di.config.strategy;

import java.util.Optional;
import java.util.function.Function;

/**
 * The interface Constructor strategy.
 */
public interface ConstructorStrategy {

    /**
     * Create new instance with given
     *
     * @param objClass       the obj class
     * @param objectFactory the object factory
     * @param arguments      the arguments
     * @return the optional
     */
    Optional<Object> instantiate(Class objClass, Function<String, Optional<Object>> objectFactory, final String... arguments);
}
