package Reflection_and_Annotations.SimpleDependencyInjection;

/*
* Simple Dependency Injection Framework
Define a custom annotation @Inject that can be used on fields.
Write a simple dependency injection utility that:
Instantiates objects of the annotated fields types and assigns them automatically.
For example, if a Car class has an @Inject annotation on an Engine field,
the utility should inject an instance of Engine into Car
* */
public class SimpleDependencyInjection {
    public static void main(String[] args) {
        Car car = DependencyInjectorUtil.getInstance(Car.class);
        car.run();
    }
}
