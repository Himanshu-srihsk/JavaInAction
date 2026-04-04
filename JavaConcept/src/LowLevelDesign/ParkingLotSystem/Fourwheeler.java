package LowLevelDesign.ParkingLotSystem;

public class Fourwheeler extends Vehicle{
    public Fourwheeler(VehicleOwner vehicleOwner, String vehicleNumber){
        super(vehicleOwner,vehicleNumber,VehicleCategoryWithCost.TWOWHEELER);
    }
}
