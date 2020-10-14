package com.taihuynh.di.example;

import com.taihuynh.di.application.NatureApplication;
import com.taihuynh.di.application.NatureApplicationBuilder;
import com.taihuynh.di.config.ObjectScope;

import java.util.Optional;

public class Example {
    public static void main(String[] args) {
        final NatureApplication app = new NatureApplicationBuilder().declare("tyre", Tyre.class, ObjectScope.PROTOTYPE)
                .declare("car1", Car.class, "tyre", "tyre", "tyre", "tyre")
                .declare("andy", Person.class, "car1")
                .declare("motherOfAndy", Person.class, "car1").build();

        app.start();

        Optional<Object> andy =  app.getObjectById("andy");
        Optional<Object> motherOfAndy = app.getObjectById("motherOfAndy");

        final Car andyCar = ((Person)andy.get()).getCar();
        final Car andyMotherCar = ((Person)motherOfAndy.get()).getCar();

        System.out.println("Andy car: " + andyCar);
        System.out.println("Andy's mother car: " + andyMotherCar);
        System.out.println(andyCar.getLeftFrontTyre());
        System.out.println(andyCar.getRightFrontTyre());
        System.out.println(andyCar.getLeftRearTyre());
        System.out.println(andyCar.getRightFrontTyre());
    }
}
