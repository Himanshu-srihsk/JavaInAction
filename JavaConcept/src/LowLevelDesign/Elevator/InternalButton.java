package LowLevelDesign.Elevator;

public class InternalButton {
    private int[] buttons = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private ElevatorController elevatorController;

    public InternalButton(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    public void submitRequest(int destinationFloor) {
        Request request;
        if (destinationFloor > elevatorController.getCurrentFloor()) {
            request = new Request(destinationFloor, RequestType.INTERNAL, Direction.UP);
        } else {
            request = new Request(destinationFloor, RequestType.INTERNAL, Direction.DOWN);
        }
        elevatorController.acceptRequest(request);
    }
}
