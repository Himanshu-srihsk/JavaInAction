package LowLevelDesign.TrafficSignalSystem;

public class Car implements Runnable {

    private final int id;
    private final Direction direction;
    private final TrafficSignal signal;

    public Car(int id, Direction direction, TrafficSignal signal) {
        this.id = id;
        this.direction = direction;
        this.signal = signal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Car " + id + " arrived from " + direction);
                signal.waitForGreen(direction);
                System.out.println("Car " + id + " passing from " + direction);
                Thread.sleep(500);
                System.out.println("Car " + id + " crossed from " + direction);
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
