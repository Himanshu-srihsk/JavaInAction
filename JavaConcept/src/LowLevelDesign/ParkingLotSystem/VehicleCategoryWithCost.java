package LowLevelDesign.ParkingLotSystem;

public enum VehicleCategoryWithCost {
    TWOWHEELER(10),
    FOURWHEELER(20);
    private int fareprice;
    VehicleCategoryWithCost(int price){
        this.fareprice=price;
    }
    public int getFarePrice(){
        return this.fareprice;
    }
}
