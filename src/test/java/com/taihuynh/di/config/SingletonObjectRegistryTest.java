package com.taihuynh.di.config;

import com.taihuynh.di.config.exception.CircularDependencyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SingletonObjectRegistryTest {


    // Currently, there are no mocks to be injected but we use it to let JUnit create object in each test cases
    // instead of writing a a method annnotated with @Before
    @InjectMocks
    private SingletonObjectRegistry singletonObjectRegistry;

    @Test(expected = CircularDependencyException.class)
    public void shouldThrowExceptionWhenObjectIsInCreation() {
        Object expectedObj = new Object();
        Supplier<Optional<Object>> circularSupplier =
                () -> singletonObjectRegistry.getSingletonObject("a", () -> Optional.of(expectedObj));


        singletonObjectRegistry.getSingletonObject("a", circularSupplier);
    }

    @Test
    public void shouldCreateInstanceByGivenSupplierWhenObjectIdIsNotExisted() {
        Object expectedObj = new Object();
        Supplier<Optional<Object>> supplier = () -> Optional.of(expectedObj);

        final Optional<Object> singletonObj = singletonObjectRegistry.getSingletonObject("a", supplier);

        assertEquals(expectedObj, singletonObj.get());
    }

    @Test
    public void shouldNotCreateMoreThanOneObjectWhenGettingSingleWithSameId() {
        Object expectedObj = new Object();
        Supplier<Optional<Object>> newInstanceSupplier = Mockito.mock(Supplier.class);
        Mockito.when(newInstanceSupplier.get()).thenReturn(Optional.of(expectedObj));
        singletonObjectRegistry.getSingletonObject("a", newInstanceSupplier);

        final Optional<Object> singletonObj = singletonObjectRegistry.getSingletonObject("a", newInstanceSupplier);

        assertEquals(expectedObj, singletonObj.get());
        // Verify that newInstanceSupplier should be called only once
        verify(newInstanceSupplier).get();
    }

}
