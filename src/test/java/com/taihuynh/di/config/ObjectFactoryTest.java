package com.taihuynh.di.config;


public class ObjectFactoryTest {

    /**
     * Throw exception when register an Object Defintion having null name.
     */
    // @Test(expected = ObjectDefinitionException.class)
    public void shouldThrowExceptionWhenRegisterNullNameObjectDef() {

    }

    /**
     * Throw exception when register an Object Defintion having null name
     */
    // @Test(expected = ObjectDefinitionException.class)
    public void shouldThrowExceptionWhenRegisterEmptyNameObjectDef() {

    }

    /**
     * Throw exception when register an Object Defintion having empty name
     */
    // @Test(expected = ObjectDefinitionException.class)
    public void shouldThrowExceptionWhenRegisterObjectDefWithNullClassType() {

    }

    /**
     * Throw exception when register an Object Defintion having null class type
     */
    // @Test(expected = ObjectDefinitionException.class)
    public void shouldThrowExceptionWhenRegisterObjectDefWithPrimitiveClassType() {

    }

    /**
     * Throw exception when register an Object Defintion having primitive class type
     */
    // @Test(expected = ObjectDefinitionException.class)
    public void shouldThrowExceptionWhenRegisterObjectDefWithInterfaceClassType() {

    }

    /**
     * Throw exception when register an Object Defintion having interface class type
     */
    // @Test(expected = ObjectDefinitionException.class)
    public void shouldThrowExceptionWhenTwoObjectDefHaveSameName() {

    }

    /**
     * Should only instantiate singleton Object Definition when instantiate singleton objects
     */
    public void shouldOnlyInstantiateSingletonObjDefWhenInstantiateSingletonObject() {

    }

    /**
     * Should only instantiate prototype object definition when it's dependency of another singleton.
     */
    public void shouldOnlyInstantiatePrototypeObjectDefWhenItsDependencyOfSingleton() {

    }


    /**
     * Should throw exception when having circular dependency.
     */
    // @Test(expected = ObjectDefinitionException.class)
    public void shouldThrowExceptionWhenHavingCircularDependency() {

    }
}
