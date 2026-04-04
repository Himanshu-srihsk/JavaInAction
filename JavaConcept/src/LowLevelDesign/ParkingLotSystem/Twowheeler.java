package LowLevelDesign.ParkingLotSystem;

public class Twowheeler extends Vehicle{
    public Twowheeler(VehicleOwner vehicleOwner, String vehicleNumber){
        super(vehicleOwner,vehicleNumber,VehicleCategoryWithCost.TWOWHEELER);
    }
}
