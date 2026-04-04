package LowLevelDesign.ParkingLotSystem;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class TicketService {
    HashMap<Person, Ticket> ticketsCollection = new HashMap<>();
    public Ticket getTicket(int parkingEntryGateNumber, ParkingSpot parkingSpot, Vehicle vehicle) {
        Ticket ticket = new Ticket(new Random().nextInt(), vehicle, parkingEntryGateNumber, parkingSpot, TicketStatus.BOOKED);
        ticketsCollection.put(vehicle.getOwner(), ticket);
        return ticket;
    }
//    public double getParkingFare(Ticket ticket) {
//        long now = new Date().getTime();
//        int farePrice = ticket.getVehicle().getVehicleCategory().getFarePrice();
//        ticketsCollection.remove(ticket.getVehicle().getOwner());
//        return now*farePrice;
//    }
    public double getParkingFare(Ticket ticket) {
        long now = System.currentTimeMillis();
        long duration = now - ticket.getEntryTime();
        int farePrice = ticket.getVehicle().getVehicleCategory().getFarePrice();
        long minutes = duration / (1000 * 60);
        if (minutes == 0) minutes = 1; // minimum charge
        ticketsCollection.remove(ticket.getVehicle().getOwner());
        return minutes * farePrice;
    }

    public HashMap<Person, Ticket> getTicketsCollection(){
        return ticketsCollection;
    }
}