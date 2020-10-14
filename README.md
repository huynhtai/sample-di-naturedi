# New Dependency Inject - NatureDI</br>

## NatureDI Architecture
### Components

This DI architecture contains components below:
- NatureApplication
- ObjectFactory
- SingletonObjectRegistry
- ConstructorStrategy
- SameArgumentsConstructorStrategy
### How components work together

 - Each Application will have a `NatureApplication` </br>
 - In `NatureApplication` has a `ObjectFactory` which produces object
 - `ObjectFactory` use `SingletonObjectRegistry` to create object if the object is defined as singleton.
 If object is prototype, the `ObjectFactory` will create new instance by using `ConstructorStrategy`.
 - The `SingletonObjectRegistry` is place from which singleton object is managed.
 If the singleton object is created before, then we will get it from a registry instead of creating new one.
 - The `ConstructorStrategy` will find constructor to create object.Currently, we use `SameNumberArgumentsConstructorStrategy` to get constructor.
 - The `SameNumberArgumentsConstructorStrategy` is an implementation of `ConstructorStrategy`. It only get constructors having same number of given arguments instead of checking by type.
 This is **not a good strategy**. Because time is tight and we don't get a good solution right now, so we need others to support us a better strategy
 
## Limitation
- Currently, we only support dependency by object ID **NOT** by type.
- We create all objects with recursion. *In worst case, each object is dependency of another (E.g: A -> B -> C -> D....), an StackOverflowException will be throw.*
The proposal to solve this problem, you can take a look at [here](#user-content-proposal-of-next-version)
- There's a constructor injection. The constructor must have all dependencies as method paramters.
- We don't support overloading constructors
- User must define all object as well as its dependency to `NatureApplication` rather than annotating object with annotation. We need support from community ;).


# How to use it

To demonstrate on how to use `NatureDI`, you can use `NatureApplicationBuilder` to define all objects and start by using `start()` method.

E.g: 

A car has 4 tyres, each person use their car.More than two person can share same car.</br>
We have two object here: 
```java
public class com.taihuynh.di.example.Tyre {
    
}

public class com.taihuynh.di.example.Car {
    private com.taihuynh.di.example.Tyre rightFrontTyre;
    private com.taihuynh.di.example.Tyre leftFrontType;
    private com.taihuynh.di.example.Tyre rightRearTyre;
    private com.taihuynh.di.example.Tyre leftRearTyre;
    
    public com.taihuynh.di.example.Car(final com.taihuynh.di.example.Tyre rightFrontTyre, final com.taihuynh.di.example.Tyre leftFrontType,
               final com.taihuynh.di.example.Tyre rightRearTyre, final com.taihuynh.di.example.Tyre leftRearTyre) {
                    // do something here
    }
}

public class Person {
    private Car car;

    public Person(final Car car){
        this.car = car;
    }
}
```

To use NatureDI, you need to declare all possible object is scanned by NatureDI by using `NatureApplicationBuilder`.</br>
In this example, Andy and his mother use same car.
```java
 final NatureApplication app = new NatureApplicationBuilder().declare("tyre", Tyre.class, ObjectScope.PROTOTYPE)
                .declare("car1", Car.class, "tyre", "tyre", "tyre", "tyre")
                .declare("andy", Person.class, "car1")
                .declare("motherOfAndy", Person.class, "car1").build();

        app.start();

        Optional<Object> andy =  app.getObjectById("andy");
        Optional<Object> motherOfAndy = app.getObjectById("motherOfAndy");

        final Car andyCar = ((Person)andy.get()).getCar();
        final Car andyMotherCar = ((Person)motherOfAndy.get()).getCar();
        
```

Now, we print the result of andyCar and andyMotherCar

```java
System.out.println("Andy car: " + andyCar);
System.out.println("Andy's mother car: " + andyMotherCar);
System.out.println(andyCar.getLeftFrontTyre());
System.out.println(andyCar.getRightFrontTyre());
System.out.println(andyCar.getLeftRearTyre());
System.out.println(andyCar.getRightFrontTyre());
```

And we got result

```java
Andy car: com.taihuynh.di.example.Car@1ddc4ec2
Andy's mother car: com.taihuynh.di.example.Car@1ddc4ec2
com.taihuynh.di.example.Tyre@133314b
com.taihuynh.di.example.Tyre@b1bc7ed
com.taihuynh.di.example.Tyre@7cd84586
com.taihuynh.di.example.Tyre@b1bc7ed
```
Because Andy and hi mother share a car, it's same address.
Each car has 4 different tyres and they have different address.
## Proposal of next version
To deal with bad performance of recursive call, we give a rough idea of using `Stack` instead of making recursive calls:
 
  - Step 1: In case a object depends on another *singleton object* and this singleton object hasn't been created yet, we should put the dependent object in `Stack`
  - Step 2: If the dependency is dependent on another one, go to step 1.
  - Step 3: If it doesn't depend on another singleton object, the we created and pop `Stack` to create next




