
package LowLevelDesign.Elevator;

import java.util.List;
import java.util.stream.Collectors;

public class OddEvenStrategy implements ExternalRequestServingStrategy {
    private ElevatorService elevatorService;

    public OddEvenStrategy(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @Override
    public void addRequest(Request request) {
        List<ElevatorController> acceptedControllers = elevatorService.getElevatorControllers().stream()
                .filter(controller -> Integer.parseInt(controller.getId().substring(0, 1), 16) % 2 == 0)
                .collect(Collectors.toList());

        acceptedControllers.forEach(controller -> controller.acceptRequest(request));
    }
}
