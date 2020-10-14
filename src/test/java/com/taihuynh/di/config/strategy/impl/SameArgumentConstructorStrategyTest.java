package com.taihuynh.di.config.strategy.impl;


import com.taihuynh.di.config.exception.AmbiguousConstructorException;
import com.taihuynh.di.config.exception.ObjectDefinitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class SameArgumentConstructorStrategyTest {


    /**
     * This test case ensure that the strategy is only return constructors which have same number of given arguments.
     */
    public void shouldGetConstructorHavingSameNumberOfGivenArguments() {

    }

    /**
     * This test case ensure that the strategy will throws exception
     * if there's no constructor which have same number of given arguments.
     */
// @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNoConstructorFound() {

    }

    /**
     * This test case ensure that the strategy will throws exception
     * if there's more than one constructor which have same number of given arguments.
     */
    // @Test(expected = AmbiguousConstructorException.class)
    public void shouldThrowExceptionWhenMoreThanOneConstructorFound() {

    }

    /**
     * This test case ensure that the strategy will throws exception
     * if problem occurs while creating an instance.
     */
    // @Test(expected = ObjectDefinitionException.class)
    public void shouldThrowExceptionWhenCreatingAnObject() {

    }
}
