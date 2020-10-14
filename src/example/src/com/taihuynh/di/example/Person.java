package com.taihuynh.di.example;

public class Person {
    private Car car;

    public Person(final Car car){
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}
