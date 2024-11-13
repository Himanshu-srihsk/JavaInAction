package LowLevelDesign.Elevator;

import java.util.Iterator;
import java.util.PriorityQueue;

public class ElevatorController {
    private ElevatorCar elevator;
    private PriorityQueue<Request> upQueue;
    private PriorityQueue<Request> downQueue;
    private ElevatorService elevatorService;

    public ElevatorController(ElevatorCar elevator) {
        this.elevator = elevator;
        downQueue = new PriorityQueue<>((r1, r2) -> Integer.compare(r2.getDestinationFloor(), r1.getDestinationFloor()));
        upQueue   = new PriorityQueue<>((r1, r2) -> Integer.compare(r1.getDestinationFloor(), r2.getDestinationFloor()));
    }
    public void acceptRequest(Request request){
       if(request.getDirection() == Direction.UP){
          if(request.getDestinationFloor() > elevator.getCurrentFloor()){
              upQueue.add(request);
          }else{
              downQueue.add(request);
          }
       }
       if(request.getDirection() == Direction.DOWN){
           if(request.getDestinationFloor() < elevator.getCurrentFloor()){
               downQueue.add(request);
           }else{
               upQueue.add(request);
           }
       }
    }
    public int getCurrentFloor(){
        return elevator.getCurrentFloor();
    }
    public String getId(){
        return elevator.getId();
    }
    public ElevatorCar getElevator(){
        return elevator;
    }
    public void processRequests(){
        if(!upQueue.isEmpty() && elevator.getDirection() == Direction.UP){
            Request request = upQueue.poll();
            elevator.setCurrentFloor(request.getDestinationFloor());
            elevator.displayStatus();
            if(request.getRequestType() == RequestType.EXTERNAL){
                elevatorService.removeDuplicateRequestFromOtherElevators(request);
            }
        }
        else if(!downQueue.isEmpty() && elevator.getDirection() == Direction.DOWN){
            Request request = downQueue.poll();
            elevator.setCurrentFloor(request.getDestinationFloor());
            elevator.displayStatus();
        }else {
            elevator.setDirection(Direction.IDLE);
        }
    }
//    public void removeIfAnyDuplicateRequestExist(Request request){
//        Iterator<Request> iterator = upQueue.iterator();
//        while (iterator.hasNext()) {
//            Request currentRequest = iterator.next();
//            if (currentRequest.getRequestType() == RequestType.EXTERNAL && currentRequest.getId() == request.getId()) {
//                iterator.remove();
//                break;
//            }
//        }
//
//        Iterator<Request> downIterator = upQueue.iterator();
//        while (downIterator.hasNext()) {
//            Request currentRequest = downIterator.next();
//            if (currentRequest.getRequestType() == RequestType.EXTERNAL && currentRequest.getId() == request.getId()) {
//                downIterator.remove();
//                break;
//            }
//        }
//    }
    public void removeIfAnyDuplicateRequestExist(Request request) {
        upQueue.removeIf(r -> r.getRequestType() == RequestType.EXTERNAL && r.getId().equals(request.getId()));
        downQueue.removeIf(r -> r.getRequestType() == RequestType.EXTERNAL && r.getId().equals(request.getId()));
    }
}
