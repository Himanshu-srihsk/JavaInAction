package LowLevelDesign.ParkingLotSystem;

import java.util.List;

public class ParkingSpace{
    private volatile List<ParkingFloor> parkingFloors;
    private static volatile ParkingSpace INSTANCE = null;

    private ParkingSpace(){}

    // Lazy and safe thread way of getInstance.
    public static ParkingSpace getInstance(){
        synchronized (ParkingSpace.class){
            if(INSTANCE == null){
                INSTANCE = new ParkingSpace();
            }
            return INSTANCE;
        }
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public synchronized void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }
    public String toString() {
        return "ParkingSpace{" +
                "parkingFloors=" + parkingFloors +
                '}';
    }

}
