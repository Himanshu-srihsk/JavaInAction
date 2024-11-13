package LowLevelDesign.Elevator;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numElevator = 3;
//        // Initialize floors
//        Floor floor1 = new Floor(1);
//        Floor floor2 = new Floor(2);
//        Floor floor3 = new Floor(3);
//        Floor floor4 = new Floor(4);
//        Floor floor5 = new Floor(5);
//
//        // Create building with floors
//        List<Floor> floors = Arrays.asList(floor1, floor2, floor3, floor4, floor5);
//        Building building = new Building(floors);
//        ElevatorService elevatorService = new ElevatorService();
//        elevatorService.initialize(building, numElevator);
//        System.out.println("Simulating external floor requests:");
//
//        floor1.pressButton(Direction.UP);     // Request from floor 1 to go UP
//        floor3.pressButton(Direction.DOWN);   // Request from floor 3 to go DOWN
//        floor5.pressButton(Direction.DOWN);   // Request from floor 5 to go DOWN
//
//        // Simulate internal button presses from inside Elevator 1 to go to Floor 4
//        ElevatorController elevator1 = elevatorService.getElevatorControllers().get(0);
//        elevator1.getElevator().getInternalButton().submitRequest(4);  // Press button to go to floor 4
//
//        // Another internal request to go to Floor 2 in Elevator 2
//        ElevatorController elevator2 = elevatorService.getElevatorControllers().get(0);
//        elevator1.getElevator().getInternalButton().submitRequest(2);  // Press button to go to floor 4

        // Number of elevators in the building
        int numElevators = 3;

        // Initialize floors
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);

        // Create a building with floors
        List<Floor> floors = Arrays.asList(floor1, floor2, floor3, floor4, floor5);
        Building building = new Building(floors);

        // Initialize the ElevatorService with the building and elevators
        ElevatorService elevatorService = new ElevatorService();
        elevatorService.initialize(building, numElevators);

        // Output current state of all elevators
        System.out.println("Initial state of all elevators:");
        for (ElevatorController controller : elevatorService.getElevatorControllers()) {
            controller.getElevator().displayStatus();
        }

        System.out.println("\nSimulating external floor requests:");

        // Simulate external requests from floors
        floor1.pressButton(Direction.UP);     // Request from floor 1 to go UP
        floor3.pressButton(Direction.DOWN);   // Request from floor 3 to go DOWN
        floor5.pressButton(Direction.DOWN);   // Request from floor 5 to go DOWN

        // Simulate processing of external requests
        for (ElevatorController controller : elevatorService.getElevatorControllers()) {
            controller.processRequests();
        }

        // Simulate internal button presses from inside Elevator 1 to go to Floor 4
        System.out.println("\nSimulating internal requests within elevators:");
        ElevatorController elevator1 = elevatorService.getElevatorControllers().get(0);
        elevator1.getElevator().getInternalButton().submitRequest(4);  // Press button to go to floor 4
        elevator1.processRequests();

        // Simulate another internal request to go to Floor 2 in Elevator 2
        ElevatorController elevator2 = elevatorService.getElevatorControllers().get(1);
        elevator2.getElevator().getInternalButton().submitRequest(2);  // Press button to go to floor 2
        elevator2.processRequests();

        // Display final state of all elevators after processing
        System.out.println("\nFinal state of all elevators:");
        for (ElevatorController controller : elevatorService.getElevatorControllers()) {
            controller.getElevator().displayStatus();
        }
    }
}
