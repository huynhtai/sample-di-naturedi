package com.taihuynh.di.application;

import com.taihuynh.di.config.ObjectInfo;
import com.taihuynh.di.config.ObjectScope;
import com.taihuynh.di.config.exception.ObjectDefinitionException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class NatureApplicationBuilder {

    private List<ObjectInfo> objectInfos;

    public NatureApplicationBuilder() {
    }

    public NatureApplicationBuilder declare(final String id, final Class<? extends Object> objClass, final String... dependencies) {
        this.declare(id, objClass, ObjectScope.SINGLETON, dependencies);

        return this;
    }

    public NatureApplicationBuilder declare(final String id, final Class<? extends Object> objClass, final ObjectScope objectScope, final String... dependencies) {
        if (objectInfos == null) {
            this.objectInfos = new ArrayList();
        }

        final ObjectInfo objectInfo = new ObjectInfo(id, objClass, objectScope, dependencies);
        objectInfos.add(objectInfo);

        return this;
    }

    public NatureApplication build() {
        return new NatureApplication(objectInfos);
    }
}
