package LowLevelDesign.ParkingLotSystem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParkingSpace parkingSpace = ParkingSpace.getInstance();
        SystemService systemService = new SystemService(parkingSpace);
        TicketService ticketService = new TicketService();
        ParkingService parkingService = new ParkingService(parkingSpace, ticketService);

        ParkingSystemAdministrator parkingSystemAdministrator =
                new ParkingSystemAdministrator("Abc", "22", "abc/12", "abc@gmail.com", systemService);

        List<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots.add(new ParkingSpot(1, VehicleCategoryWithCost.TWOWHEELER, true));
        parkingSpots.add(new ParkingSpot(2, VehicleCategoryWithCost.TWOWHEELER, true));
        parkingSpots.add(new ParkingSpot(4, VehicleCategoryWithCost.FOURWHEELER, true));

        List<ParkingEntryGate> parkingEntryGates = new ArrayList<>();
        parkingEntryGates.add(new ParkingEntryGate(1, 1, true, false));

        List<ParkingExitGate> parkingExitGates = new ArrayList<>();
        parkingExitGates.add(new ParkingExitGate(1, 2, false, false));

        ParkingFloor parkingFloor = new ParkingFloor(1, new ArrayList<>(), parkingSpots, parkingEntryGates, parkingExitGates);
        parkingSystemAdministrator.addParkingFloors(parkingFloor);

        ParkingEntryStaff parkingEntryStaff =
                new ParkingEntryStaff("mohan", "24", "mohan/12", "mohan@mail", new BookParkingService(parkingService));

        ParkingExitStaff parkingExitStaff =
                new ParkingExitStaff("ramu", "24", "ramu/12", "ramu@mail", new FreeParkingService(parkingService));

        Vehicle omVehicle =
                new Twowheeler(
                        new VehicleOwner("Om", "29", "om/12", "om@mail.com"),
                        "Dl12 CR");

        Vehicle ramVehicle =
                new Twowheeler(
                        new VehicleOwner("ram", "29", "ram/12", "ram@mail.com"),
                        "Dl14 CR");

        Vehicle himanshuVehicle = new Twowheeler(new VehicleOwner("himanshu", "25", "himanshu/12", "himanshu@mail.com"),
                "KA05316");

        Ticket omTicket = parkingEntryStaff.getBookParkingService().bookParking(1, omVehicle);
        Ticket ramTicket = parkingEntryStaff.getBookParkingService().bookParking(1, ramVehicle);

        System.out.println(ticketService.getTicketsCollection());

        double omCost = parkingExitStaff.getFreeParkingService().freeParking(omTicket);
        System.out.println("Price for om parking is "+omCost);

        System.out.println(ticketService.getTicketsCollection());
    }
}
/*
{Person{name='Om', age='29', address='om/12', emailId='om@mail.com'}=Ticket{ticketId=-1456561949, vehicle=Vehicle{owner=Person{name='Om', age='29', address='om/12', emailId='om@mail.com'}, vehicleNumber='Dl12 CR', vehicleCategory=TWOWHEELER}, entryTime=1775279619548, parkingEntryGateNumber=1, parkingSpot=ParkingSpot{spotNumber=1, category=TWOWHEELER, available=false}, ticketStatus=BOOKED}, Person{name='ram', age='29', address='ram/12', emailId='ram@mail.com'}=Ticket{ticketId=1149394099, vehicle=Vehicle{owner=Person{name='ram', age='29', address='ram/12', emailId='ram@mail.com'}, vehicleNumber='Dl14 CR', vehicleCategory=TWOWHEELER}, entryTime=1775279619548, parkingEntryGateNumber=1, parkingSpot=ParkingSpot{spotNumber=2, category=TWOWHEELER, available=false}, ticketStatus=BOOKED}}
Price for om parking is 10.0
{Person{name='ram', age='29', address='ram/12', emailId='ram@mail.com'}=Ticket{ticketId=1149394099, vehicle=Vehicle{owner=Person{name='ram', age='29', address='ram/12', emailId='ram@mail.com'}, vehicleNumber='Dl14 CR', vehicleCategory=TWOWHEELER}, entryTime=1775279619548, parkingEntryGateNumber=1, parkingSpot=ParkingSpot{spotNumber=2, category=TWOWHEELER, available=false}, ticketStatus=BOOKED}}
 */