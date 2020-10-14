package com.taihuynh.di.config.strategy.impl;

import com.taihuynh.di.config.exception.AmbiguousConstructorException;
import com.taihuynh.di.config.exception.ObjectDefinitionException;
import com.taihuynh.di.config.strategy.ConstructorStrategy;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * The type Same arguments constructor strategy only creates object which only has one constructor.
 * Only number of arguments is taken into consideration to find a constructors.
 * This strategy does <b>NOT</b> support type-check.
 */
public class SameNumberArgumentsConstructorStrategy implements ConstructorStrategy {

    @Override
    public Optional<Object> instantiate(final Class objClass, final Function<String, Optional<Object>> objectFactory, final String... argumentNames) {
        List<Constructor> constructors = this.findBestCandidate(objClass, argumentNames);

        if (constructors.size() == 0) {
            throw new IllegalArgumentException("No constructor found for " + argumentNames);
        }

        if (constructors.size() > 1) {
            throw new AmbiguousConstructorException("Found more than one constructor");
        }

        List<Object> params = new ArrayList<>();
        Constructor constructor = constructors.get(0);
        Class<?>[] paramTypes = constructor.getParameterTypes();

        for (int index = 0; index < paramTypes.length; index++) {
            final Class<?> paramType = paramTypes[index];

            // Only take reference object into consideration while injecting dependencies
            if (paramType.isPrimitive() || paramType.getSimpleName().equals("String")) {
                params.add(argumentNames[index]);
            } else {
                // This is reference object, it's a another object
                // Check whether the reference object is existed or not
                final String dependentName = argumentNames[index];
                Optional<Object> dependency = objectFactory.apply(dependentName);
                params.add(dependency.get());
            }
        }

        try {
            return Optional.ofNullable(constructor.newInstance(params.toArray()));
        } catch (final Exception exp) {
            throw new ObjectDefinitionException("Cannot create an instance", exp);
        }
    }

    private List<Constructor> findBestCandidate(Class objClass, Object... arguments) {
        final List<Constructor> matchedConstructors = new ArrayList<>();
        final Constructor[] constructors = objClass.getConstructors();
        for (final Constructor constructor : constructors) {
            if (constructor.getParameterCount() == arguments.length) {
                matchedConstructors.add(constructor);
            }
        }

        return matchedConstructors;
    }
}
