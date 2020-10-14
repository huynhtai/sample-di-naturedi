package com.taihuynh.di.config;


import com.taihuynh.di.config.exception.CircularDependencyException;
import com.taihuynh.di.config.exception.ObjectDefinitionException;
import com.taihuynh.di.config.strategy.ConstructorStrategy;
import com.taihuynh.di.config.strategy.impl.SameNumberArgumentsConstructorStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * This ObjectFactory contains all instance of
 *
 * @author taihuynh
 * @date 2020 /10/13
 */
public class ObjectFactory {

    private Map<String, ObjectInfo> objectInfos = new ConcurrentHashMap();
    private SingletonObjectRegistry singletonObjectRegistry = new SingletonObjectRegistry();
    private ConstructorStrategy constructorStrategy;

    /**
     * Instantiates a new Object factory.
     */
    public ObjectFactory() {
        constructorStrategy = new SameNumberArgumentsConstructorStrategy();
    }


    /**
     * Init object.
     *
     * @param objectInfos the object infos
     */
    public void initObjects(final List<ObjectInfo> objectInfos) {
        if (CollectionUtils.isEmpty(objectInfos)) {
            return;
        }

        for (final ObjectInfo objectInfo : objectInfos) {
            this.registerObjectDefinition(objectInfo);
        }
    }

    /**
     * Intantiate singletons.
     */
    public void instantiateSingletonObjects() {
        for (final ObjectInfo objectInfo : this.objectInfos.values()) {
            if (objectInfo.isSingleton()) {
                this.getObject(objectInfo.getName());
            }
        }
    }


    /**
     * Gets object.
     *
     * @param objName the Object name
     * @return the object
     */
    public Optional<Object> getObject(final String objName) {
        final ObjectInfo objectInfo = this.objectInfos.get(objName);

        if (objectInfo.isPrototype()) {
            return this.createObject(objectInfo);
        } else {
            final Supplier<Optional<Object>> objectSupplier = () -> this.createObject(objectInfo);

            return this.getSingletonObjectRegistry().getSingletonObject(objName, objectSupplier);
        }
    }

    private Optional<Object> createObject(final ObjectInfo objectInfo) {
        try {
            return this.getConstructorStrategy()
                    .instantiate(objectInfo.getClassType(), this::getObject, objectInfo.getDependencies());
        } catch (final CircularDependencyException ex) {
            throw new ObjectDefinitionException(
                    String.format("Circular dependencies [%s <-> %s]", objectInfo.getName(), ex.getObjectName()));
        }
    }


    private void registerObjectDefinition(final ObjectInfo objectInfo) {

        if (StringUtils.isBlank(objectInfo.getName())) {
            throw new ObjectDefinitionException("Object must have id. But I got " + objectInfo.toString());
        }

        if (Objects.isNull(objectInfo.getClassType())) {
            throw new ObjectDefinitionException("Object must not be null class. But I got " + objectInfo.toString());
        }

        if (objectInfo.getClassType().isPrimitive()) {
            throw new ObjectDefinitionException("Object type must NOT a primitive type. But I got " + objectInfo.toString());
        }

        if (objectInfo.getClassType().isInterface()) {
            throw new ObjectDefinitionException("Object type must be a concrete class type. But I got " + objectInfo.toString());
        }

        final Optional<ObjectInfo> existedObjInfo = Optional.ofNullable(this.objectInfos.get(objectInfo.getName()));
        if (existedObjInfo.isPresent()) {
            throw new ObjectDefinitionException(String.format("Object [%s] was defined [%s]", objectInfo.getName(),existedObjInfo.get().toString()));
        }

        objectInfos.put(objectInfo.getName(), objectInfo);
    }


    public SingletonObjectRegistry getSingletonObjectRegistry() {
        return singletonObjectRegistry;
    }

    /**
     * Gets constructor strategy.
     *
     *
     *

     * @return the constructor strategy
     */
    public ConstructorStrategy getConstructorStrategy() {
        return constructorStrategy;
    }

}
