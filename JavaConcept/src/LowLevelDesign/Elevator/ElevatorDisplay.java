package LowLevelDesign.Elevator;

public class ElevatorDisplay {
    private int currentFloor;
    Direction direction;
    public ElevatorDisplay(){
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
    }
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void displayStatus(){
        System.out.println("Elevator is currently on floor: " + currentFloor + ", and is moving " + direction);
    }
    public int getCurrentFloor() {
        return currentFloor;
    }
}
