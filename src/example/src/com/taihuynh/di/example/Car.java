package com.taihuynh.di.example;

public class Car {
    private Tyre leftFrontTyre;
    private Tyre rightFrontTyre;
    private Tyre leftRearTyre;
    private Tyre rightRearTyre;

    public Car(final Tyre leftFrontTyre, final Tyre rightFrontTyre, final Tyre leftRearTyre, final Tyre rightRearTyre) {
        this.leftFrontTyre = leftFrontTyre;
        this.rightFrontTyre = rightFrontTyre;
        this.leftRearTyre = leftRearTyre;
        this.rightRearTyre = rightRearTyre;
    }

    public Tyre getLeftFrontTyre() {
        return leftFrontTyre;
    }

    public Tyre getRightFrontTyre() {
        return rightFrontTyre;
    }

    public Tyre getLeftRearTyre() {
        return leftRearTyre;
    }

    public Tyre getRightRearTyre() {
        return rightRearTyre;
    }
}
