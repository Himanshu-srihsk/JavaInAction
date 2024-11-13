package LowLevelDesign.Elevator;

public class ExternalButton {
    private int floorNumber;
    Direction direction;
    private ExternalDispatcher externalDispatcher;
    public ExternalButton(){
        this.externalDispatcher = new ExternalDispatcher(new OddEvenStrategy(new ElevatorService()));
    }
    public void submitRequest(int currentFloor, Direction direction){
        Request request = new Request(currentFloor,RequestType.EXTERNAL, direction);
        externalDispatcher.acceptRequest(request);
    }
}
