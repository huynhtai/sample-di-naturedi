package com.taihuynh.di.application;

import com.taihuynh.di.config.ObjectFactory;
import com.taihuynh.di.config.ObjectInfo;

import java.util.List;
import java.util.Optional;

/**
 * The type Nature application.
 */
public class NatureApplication {

    private ObjectFactory objectFactory;
    private List<ObjectInfo> objectInfos;

    /**
     * Instantiates a new Nature application.
     *
     * @param objectInfos All object info to be defined in application.
     */
    public NatureApplication(final List<ObjectInfo> objectInfos) {
        this.objectInfos = objectInfos;
        start();
    }

    /**
     * Start.
     */
    public void start() {
        this.objectFactory = new ObjectFactory();
        this.objectFactory.initObjects(this.objectInfos);
        this.objectFactory.instantiateSingletonObjects();
    }

    public Optional<Object> getObjectById(final String id) {
        return this.objectFactory.getObject(id);
    }
}
