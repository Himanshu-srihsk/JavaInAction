package LowLevelDesign.ParkingLotSystem;

public class ParkingSpot{
    private int spotNumber;
    private Vehicle vehicle;
    private VehicleCategoryWithCost vehicleCategoryWithCost;
    private boolean isSpaceAvailableForParking;
    ParkingSpot(int spotNumber, VehicleCategoryWithCost vehicleCategoryWithCost, boolean isSpaceAvailableForParking){
        this.spotNumber = spotNumber;
        this.vehicleCategoryWithCost = vehicleCategoryWithCost;
        this.isSpaceAvailableForParking = isSpaceAvailableForParking;
    }
    public int getSpotNumber() { return spotNumber; }

    public VehicleCategoryWithCost getVehicleCategory() {
        return vehicleCategoryWithCost;
    }

    public boolean isSpaceAvailableForParking() {
        return isSpaceAvailableForParking;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setSpaceAvailableForParking(boolean val) {
        this.isSpaceAvailableForParking = val;
    }

    public void freeTheParkingSpot() {
        this.vehicle = null;
        this.isSpaceAvailableForParking = true;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber=" + spotNumber +
                ", category=" + vehicleCategoryWithCost +
                ", available=" + isSpaceAvailableForParking +
                '}';
    }

}
