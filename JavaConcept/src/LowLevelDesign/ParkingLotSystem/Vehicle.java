package LowLevelDesign.ParkingLotSystem;

public class Vehicle{
    private VehicleOwner owner;
    private String vehicleNumber;
    private VehicleCategoryWithCost vehicleCategoryWithCost;

    public Vehicle(VehicleOwner owner, String vehicleNumber, VehicleCategoryWithCost category) {
        this.owner = owner;
        this.vehicleNumber = vehicleNumber;
        this.vehicleCategoryWithCost = category;
    }

    public VehicleCategoryWithCost getVehicleCategory() {
        return vehicleCategoryWithCost;
    }

    public VehicleOwner getOwner() {
        return owner;
    }
    @Override
    public String toString() {
        return "Vehicle{" +
                "owner=" + owner +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleCategory=" + vehicleCategoryWithCost +
                '}';
    }


}