package com.taihuynh.di.config;

import com.taihuynh.di.config.exception.CircularDependencyException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * The SingletonObjectRegistry in which all singleton objects are created and managed.
 */
public class SingletonObjectRegistry {

    /** This contains all singleton which is not fully created. */
    private Set<String> singletonInCreation = new LinkedHashSet<>();

    /** This contains all created singleton */
    private Map<String, Object> instatiatedSingletonObject = new ConcurrentHashMap<String, Object>();

    /**
     * Gets an instantiated singleton.
     *
     * @param <T>               the type parameter
     * @param objectName       the object name
     * @param singletonSupplier the singleton supplier
     * @return the singleton objects
     */
    public <T> Optional<T> getSingletonObject(final String objectName, final Supplier<Optional<T>> singletonSupplier) {
        Optional<T> obj = Optional.ofNullable((T)this.instatiatedSingletonObject.get(objectName));
        if (obj.isPresent()) {
            return obj;
        }


        if (this.getSingletonInCreation().contains(objectName)) {
            throw new CircularDependencyException(objectName);
        }

        this.markObjectAsBeingCreated(objectName);
        obj = singletonSupplier.get();
        this.markObjectAsCreated(objectName, obj);

        return obj;
    }


    private void markObjectAsBeingCreated(final String objectName) {
        this.singletonInCreation.add(objectName);
    }

    private <T> void markObjectAsCreated(final String objName, final Optional<T> createdObj) {
        T objInstance = createdObj.orElseThrow(() -> new IllegalArgumentException("Object must be not null"));

        this.singletonInCreation.remove(objName);
        this.instatiatedSingletonObject.put(objName, objInstance);
    }

    protected Set<String> getSingletonInCreation() {
        return singletonInCreation;
    }

    protected Map<String, Object> getInstatiatedSingletonObject() {
        return instatiatedSingletonObject;
    }
}
