package com.taihuynh.di.config;

import com.taihuynh.di.config.exception.ObjectDefinitionException;
import org.apache.commons.lang3.StringUtils;

public class ObjectInfo {
    private final String name;
    private final Class<? extends Object> classType;
    private final ObjectScope scope;
    private final String[] dependencies;

    public ObjectInfo(final String name, final Class<? extends Object> objClass, final ObjectScope scope, final String... dependencies) {
        if (StringUtils.isEmpty(name) || objClass == null) {
            throw new ObjectDefinitionException("Object must have name and type");
        }
        this.name = name;
        this.classType = objClass;
        this.scope = scope;
        this.dependencies = dependencies;
    }

    public Class<? extends Object> getClassType() {
        return classType;
    }

    public ObjectScope getScope() {
        return scope;
    }

    public String[] getDependencies() {
        return dependencies;
    }

    public boolean isPrototype() {
        return ObjectScope.PROTOTYPE.equals(this.getScope());
    }

    public boolean isSingleton() {
        return ObjectScope.SINGLETON.equals(this.getScope());
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Object Info [Name=" + this.name + ", type=" + this.classType + ", args" + this.dependencies;
    }


}
