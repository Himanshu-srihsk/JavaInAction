package LowLevelDesign.Elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ElevatorService {
    private Building building;
    private List<ElevatorController> elevatorControllers;

    public ElevatorService() {
        this.elevatorControllers = new ArrayList<>();
    }

    // Add controller to the service
    public void addController(ElevatorController controller) {
        elevatorControllers.add(controller);
    }

    // Remove controller from the service
    public void removeController(ElevatorController controller) {
        elevatorControllers.remove(controller);
    }

    // Getter for elevator controllers
    public List<ElevatorController> getElevatorControllers() {
        return elevatorControllers;
    }

    // Removes duplicate external requests from all elevators
    public void removeDuplicateRequestFromOtherElevators(Request request) {
        for (ElevatorController controller : elevatorControllers) {
            controller.removeIfAnyDuplicateRequestExist(request);
        }
    }

    public void initialize(Building building, int numElevators) {
        this.building = building;  // Set the building

        // Initialize each elevator controller and elevator car, then associate them
        IntStream.rangeClosed(1, numElevators)
                .forEach(id -> {
                    ElevatorCar car = new ElevatorCar();
                    ElevatorController controller = new ElevatorController(car);
                    car.setController(controller);
                    elevatorControllers.add(controller);
                });
    }

}
