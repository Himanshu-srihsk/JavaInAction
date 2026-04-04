package LowLevelDesign.ParkingLotSystem;

public class Guard extends Person{
    private int parkingFloorNumber;

    public Guard(String name, String age, String address, String emailId) {
        super(name, age, address, emailId);
    }

    public int getParkingFloorNumber() {
        return parkingFloorNumber;
    }

    public void setParkingFloorNumber(int parkingFloorNumber) {
        this.parkingFloorNumber = parkingFloorNumber;
    }
}
