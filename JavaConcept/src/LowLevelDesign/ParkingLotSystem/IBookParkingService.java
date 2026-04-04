package LowLevelDesign.ParkingLotSystem;

public interface IBookParkingService {
    // return ticket
    Ticket bookParking(int parkingGateNumber, Vehicle vehicle);
}
