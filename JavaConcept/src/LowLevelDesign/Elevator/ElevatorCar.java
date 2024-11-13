package LowLevelDesign.Elevator;

import java.util.UUID;

public class ElevatorCar {
    private String id;
    private ElevatorDisplay display;
    private InternalButton internalButton;
    private ElevatorController controller;

    public ElevatorCar() {
        this.id = UUID.randomUUID().toString();
        this.display = new ElevatorDisplay();
    }

    public void setController(ElevatorController controller) {
        this.controller = controller;
        this.internalButton = new InternalButton(controller);  // Set the button with the controller
    }

    public ElevatorDisplay getDisplay() {
        return display;
    }

    public InternalButton getInternalButton() {
        return internalButton;
    }

    public int getCurrentFloor() {
        return display.getCurrentFloor();
    }

    public void setCurrentFloor(int currentFloor) {
        display.setCurrentFloor(currentFloor);
    }

    public Direction getDirection() {
        return display.getDirection();
    }

    public void setDirection(Direction direction) {
        display.setDirection(direction);
    }

    public void displayStatus() {
        display.displayStatus();
    }

    public String getId() {
        return id;
    }
}
