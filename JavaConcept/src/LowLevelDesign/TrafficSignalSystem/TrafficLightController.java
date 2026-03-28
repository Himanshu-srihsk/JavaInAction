package LowLevelDesign.TrafficSignalSystem;

public class TrafficLightController implements Runnable {
    private final TrafficSignal signal;
    public TrafficLightController(TrafficSignal signal) {
        this.signal = signal;
    }
    @Override
    public void run() {
        try {
            while (true) {

                System.out.println("\n GREEN: NORTH & SOUTH");
                signal.setNorthSouthGreen();
                Thread.sleep(5000);
                System.out.println(" SWITCHING...");
                System.out.println("\n GREEN: EAST & WEST");
                signal.setEastWestGreen();
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
