// Request.java
package LowLevelDesign.Elevator;

import java.util.UUID;

public class Request {
    private String id;
    private int destinationFloor;
    private Direction direction;
    private RequestType requestType;

    public Request(int destinationFloor, RequestType requestType, Direction direction) {
        this.id = UUID.randomUUID().toString();
        this.destinationFloor = destinationFloor;
        this.requestType = requestType;
        this.direction = direction;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getId() {
        return id;
    }
}
