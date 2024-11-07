package Reflection_and_Annotations.SimpleDependencyInjection;

public class Car {
    @Inject
    private Engine engine;
    @Inject
    private SteeringWheel steeringWheel;

    public Car() {}
    public void run() {
        engine.start();
        steeringWheel.rotate(30);
        System.out.println("Car is driving.");
    }
}
